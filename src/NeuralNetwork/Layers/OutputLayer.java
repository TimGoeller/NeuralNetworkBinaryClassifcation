package NeuralNetwork.Layers;

import NeuralNetwork.Connection;
import NeuralNetwork.Neurons.Neuron;
import NeuralNetwork.Neurons.OutputNeuron;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OutputLayer extends Layer {

    private OutputNeuron neuron;

    public OutputLayer() {
        neuron = new OutputNeuron();
    }

    @Override
    int getNeuronCount() {
        return 1;
    }

    @Override
    public Iterator<Neuron> getNeurons() {

        return new Iterator<Neuron>() {

            boolean alreadyReturned;

            @Override
            public boolean hasNext() {
                return !alreadyReturned;
            }

            @Override
            public Neuron next() {
                if(alreadyReturned) {
                    return null;
                }
                else {
                    return neuron;
                }

            }
        };

    }

    @Override
    public void initializeLayer() {
        List<Connection> outputNeuronConnections = new ArrayList<Connection>();
        super.getPreviousLayer().getNeurons().forEachRemaining(previousNeuron -> outputNeuronConnections.add(new Connection(previousNeuron, neuron)));
        neuron.setConnections(outputNeuronConnections);
    }
}
