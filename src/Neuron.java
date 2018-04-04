
public class Neuron {
	private double[] weights;
	private double bias;
	private double activation;
	public double delta;

	public Neuron(int previousLayerSize) {
		weights = new double[previousLayerSize];
		initializeWeightsRandomly();
	}

	public Neuron() {

	}

	public void calculateActivation(Layer previousLayer) {
		int sum = 0;
		for (int i = 0; i < previousLayer.numberOfNeurons(); i++) {
			sum += previousLayer.neuronAt(i).activation() * weights[i];
		}
		activation = sigmoid(sum + bias);
	}

	public void initializeWeightsRandomly() {
		activation = 2 * Math.random() - 1;
		for (int i = 0; i < weights.length; i++) {
			weights[i] = 2 * Math.random() - 1;
		}
		bias = 2 * Math.random() - 1;
	}

	public static double sigmoid(double d) {
		return 1. / (1 + Math.pow(Math.E, -d));
	}

	public void addThisToBias(double d) {
		bias += d;
	}

	public double activation() {
		return activation;
	}

	public void setActivation(double d) {
		activation = d;
	}

	public void setDelta(double d) {
		delta = d;
	}
	
	public double delta() {
		return delta;
	}
	
	public double[] weights() {
		return weights;
	}


}
