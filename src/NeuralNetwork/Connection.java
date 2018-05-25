package NeuralNetwork;

import NeuralNetwork.Neurons.Neuron;

public class Connection {

    private Neuron sourceNeuron;
    private Neuron destinationNeuron;

    public Connection(Neuron sourceNeuron, Neuron destinationNeuron) {

        this.sourceNeuron = sourceNeuron;
        this.destinationNeuron = destinationNeuron;

    }

}
