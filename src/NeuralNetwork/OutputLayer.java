package NeuralNetwork;

public class OutputLayer extends Layer {

    private OutputNeuron neuron;

    @Override
    int getNeuronCount() {
        return 1;
    }
}
