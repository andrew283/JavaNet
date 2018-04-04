
public class Layer {
	private boolean outputLayerHasBeenSet = false;
	private int numberOfNeurons;
	private Neuron[] neurons;

	public Layer(int numberOfNeurons, int previousLayerSize) {
		neurons = new Neuron[numberOfNeurons];
		for (int i = 0; i < neurons.length; i++) {
			neurons[i] = new Neuron(previousLayerSize);
		}
		this.numberOfNeurons = numberOfNeurons;
	}

	public Layer(int numberOfNeurons) {
		neurons = new Neuron[numberOfNeurons];
		for (int i = 0; i < neurons.length; i++) {
			neurons[i] = new Neuron();
		}
		this.numberOfNeurons = numberOfNeurons;
	}

	public void setNeuronActivationsAsDouble(double[] values) {
		if (values.length == neurons.length) {
			for (int i = 0; i < neurons.length; i++) {
				neurons[i].setActivation(values[i]);
			}
			outputLayerHasBeenSet = true;
		} else
			System.out.println("Failed to set neurons. Layer sizes must match.");
	}

	public Neuron neuronAt(int index) {
		return neurons[index];
	}

	public int numberOfNeurons() {
		return numberOfNeurons;
	}

	public Neuron[] getNeurons() {
		return neurons;
	}

	public void printLayerActivations() {
		for (int i = 0; i < numberOfNeurons; i++) {
			System.out.print(round(neuronAt(i).activation(), 2) + " ");
		}
		System.out.println();
	}

	public double round(double a, int b) {
		return Math.round(a * Math.pow(10, b)) / Math.pow(10, b);
	}
	
	public boolean outputLayerHasBeenSet() {
		return outputLayerHasBeenSet;
	}

}
