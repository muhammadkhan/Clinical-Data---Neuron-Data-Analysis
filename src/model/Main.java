package model;

import java.util.Arrays;

public class Main {
	
	
	
	public static void main(String[] args){
		ResponseSpaceCalculator k = new ResponseSpaceCalculator();
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
		//way we are able to construct an adequate set to model on. 
	}
}
