package NeuralNetwork;

import java.util.List;

public class HiddenLayer extends Layer {

    private List<InputNeuron> inputNeurons;

    public HiddenLayer(int neuronCount) {

    }

    @Override
    int getNeuronCount() {
        return inputNeurons.size();
    }
}
