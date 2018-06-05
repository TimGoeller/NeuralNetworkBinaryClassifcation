import NeuralNetwork.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static Scanner in = new Scanner( System.in );

    public static void main(String args[]) {

        Dataset d = Dataset.readDatasetFromCSV("Admission_Dataset_Getrennt.csv");
        NeuralNetwork network = new NeuralNetwork(2, d, 0.05);

        network.addHiddenLayer(4);
        network.addHiddenLayer(5);
        network.addHiddenLayer(4);

        network.initializeLayers();
        network.trainNetwork(4000);

        network.testNetwork();

        int input = 0;
        boolean running = true;
        while( running )
        {
            System.out.println( "Please choose option (1)train (0)exit." );
            input = in.nextInt();
            switch( input )
            {
                case 0:
                    running = false;
                    break;
                case 1:
                    System.out.print( "X: " );
                    double x = in.nextDouble();
                    System.out.print( "Y: " );
                    double y = in.nextDouble();
                    System.out.print( "Output: " );
                    double output = in.nextInt();
                    List<Double> data = new ArrayList<Double>();
                    data.add( x );
                    data.add( y );
                    data.add( output );
                    network.test(data);
                    break;
            }


        }
    }
}
