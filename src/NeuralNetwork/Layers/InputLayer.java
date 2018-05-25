package NeuralNetwork.Layers;

import java.util.ArrayList;
import java.util.List;

import NeuralNetwork.Neurons.InputNeuron;
import NeuralNetwork.Neurons.Neuron;

public class InputLayer extends Layer {

    private List<InputNeuron> inputNeurons = new ArrayList<InputNeuron>();

    public InputLayer(int inputNeuronCount) {
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
