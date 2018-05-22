package NeuralNetwork;

import java.util.List;

public class NeuralNetwork { //static?

    private InputLayer inputLayer;
    private List<HiddenLayer> hiddenLayers;
    private OutputLayer outputLayer;

    public NeuralNetwork(int inputNeuronCount, int[] hiddenLayerNeuronsCount) {

        inputLayer = new InputLayer(inputNeuronCount);
        outputLayer = new OutputLayer();

        int currentLayer = 0;
        for(int layerNeuronCount : hiddenLayerNeuronsCount) {
            if(currentLayer == 0) {
                hiddenLayers.add(new HiddenLayer(layerNeuronCount, inputNeuronCount));
            }
            else {
                hiddenLayers.add(new HiddenLayer(layerNeuronCount, hiddenLayers.get(currentLayer - 1).getNeuronCount()));
            }

            currentLayer++;
        }
    }
}
