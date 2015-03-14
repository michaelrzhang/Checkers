package adam;
import java.util.*;
public class NeuralMap {
	HashMap<Perceptron, Perceptron[]> nmap;
	ArrayList<HashSet<Perceptron>> mapLevels;
	public NeuralMap () {

	}

	public NeuralMap (NeuralMap nm) {
		Perceptron[] perceptronin;
		Perceptron[] originalPerceptronList;
		HashSet<Perceptron> plevel;		
		for (Perceptron p : nm.nmap.keySet()) {
			originalPerceptronList = nm.nmap.get(p);
			perceptronin = new Perceptron[originalPerceptronList.length];
			for (int i = 0; i < originalPerceptronList.length; i++ ) {
				perceptronin[i] = new Perceptron(originalPerceptronList[i]);
			}
			this.nmap.put(p, perceptronin);
		}
		for (HashSet<Perceptron> hmp: nm.mapLevels) {
			plevel = new HashSet<Perceptron>();
			for (Perceptron p: hmp) {
				plevel.add(new Perceptron(p));
			}
		}
	}

	public double output (int[] input) {
		Perceptron[] previousLevel;
		double[] perceptronin;
		double output = 0;
		for (int j = 0; j < mapLevels.size(); j++) {
			for (Perceptron p: mapLevels.get(j)) {
				previousLevel = nmap.get(p);
				perceptronin = new double[p.size];
				for (int i = 0; i < p.size; i++){
					if (previousLevel[i] != null) {
						perceptronin[i] = previousLevel[i].getValue();
					} else {
						perceptronin[i] = input[i];
					}
				}
				output = p.output(perceptronin);
			}
		}
		return output;
	}

	public Perceptron highestOuputter (double[] input, int level) {
		double max = Integer.MIN_VALUE;
		Perceptron maxPerceptron = new Perceptron();
		for (Perceptron p : mapLevels.get(level)) {
			if (p.output(input) > max) {
				max = p.output(input);
				maxPerceptron = p;
			}
		}
		return maxPerceptron;
	}	
}