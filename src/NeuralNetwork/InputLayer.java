package NeuralNetwork;

import java.rmi.activation.ActivationID;
import java.util.ArrayList;
import java.util.List;

public class InputLayer extends Layer {

    private List<InputNeuron> inputNeurons;

    InputLayer(int inputNeuronCount) {
        for(int i = 0; i <= inputNeuronCount; i++) {
            inputNeurons.add(new InputNeuron());
        }
    }

    @Override
    int getNeuronCount() {
        return inputNeurons.size();
    }

    @Override
    public List<Neuron> getNeurons() {
        return (List<Neuron>)(List<?>)inputNeurons;
    }

    @Override
    public void initializeLayer() {

    }
}
