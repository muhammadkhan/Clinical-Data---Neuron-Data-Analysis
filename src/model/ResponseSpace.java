
package model;

public class ResponseSpace{
	
	//constructor here
	
	
	
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
	
	//what we can do here is implement the smith-waterman algorithm
	
	
	
	
	
	
	
	
	
//	/**
//	 * This is the method to calculate the correlation between two neurons
//	 * Strategy: turn both neurons into vectors and compare the cosine.
//	 * @param a
//	 * @param b
//	 * @return
//	 */
//	private double neuronTransform(Neuron a, Neuron b){
//		
//		
//		return 0.0;
//	}
	

	
	
	
		
}

