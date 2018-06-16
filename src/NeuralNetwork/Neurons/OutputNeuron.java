package NeuralNetwork.Neurons;

import NeuralNetwork.NeuralNetwork;

import java.util.List;

public class OutputNeuron extends ActivationNeuron {

    private double expectedValue;

    public OutputNeuron()
    {
        super();
    }

    public double getExpectedValue()
    {
        return expectedValue;
    }

    public void setExpectedValue(double expectedValue)
    {
        this.expectedValue = expectedValue;
    }

}
