package NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork { //static?

    private InputLayer inputLayer;
    public List<HiddenLayer> hiddenLayers = new ArrayList<HiddenLayer>();
    private OutputLayer outputLayer;

    public NeuralNetwork(int inputNeuronCount) {

        inputLayer = new InputLayer(inputNeuronCount);
        outputLayer = new OutputLayer();
        inputLayer.setNextLayer(outputLayer);
        outputLayer.setPreviousLayer(inputLayer);

    }

    public void addHiddenLayer(int neuronCount) {

        HiddenLayer hiddenLayer = new HiddenLayer(neuronCount);

        Layer previousLayer;

        if(hiddenLayers.isEmpty()) {
            previousLayer = inputLayer;
        }
        else {
            previousLayer = hiddenLayers.get(hiddenLayers.size() - 1);
        }

        hiddenLayer.setPreviousLayer(previousLayer);
        previousLayer.setNextLayer(hiddenLayer);
        hiddenLayer.setNextLayer(outputLayer);
        outputLayer.setPreviousLayer(hiddenLayer);

        hiddenLayers.add(hiddenLayer);
    }
}
