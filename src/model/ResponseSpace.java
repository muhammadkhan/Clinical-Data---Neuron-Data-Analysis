package model;

import java.util.ArrayList;

public class ResponseSpace{
	
	private ArrayList<double[]> c;
	private double[][] distances;
	
	public ResponseSpace(ArrayList<double[]> spiketrains){
		c = spiketrains;
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
	
	public void addTrain(double[] t){
		c.add(t);
		computeDistances();
	}
}

