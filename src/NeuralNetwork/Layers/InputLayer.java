package NeuralNetwork.Layers;

import java.util.ArrayList;
import java.util.Iterator;
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
    public Iterator<Neuron> getNeurons() {

        return new Iterator<Neuron>() {

            int currentIndex;

            @Override
            public boolean hasNext() {
                return currentIndex < inputNeurons.size();
            }

            @Override
            public Neuron next() {

                Neuron neuronToReturn = null;

                if(currentIndex < inputNeurons.size()) {
                    neuronToReturn = inputNeurons.get(currentIndex);
                }

                currentIndex++;
                return neuronToReturn;
            }
        };

    }

    @Override
    public void initializeLayer() {

    }
}
