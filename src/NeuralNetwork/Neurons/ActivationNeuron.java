package NeuralNetwork.Neurons;

import NeuralNetwork.Connection;
import java.util.ArrayList;
import java.util.List;

public abstract class ActivationNeuron extends Neuron {

    private List<Connection> inputConnections = new ArrayList<Connection>();
    private double bias;

    private double activationValue;

    public ActivationNeuron() {

    }

    public void setConnections(List<Connection> connections) {
        this.inputConnections = connections;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias)
    {
        if( bias < -1.0 ) bias = -1.0;
        if( bias > 1.0 ) bias = 1.0;
        this.bias = bias;
    }

    public void sigmoidActivation()
    {
        destinationValue = 1 / ( 1 +  Math.pow( Math.E, -getValueOfInputConnections() ) );
    }

    public double getValueOfInputConnections()
    {
        double sum = 0;
        for ( Connection c : inputConnections )
        {
            sum += c.getConnectionValue();
        }
        activationValue = sum;
        return sum;
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
