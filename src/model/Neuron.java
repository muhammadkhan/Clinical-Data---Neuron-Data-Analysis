package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Neuron {
	private Map<Stimulus, ArrayList<double[]> > spikeTimes;
	
	public Neuron(){
		spikeTimes = new HashMap<Stimulus, ArrayList<double[]> >();
	}
	
	public Map<Stimulus,ArrayList<double[]> > getSpikesMap(){
		return spikeTimes;
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
		Map m = n.getSpikesMap();
		return (ArrayList<double[]>) m.get(x);
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
	

	
	
}
