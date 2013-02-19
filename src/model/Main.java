package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import parser.DataParser;

public class Main {
	
	public static void main(String[] args){
		/*ResponseSpaceCalculator k = new ResponseSpaceCalculator();
		//test of aligning two like-length sequences.
		double[] a = {3.0,2.0,1.0};
		double[] b = {1.0,2.0,3.0};
		double cost = k.align2(a, b);
		System.out.println(cost);
		System.out.println(Arrays.toString(a));
		
		//test of unsized sequences
		double[] c = {1.0, 2.0, 3.0, 4.0, 5.0};
		double[] d = {2.0, 3.0, 4.0};
		//So this has the case a > b
		double cost2 = k.align(c,d);
		System.out.println("The a > b case has cost: " + cost2);
		
		double[] e = {2.0, 3.0, 4.0};
		double[] f = {1.0, 2.0, 3.0, 4.0, 5.0};
		double cost3 = k.align(e, f);
		System.out.println("The a < b case has cost: " + cost3);
		
		//need to perform tests on the test case input that we have.
		//Basically we should take like 9 neurons as the test set, that
		//way we are able to construct an adequate set to model on. */
		Set<Neuron> neurons = DataParser.parseFile("modelData.txt");
		for(Neuron n : neurons)
			n.genResponseSpaces();
		//rspaces have been initialized
		ArrayList<double[]> ds = new ArrayList<double[]>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File("testTrains.txt")));
			String line = "";
			while((line = br.readLine()) != null){
				String[] s = line.split(" ");
				double[] d = new double[s.length];
				for(int i = 0; i < s.length; i++)
					d[i] = Double.parseDouble(s[i]);
				ds.add(d);
			}
			br.close();
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
		for(Neuron n : neurons){
			System.out.println("------Neuron #" + n.getID() + " START-------");
			for(double[] d : ds){
				Stimulus s = n.testTrain(d);
				System.out.println(d + " : " + s);
			}
			System.out.println("------Neuron #" + n.getID() + " END---------");
		}
	}
}
