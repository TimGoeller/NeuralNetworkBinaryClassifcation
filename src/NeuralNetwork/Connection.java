package NeuralNetwork;

import NeuralNetwork.Neurons.ActivationNeuron;
import NeuralNetwork.Neurons.Neuron;

public class Connection {

    private Neuron sourceNeuron;
    private Neuron destinationNeuron;

    private double weight = Math.random();

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

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
        return (sourceNeuron).getActivationValue()* weight;
    }

    public Neuron getSourceNeuron() {
        return sourceNeuron;
    }

    public Neuron getDestinationNeuron() {
        return destinationNeuron;
    }
}
