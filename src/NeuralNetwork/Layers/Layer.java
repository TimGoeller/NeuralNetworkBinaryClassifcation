package NeuralNetwork.Layers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import NeuralNetwork.NeuralNetwork;
import NeuralNetwork.Neurons.HiddenNeuron;
import NeuralNetwork.Neurons.Neuron;

public abstract class Layer<T extends Neuron> implements Iterable<T>{

    protected List<T> neurons = new ArrayList<T>();
    private Layer nextLayer;
    private Layer previousLayer;

    int getNeuronCount()
    {
        return neurons.size();
    }

    public Layer getNextLayer()
    {
        return nextLayer;
    }

    public Layer getPreviousLayer()
    {
        return previousLayer;
    }

    public void setNextLayer(Layer nextLayer)
    {
        this.nextLayer = nextLayer;
    }

    public void setPreviousLayer(Layer previousLayer)
    {
        this.previousLayer = previousLayer;
    }

    public Iterator<T> getNeuronsAsIterator()
    {
        return new Iterator<>() {

            int currentIndex;

            @Override
            public boolean hasNext()
            {
                return currentIndex < neurons.size();
            }

            @Override
            public T next()
            {
                T neuronToReturn = null;

                if (currentIndex < neurons.size()) {
                    neuronToReturn = neurons.get(currentIndex);
                }

                currentIndex++;
                return neuronToReturn;
            }
        };
    }

    public List<T> getNeurons()
    {
        return neurons;
    }

    public abstract void initializeLayer();

    @Override
    public Iterator<T> iterator()
    {
        return getNeuronsAsIterator();
    }
}
