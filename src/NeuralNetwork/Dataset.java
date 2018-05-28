package NeuralNetwork;

import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Dataset {

    List<List<Double>> trainingSet;
    List<List<Double>> testSet;

    private Dataset(List<List<Double>> trainingSet, List<List<Double>> testSet) {

        this.trainingSet = trainingSet;
        this.testSet = testSet;
    }

    public static Dataset readDatasetFromCSV(String csvPath) {

        List<List<Double>> data = new ArrayList<>();

        try
        {
            Scanner s = new Scanner( new File(csvPath) );

            NumberFormat format = NumberFormat.getInstance(Locale.GERMAN);


            while( s.hasNextLine() )
            {
                List<Double> line = new ArrayList();
                for( String value : s.nextLine().split( ";" ) )
                {
                    Number number = format.parse(value);
                    line.add( number.doubleValue());
                }
                data.add( line );
            }

        }
        catch (Exception e)
        {
            System.out.println( "Could not open file!" );
        }

        int splitAt = (int)(data.size() * 0.2);

        List<List<Double>> trainingSet = data.subList(0, data.size() - splitAt);
        List<List<Double>> testSet = data.subList( data.size() - splitAt, data.size());

        return new Dataset(trainingSet, testSet);
    }
}
