package NeuralNetwork.Neurons;

import NeuralNetwork.Connection;
import java.util.ArrayList;
import java.util.List;

public abstract class ActivationNeuron extends Neuron {

    private List<Connection> inputConnections = new ArrayList<Connection>();
    private double bias;
    private double destinationValue;

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

    public double sigmoidActivation()
    {
        double x = 0;
        return destinationValue = 1 / ( 1 +  Math.pow( Math.E, -x ) );
    }

    public  double getValueOfInputConnections()
    {
        double sum = 0;
        for ( Connection c : inputConnections )
        {
            sum += c.getConnectionValue();
        }
        return sum;
    }
}
