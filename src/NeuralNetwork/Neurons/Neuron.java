package NeuralNetwork.Neurons;

public abstract class Neuron
{
    private double activationValue;

    public void setActivationValue( double activationValue )
    {
        this.activationValue = activationValue;
    }

    public double getActivationValue()
    {
        return activationValue;
    }
}
