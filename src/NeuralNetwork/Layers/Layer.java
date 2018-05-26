package NeuralNetwork.Layers;

import java.util.Iterator;
import java.util.List;

import NeuralNetwork.Neurons.Neuron;

public abstract class Layer {

    abstract int getNeuronCount();

    private Layer nextLayer;
    private Layer previousLayer;

    public Layer getNextLayer() {
        return nextLayer;
    }

    public Layer getPreviousLayer() {
        return previousLayer;
    }

    public void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }

    public void setPreviousLayer(Layer previousLayer) {
        this.previousLayer = previousLayer;
    }

    public abstract Iterator<Neuron> getNeurons();

    public abstract void initializeLayer();
}
