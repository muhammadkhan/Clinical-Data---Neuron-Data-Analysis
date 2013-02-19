package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ResponseSpace{
	
	private ArrayList<double[]> c;
	private double[][] distances;
	private Map<double[], Integer> knnMap;
	
	public ResponseSpace(ArrayList<double[]> spiketrains){
		c = spiketrains;
		knnMap = new HashMap<double[], Integer>();
		computeDistances();
	}
	
	private void computeDistances(){
		distances = new double[c.size()][c.size()];
		for(int i = 0; i < c.size(); i++){
			for(int j = 0; j < c.size(); j++){
				distances[i][j] = ResponseSpaceCalculator.align(c.get(i), c.get(j));
			}
		}
	}
	
	public void testTrain(double[] t){
		c.add(t);
		computeDistances();
		Double[] tt = new Double[t.length];
		for(int i = 0; i < t.length; i++)
			tt[i] = t[i];
		int i = 0;
		while(true){
			Double[]  dd = new Double[distances[i].length];
			for(int j = 0; j < distances[i].length; j++)
				dd[j] = distances[i][j];
			if(Arrays.deepEquals(tt, dd));
				break;
		}
		//i will be set equal to t
		int count = 0;
		double threshold = .5;
		for(int j = 0; j < c.size(); j++){
			if(distances[i][j] <= threshold)
				count++;
		}
		knnMap.put(t, count);
	}
}

