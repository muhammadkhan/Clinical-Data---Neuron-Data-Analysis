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
}
