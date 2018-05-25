package NeuralNetwork;

import java.util.List;

public abstract class Layer {

    abstract int getNeuronCount();

    private Layer nextLayer;
    private Layer previousLayer;

    Layer getNextLayer() {
        return nextLayer;
    }

    Layer getPreviousLayer() {
        return previousLayer;
    }

    void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }

    void setPreviousLayer(Layer previousLayer) {
        this.previousLayer = previousLayer;
    }

    public abstract List<Neuron> getNeurons();

    public abstract void initializeLayer();
}
