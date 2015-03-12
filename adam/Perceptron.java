package adam;
public class Perceptron {
	int[] weights;
	int size;
	double value;
	public Perceptron (int[] weights) {
		this.weights = weights;
		size = weights.length;
	}

	public Perceptron (Perceptron p) {
		this.size = p.size;
		for (int i = 0; i < size; i ++) {
			weights[i] = p.weights[i];
		}
	}

	public double output (double[] input) {
		int output = 0;
		for (int i = 0; i < input.length; i++) {
			output += input[i] * weights[i];
		}
		value = output;
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
}