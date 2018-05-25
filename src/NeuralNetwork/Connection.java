package NeuralNetwork;

public class Connection {

    private Neuron sourceNeuron;
    private Neuron destinationNeuron;

    public Connection(Neuron sourceNeuron, Neuron destinationNeuron) {

        this.sourceNeuron = sourceNeuron;
        this.destinationNeuron = destinationNeuron;

    }

}
