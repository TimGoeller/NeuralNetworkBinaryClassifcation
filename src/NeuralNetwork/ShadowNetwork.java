package NeuralNetwork;

import NeuralNetwork.Layers.HiddenLayer;
import NeuralNetwork.Layers.InputLayer;
import NeuralNetwork.Layers.Layer;
import NeuralNetwork.Layers.OutputLayer;
import NeuralNetwork.Neurons.ActivationNeuron;
import NeuralNetwork.Neurons.Neuron;
import NeuralNetwork.Neurons.OutputNeuron;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShadowNetwork
{
    private InputLayer inputLayer;
    public  List<HiddenLayer> hiddenLayers = new ArrayList<HiddenLayer>();
    private OutputLayer outputLayer;

    public ShadowNetwork(int inputLayerNeuronCount, List<Integer> hiddenLayerNeuronCount)
    {
        inputLayer = new InputLayer(inputLayerNeuronCount);
        Layer prevLayer = inputLayer;

        for ( int hiddenLayerNeuronCounter : hiddenLayerNeuronCount )
        {
            HiddenLayer newHiddenLayer = new HiddenLayer( hiddenLayerNeuronCounter );
            prevLayer.setNextLayer( newHiddenLayer );
            newHiddenLayer.setPreviousLayer( prevLayer );
            prevLayer = newHiddenLayer;
            hiddenLayers.add( newHiddenLayer );
        }

        outputLayer = new OutputLayer();
        outputLayer.setPreviousLayer( prevLayer );
        prevLayer.setNextLayer( outputLayer );

        initializeLayers();
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

    public void synchronize(NeuralNetwork network)
    {
        Iterator<Layer> iterator = network.getLayersAsIterator();
        Iterator<Layer> shadowIterator = getLayersAsIterator();

        while( iterator.hasNext() )
        {
            int neuronIndex = 0;
            Layer shadowLayer = shadowIterator.next();
            for(Neuron e : (List<Neuron>) iterator.next().getNeurons() )
            {
                int connectionIndex = 0;
                for (Connection c : e.getOutputConnections())
                {
                    ((Neuron)shadowLayer.getNeurons().get( neuronIndex )).getOutputConnections().get( connectionIndex ).setWeight( c.getWeight() );
                    connectionIndex++;
                }
                if(e instanceof ActivationNeuron)
                {
                    ((ActivationNeuron)shadowLayer.getNeurons().get( neuronIndex )).setBias( ((ActivationNeuron) e).getBias() );
                }
                neuronIndex++;
            }
        }
    }
}
