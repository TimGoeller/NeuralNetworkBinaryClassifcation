package NeuralNetwork.Layers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import NeuralNetwork.Neurons.InputNeuron;
import NeuralNetwork.Neurons.Neuron;

public class InputLayer extends Layer<InputNeuron>{

    public InputLayer(int inputNeuronCount) {
        for(int i = 0; i <= inputNeuronCount; i++) {
            neurons.add(new InputNeuron());
        }
    }

    @Override
    public void initializeLayer() {

    }
}
