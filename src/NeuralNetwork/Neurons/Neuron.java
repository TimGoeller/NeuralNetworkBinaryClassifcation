package NeuralNetwork.Neurons;

import NeuralNetwork.Connection;

import java.util.ArrayList;
import java.util.List;

public abstract class Neuron
{
    protected double activationValue;

    private List<Connection> outputConnections = new ArrayList<Connection>();

    public void addOutputConnection(Connection connection) {
        this.outputConnections.add(connection);
    }

    public List<Connection> getOutputConnections() {
        return outputConnections;
    }

    public void setActivationValue(double activationValue)
    {
        this.activationValue = activationValue;
    }

    public double getActivationValue()
    {
        return activationValue;
    }
}
