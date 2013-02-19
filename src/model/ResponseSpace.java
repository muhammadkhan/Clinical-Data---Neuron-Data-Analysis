
package model;

import java.util.ArrayList;
import java.util.Arrays;

public class ResponseSpace{
	
	//constructor 
	public ResponseSpace(){}
	
	/**
	 * Method to sum up the values of a double list
	 * @param x
	 * @return
	 */
	public static double sum(double[] x){
	  double s = 0;	
      for (int i = 0; i < x.length; i++){
    	 s += x[i]; 
      }	
	  return s;
	}
	
	public static double sum_sq(double[] x){
		double s = 0;
		for(int i = 0; i < x.length; i++){
			s += x[i] * x[i];
		}
		return s;
	}
	
	public static double sum2(double[] x, double[] y){
		double s = 0;
		for(int i= 0; i < x.length; i++){
			s += (x[i] * y[i]);
		}
		return s;
	}
	
	/**
	 * Calculates the correlation coefficient of two lists of equal length
	 * @param a
	 * @param b
	 * @return
	 */
	private double correlationCoeff(double[] a, double[] b){
	  double n = (double) a.length;
	  double sum_a = sum(a);
	  double sum_b = sum(b);
	  double sum_a2 = sum_sq(a);
	  double sum_b2 = sum_sq(b);
	  double sum_ab = sum2(a,b);
	  
	  double top = (n*sum_ab)-(sum_a * sum_b);
	  double bottom2 = ((n*sum_a2) - (sum_a * sum_a)) * ((n*sum_b2) - (sum_b * sum_b));
	  double bottom = Math.sqrt(bottom2);
	  return top/bottom;
	}
	
	/**
	 * Given two equal length double arrays (a and b),
	 * We calculate the cost to transform a into b 
	 * @param a
	 * @param b
	 * @return
	 */
	public double align2(double[] a, double[] b){
	  double cost = 0;	
	  for (int i = 0; i < a.length; i++){
	    if (a[i] > b[i]){
	  	  cost += Math.abs(a[i]-b[i]);
	  	  a[i] -= a[i] - b[i];	  
	  	}
	    if (a[i] < b[i]){
	      cost += Math.abs(b[i] - a[i]);
	      a[i] += b[i] - a[i];
	    }
	  }
	  if (a==b){
		  System.out.println("A is now equal to B");}
	  return cost;	
	}
	
	/**
	 * Method to calculate the cost required to transform array a
	 * into array b. In this version there is no requirement that the length 
	 * must be equal, for we will compensate for that here.  
	 * @param a
	 * @param b
	 * @return
	 */
	public double align(double[]a, double[] b ){
		double cost  = 0;
		if (a.length < b.length){
		  //in this instance we must add all of the values 	
		  double[] a_prime = Arrays.copyOf(a, b.length);
		  System.out.println("Before: "+ Arrays.toString(a));
		  System.out.println("After: "+ Arrays.toString(a_prime));
		  //we add all of the values that are not in A
		  for (int i = a.length; i < b.length; i++){
			  cost += 1;
			  a_prime[i] = b[i];
		  }
		  cost += align2(a_prime, b);
		}
		//This case is a little bit trickier, 
		//since we do not want to eliminate "good" data
		else if (a.length > b.length){
		  //We can do copyOfRange to make them equal length
		  double[] a_prime = Arrays.copyOfRange(a, 0, b.length);
		  System.out.println("Before: "+ Arrays.toString(a));
		  System.out.println("After: "+ Arrays.toString(a_prime));
		  //we also need to truncate A, because 
		  cost += align2(a_prime,b);
		}
		else cost = align2(a,b);
		return cost;
	}
	
	/**
	 * If only we had List.filter from OCaml :)
	 * This is to filter so that we include only the first two seconds.
	 * We are given the list in a sorted fashion.
	 * @param start
	 * @param fin
	 * @param x
	 * @return
	 */
	//ideally this would not return an Object[], but a double[]
	public static double[] filter(double start, double fin, double[] x){
	  ArrayList<Double> acc = new ArrayList<Double>();
	  for(int i = 0; i < x.length; i++){
	    if ((x[i] <= fin) && (x[i] >= start)){acc.add(x[i]);} 
	  }
	  double[] ret = new double[acc.size()];
	  for(int i = 0; i < ret.length; i++)
		  ret[i] = acc.get(i);
	  return ret;
	}
	
	/**
	 * Returns the set of spike times that we want 
	 * @param n
	 * @return
	 */
	//ideally we would want to return a double[][]
	public ArrayList<double[]> getCompSet(Neuron n, Stimulus s){
	  ArrayList<double[]> s_t = n.getStimulus_Spikes(n, s);
	  ArrayList<double[]> acc = new ArrayList<double[]>();
	  for(int i = 0; i < s_t.size(); i++){
	    double[] intermediate = filter(20.0, 22.0, s_t.get(i));
	    acc.add(intermediate);
	  }
	  return acc;
	}
	
	//need to return the changing value for every possible combination
	//of nerves that deal with that stimulus. 
	/**
	 * Returns the adjustment score between all of the different 
	 * spike trains for a given neuron and stimulus. 
	 * @param x
	 * @return
	 */
	public ArrayList<Tuple<Integer, Integer, Double>> calculateComparisonSpace(ArrayList<double[]> x){
		//how do we create the tuple types that we want to return?
		ArrayList<Tuple<Integer, Integer, Double>> acc = new ArrayList<Tuple<Integer, Integer, Double>>();
		for(int i = 0; i < x.size(); i++){
		  for(int j = 0; j< x.size(); j++){
			double score = this.align(x.get(i), x.get(j));
			acc.add(new Tuple<Integer, Integer, Double>(i, j, score));
		  }		
		}
		return acc;
	}
	
	/**
	 * This will return all of the costs involved to compare 
	 * the spike trains of this neuron-stimulus pair.
	 * From this we are able to show that there is a good fit/bad fit 
	 * with an aggregation of the costs.
	 * @param n
	 * @param s
	 * @return
	 */
	public ArrayList<Tuple<Integer, Integer, Double>> createComparisionSpace(Neuron n, Stimulus s){
		return this.calculateComparisonSpace(n.getStimulus_Spikes(n, s));
	}
	
	/**
	 * Will create a response space for one neuron given all of its 
	 * spike train data.
	 * @param n
	 */
	public void analyzeData(Neuron n){
	  	
	}
	
	
	
	public static void main(String[] args){
		ResponseSpace k = new ResponseSpace();
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
	
	public class Tuple<S,T,U>{
		S first;
		T second;
		U third;
		
		public Tuple(S s, T t, U u){
			first = s;
			second = t;
			third = u;
		}
		
		public S getFirst(){ return first;}
		public T getSecond(){ return second; }
		public U getThird(){ return third; }
		
		public void setFirst(S s){ first = s; }
		public void setSecond(T t){ second = t; }
		public void setThird(U u){ third = u; }
	}
}

