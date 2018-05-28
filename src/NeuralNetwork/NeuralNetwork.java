package NeuralNetwork;

import NeuralNetwork.Layers.HiddenLayer;
import NeuralNetwork.Layers.InputLayer;
import NeuralNetwork.Layers.Layer;
import NeuralNetwork.Layers.OutputLayer;
import NeuralNetwork.Neurons.ActivationNeuron;
import NeuralNetwork.Neurons.InputNeuron;
import NeuralNetwork.Neurons.Neuron;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NeuralNetwork { //static?

    private InputLayer inputLayer;
    public List<HiddenLayer> hiddenLayers = new ArrayList<HiddenLayer>();
    private OutputLayer outputLayer;

    private Dataset dataset;

    public NeuralNetwork(int inputNeuronCount, Dataset dataset) {

        inputLayer = new InputLayer(inputNeuronCount);
        outputLayer = new OutputLayer();
        inputLayer.setNextLayer(outputLayer);
        outputLayer.setPreviousLayer(inputLayer);
        this.dataset = dataset;
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

    public void trainNetwork(int epochs) {

        while(epochs > 0) {
            epochs --;

            dataset.trainingSet.forEach((data -> train(data)));
        }
    }

    private void train(List<Double> data) {

        Iterator<Layer> layerIt = getLayersAsIterator();

        int currentNeuron = 0;
        for(InputNeuron neuron : inputLayer.getNeurons()) {
            neuron.setDestinationValue(data.get(currentNeuron++));
            layerIt.next();
        }
        outputLayer.getOutputNeuron().setExpectedValue(data.get(currentNeuron));

        Layer currentLayer = inputLayer;

        while(currentLayer.getNextLayer() != null) {
            forwardPass(currentLayer.getNextLayer());
            currentLayer = currentLayer.getNextLayer();
        }
    }

    private void forwardPass(Layer layer) {

        Iterator<Neuron> it = layer.getNeuronsAsIterator();
        while(it.hasNext()) {
            ActivationNeuron currentNeuron = (ActivationNeuron)it.next();
            currentNeuron.sigmoidActivation();
        }
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