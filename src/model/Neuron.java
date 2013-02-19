package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Neuron {
	private Map<Stimulus, ArrayList<double[]> > spikeTimes;
	private ArrayList<ResponseSpace> rspaces;
	
	public Neuron(){
		spikeTimes = new HashMap<Stimulus, ArrayList<double[]> >();
		rspaces = new ArrayList<ResponseSpace>();
	}
	
	public Map<Stimulus,ArrayList<double[]> > getSpikesMap(){
		return spikeTimes;
	}
	
	public ArrayList<ResponseSpace> getResponseSpaces(){
		return rspaces;
	}
	
	/**
	 * Gets an arrayList of the spiketimes for this Neuron-Stimulus pair.
	 * We use this in the ResponseSpace Class to construct our comparisons
	 * between spike trains of the same stimuli  
	 * @param n
	 * @param x
	 * @return
	 */
	public ArrayList<double[]> getStimulus_Spikes(Neuron n, Stimulus x){
		Map<Stimulus, ArrayList<double[]>> m = n.getSpikesMap();
		return m.get(x);
	}
	
	/**
	 * Returns the sensitive stimuli of that particular Neuron
	 * @return
	 */
	public ArrayList<Stimulus> sensitiveStimuli(){
		Stimulus[] ss = {Stimulus.N,Stimulus.Q,Stimulus.H,Stimulus.S,Stimulus.NQ,Stimulus.NH,Stimulus.NS,Stimulus.HQ,Stimulus.HS,Stimulus.SQ};
		ArrayList<Stimulus> stimuli = new ArrayList<Stimulus>();
		for(Stimulus s : ss){
			ArrayList<double[]> ald = spikeTimes.get(s);
			double avg = 0;
			for(double[] darr : ald){
				avg += darr.length / ald.size();
			}
			if(avg >= 30.0)
				stimuli.add(s);
		}
		return stimuli;
	}
	
	public void genResponseSpaces(){
		ArrayList<Stimulus> sensitives = sensitiveStimuli();
		for(Stimulus s : sensitives){
			rspaces.add(new ResponseSpace(spikeTimes.get(s), s));
		}
	}
	
	public Stimulus testTrain(double[] t){
		for(ResponseSpace rs : rspaces)
			rs.testTrain(t);
		
		int maxNN = Integer.MIN_VALUE;
		Stimulus type = null;
		for(ResponseSpace rs : rspaces){
			int num = rs.numNN(t);
			if(num > maxNN){
				maxNN = num;
				type = rs.getStimType();
			}
		}
		return type;
	}
	
}
