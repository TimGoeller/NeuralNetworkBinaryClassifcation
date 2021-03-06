package NeuralNetwork.Neurons;

import NeuralNetwork.Connection;

import java.util.ArrayList;
import java.util.List;

public abstract class ActivationNeuron extends Neuron {

    private List<Connection> inputConnections = new ArrayList<>();

    protected double inputValue;

    private double bias;

    private double error;

    public double getError()
    {
        return error;
    }

    public void setError(double error)
    {
        this.error = error;
    }

    public void setInputConnections(List<Connection> connections)
    {
        this.inputConnections = connections;
    }

    public List<Connection> getInputConnections()
    {
        return inputConnections;
    }

    public double getBias()
    {
        return bias;
    }

    public void setBias(double bias)
    {
        this.bias = bias < -1.0 ? -1.0 : bias > 1.0 ? 1.0 : bias;
    }

    public void sigmoidActivation()
    {
        //System.out.println(1 +  Math.exp(-getValueOfInputConnections() ));
        activationValue = 1 / (1 + Math.exp(-(getValueOfInputConnections() + bias)));

    }

    public double getValueOfInputConnections()
    {
        double sum = 0;
        for (Connection c : inputConnections) {
            sum += c.getConnectionValue();
        }
        inputValue = sum;

        return sum;
    }

    public double getInputValue()
    {
        return inputValue;
    }

    public void setInputValue(double inputValue)
    {
        this.inputValue = inputValue;
    }
}
