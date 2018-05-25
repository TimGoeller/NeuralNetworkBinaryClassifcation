package NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class HiddenLayer extends Layer {

    private List<HiddenNeuron> neurons = new ArrayList<HiddenNeuron>();

    public HiddenLayer(int neuronCount) {
        for(int i = 0; i <= neuronCount; i++) {
            neurons.add(new HiddenNeuron());
        }
    }

    @Override
    int getNeuronCount() {
        return neurons.size();
    }

    @Override
    public List<Neuron> getNeurons() {
        return (List<Neuron>)(List<?>)neurons;
    }

    @Override
    public void initializeLayer() {
        for(HiddenNeuron neuron : neurons) {
            List<Connection> hiddenNeuronConnections = new ArrayList<Connection>();
            for(Neuron previousLayerNeuron : super.getPreviousLayer().getNeurons()) {
                hiddenNeuronConnections.add(new Connection(previousLayerNeuron, neuron));
            }
            neuron.setConnections(hiddenNeuronConnections);
        }
    }
}
