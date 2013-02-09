package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Neuron {
	private Map<Stimulus, ArrayList<Double> > spikeTimes;
	
	public Neuron(){
		spikeTimes = new HashMap<Stimulus, ArrayList<Double> >();
	}
	
	public Map<Stimulus,ArrayList<Double> > getSpikesMap(){
		return spikeTimes;
	}
}
