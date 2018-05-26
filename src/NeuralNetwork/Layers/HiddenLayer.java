package NeuralNetwork.Layers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import NeuralNetwork.Connection;
import NeuralNetwork.Neurons.HiddenNeuron;
import NeuralNetwork.Neurons.Neuron;

public class HiddenLayer extends Layer {

    private List<HiddenNeuron> neurons = new ArrayList<HiddenNeuron>();

    public HiddenLayer(int neuronCount) {
        for(int i = 0; i <= neuronCount; i++) {
            neurons.add(new HiddenNeuron());
        }
    }

    @Override
    int getNeuronCount() {
        return neurons.size();
    }

    @Override
    public Iterator<Neuron> getNeurons() {

        return new Iterator<Neuron>() {

            int currentIndex;

            @Override
            public boolean hasNext() {
                return currentIndex < neurons.size();
            }

            @Override
            public Neuron next() {

                Neuron neuronToReturn = null;

                if(currentIndex < neurons.size()) {
                    neuronToReturn = neurons.get(currentIndex);
                }

                currentIndex++;
                return neuronToReturn;
            }
        };

    }

    @Override
    public void initializeLayer() {
        for(HiddenNeuron neuron : neurons) {
            List<Connection> hiddenNeuronConnections = new ArrayList<Connection>();
            super.getPreviousLayer().getNeurons().forEachRemaining(previousNeuron -> hiddenNeuronConnections.add(new Connection(previousNeuron, neuron)));
            neuron.setConnections(hiddenNeuronConnections);
        }
    }
}
