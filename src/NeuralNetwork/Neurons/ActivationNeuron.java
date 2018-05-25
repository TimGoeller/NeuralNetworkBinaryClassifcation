package NeuralNetwork.Neurons;

import NeuralNetwork.Connection;
import java.util.ArrayList;
import java.util.List;

public class ActivationNeuron extends Neuron {

    List<Connection> inputConnections = new ArrayList<Connection>();
    double bias;

    public ActivationNeuron() {

    }

    public void setConnections(List<Connection> connections) {
        this.inputConnections = connections;
    }

}
