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
    public List<HiddenLayer> hiddenLayers = new ArrayList<HiddenLayer>();
    private OutputLayer outputLayer;
    private double lastTestSuccessRatio;

    private double learningRate;

    private Dataset dataset;

    public NeuralNetwork(int inputNeuronCount, Dataset dataset, double learningRate) {

        inputLayer = new InputLayer(inputNeuronCount);
        outputLayer = new OutputLayer();
        inputLayer.setNextLayer(outputLayer);
        outputLayer.setPreviousLayer(inputLayer);
        this.dataset = dataset;
        this.learningRate = learningRate;
    }

    public void addHiddenLayer(int neuronCount) {

        HiddenLayer hiddenLayer = new HiddenLayer(neuronCount);

        Layer previousLayer;

        if (hiddenLayers.isEmpty()) {
            previousLayer = inputLayer;
        } else {
            previousLayer = hiddenLayers.get(hiddenLayers.size() - 1);
        }

        hiddenLayer.setPreviousLayer(previousLayer);
        previousLayer.setNextLayer(hiddenLayer);
        hiddenLayer.setNextLayer(outputLayer);
        outputLayer.setPreviousLayer(hiddenLayer);

        hiddenLayers.add(hiddenLayer);
    }

    public void trainNetwork(int epochs) {

        while(epochs > 0) {
            epochs --;

            dataset.trainingSet.forEach(data -> train(data));
        }
    }

    private void train(List<Double> data) {

        System.out.println();
        System.out.println("New Training Pass");
        System.out.println();
        System.out.println("X: " + data.get(0));
        System.out.println("Y: " + data.get(1));
        System.out.println();


        int currentNeuron = 0;
        for(InputNeuron neuron : inputLayer.getNeurons()) {
            neuron.setActivationValue(data.get(currentNeuron++));
        }
        outputLayer.getOutputNeuron().setExpectedValue(data.get(currentNeuron));

        Layer currentLayer = inputLayer;

        while(currentLayer.getNextLayer() != null) {
            currentLayer = currentLayer.getNextLayer();
            forwardPass(currentLayer );
        }

        currentLayer = outputLayer;

        while(currentLayer.getPreviousLayer() != null) { //Input connections to next layer are changed
            backwardPass(currentLayer);
            currentLayer = currentLayer.getPreviousLayer();
        }
    }

    public void testNetwork()
    {
        int match = 0;
        for( int counter = 0; counter < dataset.testSet.size(); counter++ )
        {
            if( test( dataset.testSet.get( counter ) ) ) match++;
        }

        System.out.println( match + " von " + dataset.testSet.size() + " wurden richtig bestimmt." );
        lastTestSuccessRatio = (double) match / (double) dataset.testSet.size();
        System.out.println( "Korrektheitsrate: " + lastTestSuccessRatio * 100 + "%");
    }

    public boolean test(List<Double> data) {
        int match = 0;
        System.out.println();
        System.out.println("New Test Pass");
        System.out.println();
        System.out.println("X: " + data.get(0));
        System.out.println("Y: " + data.get(1));
        System.out.println();

        int currentNeuron = 0;
        for(InputNeuron neuron : inputLayer.getNeurons()) {
            neuron.setActivationValue(data.get(currentNeuron++));
        }
        outputLayer.getOutputNeuron().setExpectedValue(data.get(currentNeuron));

        Layer currentLayer = inputLayer;
        while(currentLayer.getNextLayer() != null) {
            currentLayer = currentLayer.getNextLayer();
            forwardPass(currentLayer );
        }
        System.out.println( "Result: " + outputLayer.getOutputNeuron().getActivationValue() );
        System.out.println( "Real value:" + outputLayer.getOutputNeuron().getExpectedValue() );
        //Return TRUE if guess was correct
        if( Math.round(outputLayer.getOutputNeuron().getActivationValue()) != data.get( data.size() - 1 ) )
        {
            System.out.println( "ERROR404: X:" + data.get(0) + " Y:" + data.get(1) );
        }
        return (Math.round( outputLayer.getOutputNeuron().getActivationValue() ) == data.get( data.size() - 1 ) );

    }

    private void forwardPass(Layer layer) {

        Iterator<Neuron> it = layer.getNeuronsAsIterator();
        while(it.hasNext()) {
            ActivationNeuron currentNeuron = (ActivationNeuron)it.next();
            currentNeuron.sigmoidActivation();
        }
    }

    private void backwardPass(Layer layer) {

        Iterator<Neuron> it = layer.getNeuronsAsIterator();
        while(it.hasNext()) {
            ActivationNeuron currentNeuron = (ActivationNeuron)it.next();
            if(currentNeuron instanceof OutputNeuron) {
                double y = ((OutputNeuron) currentNeuron).getExpectedValue();
                double a = currentNeuron.getActivationValue();
                if(a == 1) {
                    a = a-0.00000001; //Teilen: The way to go
                }

                double addedValues = 0;
                for(int i = 0; i < inputLayer.getNeurons().size(); i++) {
                    addedValues += inputLayer.getNeurons().get(i).getActivationValue()*(a-y);
                }

                double error = -(1.0f/inputLayer.getNeurons().size())*addedValues;
                currentNeuron.setError(error);


                System.out.println("PREDICTION: " + currentNeuron.getActivationValue());
                System.out.println("REAL VAL: " + ((OutputNeuron) currentNeuron).getExpectedValue());
                System.out.println("Error: " + error);


            }
            else {
                double errorSum = 0;

                for(Connection con : currentNeuron.getOutputConnections()) {
                    //System.out.println(con.getWeight() * ((ActivationNeuron)con.getDestinationNeuron()).getActivationValue());
                    errorSum += con.getWeight() * ((ActivationNeuron) con.getDestinationNeuron()).getError();
                }
                double errorMultipliedSigmoidDerivative = errorSum * (currentNeuron.getActivationValue() * (1-currentNeuron.getActivationValue()));
                currentNeuron.setError(errorMultipliedSigmoidDerivative);
                //System.out.println("Propagated Error: " + errorMultipliedSigmoidDerivative);
            }
            //currentNeuron.ge

            for(Connection con : currentNeuron.getInputConnections()) {
                //System.out.println();
                double change = con.getSourceNeuron().getActivationValue() * currentNeuron.getError() * learningRate;
                con.setWeight(con.getWeight() + change);
            }
            double change = currentNeuron.getError() * learningRate;
            currentNeuron.setBias(currentNeuron.getBias() + change);
        }

    }

    public void initializeLayers() {

        Iterator<Layer> layerIt = getLayersAsIterator();

        while(layerIt.hasNext()) {
            layerIt.next().initializeLayer();
        }
    }

    public Iterator<Layer> getLayersAsIterator() {
        return new Iterator<Layer>() {

            private Layer currentLayer;

            @Override
            public boolean hasNext() {
                return !(currentLayer instanceof OutputLayer);
            }

            @Override
            public Layer next() {
                if(currentLayer == null)
                    currentLayer = inputLayer;
                else
                    currentLayer = currentLayer.getNextLayer();
                return currentLayer;
            }
        };
    }

    public double getLastTestSuccessRatio() {
        return lastTestSuccessRatio;
    }


}