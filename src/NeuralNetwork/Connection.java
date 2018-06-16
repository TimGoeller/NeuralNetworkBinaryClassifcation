package NeuralNetwork;

import NeuralNetwork.Neurons.ActivationNeuron;
import NeuralNetwork.Neurons.Neuron;

public class Connection {

    private Neuron sourceNeuron;
    private Neuron destinationNeuron;

    private double weight = Math.random();
    private double gradientCheckingWeight;

    public double getWeight()
    {
        return weight;
    }

    public double getGradientCheckingWeight()
    {
        return gradientCheckingWeight;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
        this.gradientCheckingWeight = weight;
    }

    public void resetGradientCheckingWeight()
    {
        this.gradientCheckingWeight = weight;
    }

    public void setGradientCheckingWeight(double weight)
    {
        this.gradientCheckingWeight = weight;
    }

    public Connection(Neuron sourceNeuron, Neuron destinationNeuron)
    {
        this.sourceNeuron = sourceNeuron;
        this.destinationNeuron = destinationNeuron;
    }

    public Connection(Neuron sourceNeuron, Neuron destinationNeuron, double weight)
    {
        this(sourceNeuron, destinationNeuron);
        this.weight = weight;
    }

    public double getConnectionValue()
    {
        return (sourceNeuron).getActivationValue() * weight;
    }

    public Neuron getSourceNeuron()
    {
        return sourceNeuron;
    }

    public Neuron getDestinationNeuron()
    {
        return destinationNeuron;
    }
}
