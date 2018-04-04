
public class Tester {
	public static void main(String[] args) {

		double[] input = { 2, 34, 2, 5, 6, 43, 543, 5345, 45, 345, 2345, 54 };
		double[] expected = { 0, 1, 0 };

		Net net = new Net();

		net.addLayer(input.length);
		net.addLayer(27);
		net.addLayer(3);

		net.setInputLayerAsDouble(input);
		net.setExpectedOutputLayerAsDouble(expected);

		net.optimize(10000, .3);

		net.printOutputLayer();
	}
}
