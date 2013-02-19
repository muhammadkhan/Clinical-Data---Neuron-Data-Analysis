package parser;

import model.Neuron;
import model.Stimulus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DataParser {
	public static Set<Neuron> parseFile(String fName){
		Set<Neuron> ret = new HashSet<Neuron>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fName)));
			String line;
			Neuron curNeuron = null;
			Stimulus curStim = null;
			int trialNum = 0;
			while((line = br.readLine()) != null){
				String[] parts = line.split(" ");
				try{
					trialNum = Integer.parseInt(parts[0]);
					//actual data
					ArrayList<double[]> ts = curNeuron.getSpikesMap().get(curStim);
					double[] arr = new double[Integer.parseInt(parts[0])];
					for(int i = 0; i < arr.length; i++)
						arr[i] = Double.parseDouble(parts[i+1]);
					ts.add(arr);
					ret.add(curNeuron);
				} catch(NumberFormatException e){
					if(parts[0].equals("Neuron")){
						/*if(!ret.isEmpty()){
							System.out.println("ret is nonempty");
							ret.add(curNeuron);
						}*/
						curNeuron = new Neuron(Integer.parseInt(parts[1].substring(1)));
						continue;
					}
					//<stimulus name> <num> case
					Stimulus stim = Stimulus.stimulus_of_string(parts[0]);
					if(stim != null){
						curNeuron.getSpikesMap().put(stim, new ArrayList<double[]>());
						curStim = stim;
						continue;
					}
				}
				/*if(parts.length == 2){
					//new neuron
					if(parts[0].equals("Neuron")){
						if(!ret.isEmpty())
							ret.add(curNeuron);
						curNeuron = new Neuron();
						continue;
					}
					//<stimulus name> <num> case
					Stimulus stim = Stimulus.stimulus_of_string(parts[0]);
					if(stim != null){
						trialNum = Integer.parseInt(parts[1]);
						curNeuron.getSpikesMap().put(stim, new ArrayList<double[]>());
						curStim = stim;
						continue;
					}
				} else{
					//actual data
					ArrayList<double[]> ts = curNeuron.getSpikesMap().get(curStim);
					double[] arr = new double[Integer.parseInt(parts[0])];
					for(int i = 0; i < arr.length; i++)
						arr[i] = Double.parseDouble(parts[i+1]);
				}*/
			}
			br.close();
			//ret.add(curNeuron); //to make sure the last one gets added
			return ret;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}
}
