package NeuralNetwork;

import java.util.List;

public class InputLayer extends Layer {

    private List<InputNeuron> inputNeurons;

    public InputLayer(int inputNeuronCount) {

    }

    @Override
    int getNeuronCount() {
        return inputNeurons.size();
    }
}
