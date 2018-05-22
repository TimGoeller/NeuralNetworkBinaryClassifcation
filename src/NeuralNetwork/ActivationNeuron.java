package NeuralNetwork;

import java.util.List;

public class ActivationNeuron extends Neuron{

    private List<Integer> weights;
    private int bias;

    public ActivationNeuron(int previousLayerNeuronCount) {
        bias = 1;

        for(int i = 0; i <= previousLayerNeuronCount; i++) {
            weights.add(1);
        }
    }
}
