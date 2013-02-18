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
	
	//Need a method to get all of the double arrays 
	//for each particular stimulus-neuron pair 
	//public double[] getStimulus
	
	
	
	
	
}
