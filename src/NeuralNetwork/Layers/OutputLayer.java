package NeuralNetwork.Layers;

import NeuralNetwork.Connection;
import NeuralNetwork.Neurons.Neuron;
import NeuralNetwork.Neurons.OutputNeuron;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OutputLayer extends Layer<OutputNeuron> {



    public OutputLayer() {
        neurons.add(new OutputNeuron());
    }


    @Override
    public void initializeLayer() {
        List<Connection> outputNeuronConnections = new ArrayList<Connection>();
        super.getPreviousLayer().getNeurons().forEachRemaining(previousNeuron -> outputNeuronConnections.add(new Connection((Neuron)previousNeuron, neurons.get(0))));
        neurons.get(0).setConnections(outputNeuronConnections);
    }
}
