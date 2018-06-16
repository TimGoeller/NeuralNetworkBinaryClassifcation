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

    private int inputColumnCount;

    private Dataset(List<List<Double>> trainingSet, List<List<Double>> testSet)
    {

        this.trainingSet = trainingSet;
        this.testSet = testSet;
    }

    public static Dataset readDatasetFromCSV(String csvPath, double testingPercentage)
    {
        List<List<Double>> data = new ArrayList<>();

        try {
            Scanner s = new Scanner(new File(csvPath));

            NumberFormat format = NumberFormat.getInstance(Locale.GERMAN);

            while (s.hasNextLine()) {
                List<Double> line = new ArrayList();
                for (String value : s.nextLine().split(";")) {
                    Number number = format.parse(value);
                    line.add(number.doubleValue());
                }
                data.add(line);
            }

        } catch (Exception e) {
            System.out.println("Could not open file!");
            return null;
        }

        normalizeValuesInDataset(data);

        int splitAt = (int) (data.size() * testingPercentage);

        List<List<Double>> trainingSet = data.subList(0, data.size() - splitAt);
        List<List<Double>> testSet = data.subList(data.size() - splitAt, data.size());

        Dataset newSet = new Dataset(trainingSet, testSet);
        newSet.inputColumnCount = data.get(0).size() - 1;

        return newSet;
    }

    private static void normalizeValuesInDataset(List<List<Double>> data)
    {
        int columnCount = data.get(0).size();
        int column = 0;
        while (column < columnCount) {
            double maxima = 0;
            for (List<Double> d : data) {
                maxima = d.get(column) > maxima ? d.get(column) : maxima;
            }

            if (maxima > 1) {
                for (List<Double> d : data) {
                    d.set(column, d.get(column) / maxima);
                }
            }
            column++;
        }
    }

    public int getInputColumnCount()
    {
        return inputColumnCount;
    }

    public List<List<Double>> getTrainingSet()
    {
        return trainingSet;
    }

    public void setTrainingSet(List<List<Double>> trainingSet)
    {
        this.trainingSet = trainingSet;
    }

    public List<List<Double>> getTestSet()
    {
        return testSet;
    }

    public void setTestSet(List<List<Double>> testSet)
    {
        this.testSet = testSet;
    }
}
