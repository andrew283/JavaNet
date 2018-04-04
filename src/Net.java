import java.util.ArrayList;

public class Net {
	private int numberOfLayers = 0;
	private ArrayList<Layer> layers = new ArrayList<Layer>();
	private Layer expectedOutputLayer;

	public void addLayer(int numberOfNeurons) {
		if (numberOfLayers == 0)
			layers.add(new Layer(numberOfNeurons));
		if (numberOfLayers > 0)
			layers.add(new Layer(numberOfNeurons, layers.get(numberOfLayers - 1).numberOfNeurons()));
		expectedOutputLayer = new Layer(numberOfNeurons);
		numberOfLayers++;
	}

	public void setInputLayerAsDouble(double[] values) {
		layers.get(0).setNeuronActivationsAsDouble(values);
	}

	public void setExpectedOutputLayerAsDouble(double[] d) {
		expectedOutputLayer.setNeuronActivationsAsDouble(d);
	}

	public void feedforward() {
		for (int i = 1; i < layers.size(); i++) {
			for (int j = 0; j < layers.get(i).numberOfNeurons(); j++) {
				layers.get(i).neuronAt(j).calculateActivation(layers.get(i - 1));
			}
		}
	}

	public void backpropAndUpdate(double LR) {
		if (expectedOutputLayer.outputLayerHasBeenSet()) {
			feedforward();
			for (int i = numberOfLayers - 1; i >= 0; i--) {
				for (int j = 0; j < layers.get(i).numberOfNeurons(); j++) {
					double errorFactor = 0;
					double delta = 0;
					double predicted = layers.get(i).neuronAt(j).activation();
					if (i == numberOfLayers - 1) {
						errorFactor = expectedOutputLayer.neuronAt(j).activation() - predicted;
					} else {
						for (int k = 0; k < layers.get(i + 1).numberOfNeurons(); k++) {
							errorFactor += layers.get(i + 1).neuronAt(k).delta()
									* layers.get(i + 1).neuronAt(k).weights()[j];

						}
					}
					delta = calculateDelta(predicted, errorFactor);
					layers.get(i).neuronAt(j).setDelta(delta);
					layers.get(i).neuronAt(j).addThisToBias(LR * delta);
				}
			}
		} else {
			System.out.println(
					"The expected output layer's activations have not been assigned values correctly or at all. Backpropogation did not run.");
		}
	}

	public void optimize(int iter, double LR) {
		for (int i = 0; i < iter; i++) {
			backpropAndUpdate(LR);
		}
	}

	public void printOutputLayer() {
		Layer last = layers.get(numberOfLayers - 1);
		last.printLayerActivations();
	}

	public double calculateDelta(double predicted, double error) {
		return predicted * (1.0 - predicted) * error;
	}

	public double round(double a, int b) {
		return Math.round(a * Math.pow(10, b)) / Math.pow(10, b);
	}

}
