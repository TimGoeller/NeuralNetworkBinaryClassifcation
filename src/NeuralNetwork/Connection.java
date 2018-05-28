package NeuralNetwork;

import NeuralNetwork.Neurons.Neuron;

public class Connection {

    private Neuron sourceNeuron;
    private Neuron destinationNeuron;

    private double weight = 1;

    public Connection(Neuron sourceNeuron, Neuron destinationNeuron)
    {
        this.sourceNeuron = sourceNeuron;
        this.destinationNeuron = destinationNeuron;
    }

    public Connection(Neuron sourceNeuron, Neuron destinationNeuron, double weight)
    {
        this( sourceNeuron, destinationNeuron );
        this.weight = weight;
    }

    public double getConnectionValue()
    {
        return sourceNeuron.getDestinationValue() * weight;
    }
}
