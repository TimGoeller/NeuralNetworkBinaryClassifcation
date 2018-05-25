package NeuralNetwork;

import NeuralNetwork.Layers.HiddenLayer;
import NeuralNetwork.Layers.InputLayer;
import NeuralNetwork.Layers.Layer;
import NeuralNetwork.Layers.OutputLayer;

import java.util.ArrayList;
import java.util.Iterator;
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

        if (hiddenLayers.isEmpty()) {
            previousLayer = inputLayer;
        } else {
            previousLayer = hiddenLayers.get(hiddenLayers.size() - 1);
        }

        hiddenLayer.setPreviousLayer(previousLayer);
        previousLayer.setNextLayer(hiddenLayer);
        hiddenLayer.setNextLayer(outputLayer);
        outputLayer.setPreviousLayer(hiddenLayer);

        hiddenLayers.add(hiddenLayer);
    }

    public void initializeLayers() {

        Iterator<Layer> layerIt = getLayersAsIterator();

        while(layerIt.hasNext()) {
            layerIt.next().initializeLayer();
        }
    }

    public Iterator<Layer> getLayersAsIterator() {
        return new Iterator<Layer>() {

            private Layer currentLayer;

            @Override
            public boolean hasNext() {
                return !(currentLayer instanceof OutputLayer);
            }

            @Override
            public Layer next() {
                if(currentLayer == null)
                    currentLayer = inputLayer;
                else
                    currentLayer = currentLayer.getNextLayer();
                return currentLayer;
            }
        };
    }

}