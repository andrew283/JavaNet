# JavaNet
Basic Neural Network Implementation in Java.
# How to Use
### Initialize a net:
    Net net = new Net();
### Add layers (Layer Size):
    net.addLayer(784);
    net.addLayer(200);
    net.addLayer(10);
### Set input and expected output layers (double[]):
    net.setInputLayerAsDouble(array);
    net.setExpectedOutputLayerAsDouble(array);
### Train the net (Number of Iterations, Learning Rate):
    net.optimize(10000, .3);
### Print output layer:
    net.printOutputLayer();
