package adam;
public class Perceptron {
	double[] weights;
	int size;
	double value;
	public Perceptron () {}
	public Perceptron (double[] weights) {
		this.weights = weights;
		size = weights.length;
	}

	public Perceptron (Perceptron p) {
		this.size = p.size;
		for (int i = 0; i < size; i ++) {
			weights[i] = p.weights[i];
		}
	}

	public Perceptron (Perceptron p, double[] updatew) {
		this.size = p.size;
		for (int i = 0; i < size; i ++) {
			weights[i] = p.weights[i] + updatew[i];
		}
	}

	public double output (double[] input) {
		int output = 0;
		for (int i = 0; i < input.length; i++) {
			output += input[i] * weights[i];
		}
		return output;
	}

	public void setValue (double[] input) {
		int output = 0;
		for (int i = 0; i < input.length; i++) {
			output += input[i] * weights[i];
		}
		value = output;
	}
	public int size () {
		return size;
	}
	public double getValue() {
		return value;
	}

	public void addWeights(double[] w) {
		for (int i = 0; i < weights.length; i++) {
			weights[i] += w[i];
		}
	}

	public void setWeights(double[] w) {
		weights = w;
	}
}