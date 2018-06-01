package NeuralNetwork.Layers;

import NeuralNetwork.Connection;
import NeuralNetwork.Neurons.Neuron;
import NeuralNetwork.Neurons.OutputNeuron;

import java.util.ArrayList;
import java.util.List;

public class OutputLayer extends Layer<OutputNeuron> {

    public OutputLayer() {
        neurons.add(new OutputNeuron());
    }

    @Override
    public void initializeLayer() {
        List<Connection> outputNeuronConnections = new ArrayList<Connection>();
        super.getPreviousLayer().getNeuronsAsIterator().forEachRemaining(previousNeuron -> outputNeuronConnections.add(new Connection((Neuron)previousNeuron, neurons.get(0))));
        getOutputNeuron().setConnections(outputNeuronConnections);

        getOutputNeuron().getInputConnections().forEach(connection -> connection.getSourceNeuron().addOutputConnection(connection));
    }

    public OutputNeuron getOutputNeuron() {
        return neurons.get(0);
    }
}
