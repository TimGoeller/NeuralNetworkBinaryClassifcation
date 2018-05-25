package NeuralNetwork.Layers;

import NeuralNetwork.Connection;
import NeuralNetwork.Neurons.Neuron;
import NeuralNetwork.Neurons.OutputNeuron;

import java.util.ArrayList;
import java.util.List;

public class OutputLayer extends Layer {

    private OutputNeuron neuron;

    public OutputLayer() {
        neuron = new OutputNeuron();
    }

    @Override
    int getNeuronCount() {
        return 1;
    }

    @Override
    public List<Neuron> getNeurons() {
        List<Neuron> neurons = new ArrayList<Neuron>();
        neurons.add(neuron);
        return neurons;
    }

    @Override
    public void initializeLayer() {
        List<Connection> outputNeuronConnections = new ArrayList<Connection>();
        for(Neuron previousLayerNeuron : super.getPreviousLayer().getNeurons()) {
            outputNeuronConnections.add(new Connection(previousLayerNeuron, neuron));
        }
        neuron.setConnections(outputNeuronConnections);
    }
}
