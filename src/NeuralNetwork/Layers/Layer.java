package NeuralNetwork.Layers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import NeuralNetwork.NeuralNetwork;
import NeuralNetwork.Neurons.HiddenNeuron;
import NeuralNetwork.Neurons.Neuron;

public abstract class Layer<T extends Neuron> {

    protected List<T> neurons = new ArrayList<T>();

    int getNeuronCount() {
        return neurons.size();
    }

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

    public Iterator<T> getNeuronsAsIterator() {

        return new Iterator<T>() {

            int currentIndex;

            @Override
            public boolean hasNext() {
                return currentIndex < neurons.size();
            }

            @Override
            public T next() {

                Neuron neuronToReturn = null;

                if(currentIndex < neurons.size()) {
                    neuronToReturn = neurons.get(currentIndex);
                }

                currentIndex++;
                return (T)neuronToReturn;
            }
        };

    }
    public List<T> getNeurons() {
        return neurons;
    }

    public abstract void initializeLayer();
}
