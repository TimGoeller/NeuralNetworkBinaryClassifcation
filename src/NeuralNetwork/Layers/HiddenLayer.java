package NeuralNetwork.Layers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import NeuralNetwork.Connection;
import NeuralNetwork.Neurons.HiddenNeuron;
import NeuralNetwork.Neurons.Neuron;

public class HiddenLayer extends Layer<HiddenNeuron> {



    public HiddenLayer(int neuronCount) {
        for(int i = 0; i <= neuronCount; i++) {
            neurons.add(new HiddenNeuron());
        }
    }

    @Override
    public void initializeLayer() {
        for(HiddenNeuron neuron : neurons) {
            List<Connection> hiddenNeuronConnections = new ArrayList<Connection>();
            super.getPreviousLayer().getNeurons().forEachRemaining(previousNeuron -> hiddenNeuronConnections.add(new Connection((Neuron)previousNeuron, neuron)));
            neuron.setConnections(hiddenNeuronConnections);
        }
    }
}
