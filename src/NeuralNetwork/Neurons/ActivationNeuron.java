package NeuralNetwork.Neurons;

import NeuralNetwork.Connection;
import java.util.ArrayList;
import java.util.List;

public abstract class ActivationNeuron extends Neuron {

    private List<Connection> inputConnections = new ArrayList<Connection>();
    private double bias;

    public ActivationNeuron() {

    }

    public void setConnections(List<Connection> connections) {
        this.inputConnections = connections;
    }

}
