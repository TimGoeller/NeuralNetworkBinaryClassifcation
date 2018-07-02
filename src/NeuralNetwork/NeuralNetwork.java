package NeuralNetwork;

import NeuralNetwork.Layers.HiddenLayer;
import NeuralNetwork.Layers.InputLayer;
import NeuralNetwork.Layers.Layer;
import NeuralNetwork.Layers.OutputLayer;
import NeuralNetwork.Neurons.ActivationNeuron;
import NeuralNetwork.Neurons.InputNeuron;
import NeuralNetwork.Neurons.Neuron;
import NeuralNetwork.Neurons.OutputNeuron;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NeuralNetwork { //static?

    private InputLayer inputLayer;
    private List<HiddenLayer> hiddenLayers = new ArrayList<HiddenLayer>();
    private OutputLayer outputLayer;
    private double lastTestSuccessRatio;
    private double learningRate;
    private Dataset dataset;

    public NeuralNetwork(Dataset dataset, double learningRate)
    {
        inputLayer = new InputLayer(dataset.getInputColumnCount());
        outputLayer = new OutputLayer();
        inputLayer.setNextLayer(outputLayer);
        outputLayer.setPreviousLayer(inputLayer);
        this.dataset = dataset;
        this.learningRate = learningRate;
    }

    public void addHiddenLayer(int neuronCount)
    {
        HiddenLayer hiddenLayer = new HiddenLayer(neuronCount);

        Layer previousLayer = hiddenLayers.isEmpty() ? inputLayer : hiddenLayers.get(hiddenLayers.size() - 1);

        hiddenLayer.setPreviousLayer(previousLayer);
        previousLayer.setNextLayer(hiddenLayer);
        hiddenLayer.setNextLayer(outputLayer);
        outputLayer.setPreviousLayer(hiddenLayer);

        hiddenLayers.add(hiddenLayer);
    }

    public void trainNetwork(int epochs)
    {
        while (epochs-- > 0) {
            dataset.trainingSet.forEach(this::train);
        }
    }

    private void train(List<Double> data)
    {
        inputLayer.getNeurons().forEach(inputNeuron -> inputNeuron.setActivationValue(data.get(inputLayer.getNeurons().indexOf(inputNeuron))));

        outputLayer.getOutputNeuron().setExpectedValue(data.get(inputLayer.getNeurons().size()));

        Layer currentLayer = inputLayer;

        while (currentLayer.getNextLayer() != null) {
            currentLayer = currentLayer.getNextLayer();
            forwardPass(currentLayer);
        }

        currentLayer = outputLayer;

        while (currentLayer.getPreviousLayer() != null) { //Input connections to next layer are changed
            backwardPass(currentLayer);
            currentLayer = currentLayer.getPreviousLayer();
        }
    }

    public void testNetwork()
    {
        int match = 0;
        for (int counter = 0; counter < dataset.testSet.size(); counter++) {
            if (test(dataset.testSet.get(counter))) match++;
        }

        System.out.println(match + " von " + dataset.testSet.size() + " wurden richtig bestimmt.");
        lastTestSuccessRatio = (double) match / (double) dataset.testSet.size();
    }

    private boolean test(List<Double> data)
    {
        inputLayer.getNeurons().forEach(inputNeuron -> inputNeuron.setActivationValue(data.get(inputLayer.getNeurons().indexOf(inputNeuron))));

        Layer currentLayer = inputLayer;

        while (currentLayer.getNextLayer() != null) {
            currentLayer = currentLayer.getNextLayer();
            forwardPass(currentLayer);
        }

        return (Math.round(outputLayer.getOutputNeuron().getActivationValue()) == data.get(data.size() - 1));
    }

    private void forwardPass(Layer layer)
    {
        for(Object neuron : layer) {
            ((ActivationNeuron) neuron).sigmoidActivation();
        }
    }

    private void backwardPass(Layer layer)
    {
        Iterator it =layer.getNeuronsAsIterator();

        for(Object neuron : layer) {
            ActivationNeuron currentNeuron = (ActivationNeuron) neuron;

            if (currentNeuron instanceof OutputNeuron) {
                //Cross Entropy
                double expectedValue = ((OutputNeuron) currentNeuron).getExpectedValue();
                double activationValue = currentNeuron.getActivationValue();

                double crossEntropySum = 0;
                for (int i = 0; i < inputLayer.getNeurons().size(); i++) {
                    crossEntropySum += inputLayer.getNeurons().get(i).getActivationValue() * (activationValue - expectedValue);
                }

                double error = -(1.0f / inputLayer.getNeurons().size()) * crossEntropySum;
                currentNeuron.setError(error);
            } else {
                //Backpropagate error
                double errorSum = 0;

                for (Connection con : currentNeuron.getOutputConnections()) {
                    errorSum += con.getWeight() * ((ActivationNeuron) con.getDestinationNeuron()).getError();
                }

                double errorMultipliedSigmoidDerivative = errorSum * (currentNeuron.getActivationValue() * (1 - currentNeuron.getActivationValue()));
                currentNeuron.setError(errorMultipliedSigmoidDerivative);
            }

            //Change weights and biases
            for (Connection con : currentNeuron.getInputConnections()) {
                double weightChange = con.getSourceNeuron().getActivationValue() * currentNeuron.getError() * learningRate;
                con.setWeight(con.getWeight() + weightChange);
            }

            double biasChange = currentNeuron.getError() * learningRate; //Activation value of 1, essentially just another weight
            currentNeuron.setBias(currentNeuron.getBias() + biasChange);
        }
    }

    public void initializeLayers()
    {
        Layer currentLayer = inputLayer;

        while(currentLayer != null) {
            currentLayer.initializeLayer();
            currentLayer = currentLayer.getNextLayer();
        }
    }

    public double getLastTestSuccessRatio()
    {
        return lastTestSuccessRatio;
    }
}