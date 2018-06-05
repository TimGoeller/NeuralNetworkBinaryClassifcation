import NeuralNetwork.Dataset;
import NeuralNetwork.NeuralNetwork;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NetworkUi extends Application
{
    public static Scanner in;

    public static void main( String[] args )
    {
        launch( args );


        /*
        Dataset d = Dataset.readDatasetFromCSV("Admission_Dataset_Getrennt.csv");
        NeuralNetwork network = new NeuralNetwork(2, d, 0.05);

        network.addHiddenLayer(4);
        network.addHiddenLayer(5);
        network.addHiddenLayer(4);

        network.initializeLayers();
        network.trainNetwork(4000);

        network.testNetwork();
        */
    }

    @Override public void start(Stage primaryStage)
    {
        Axis axis = new Axis( 400, 300, -8, 8, 1, -6, 6, 1 );
        StackPane layout = new StackPane( axis );
        layout.setPadding( new Insets( 20 ) );
        layout.setStyle( "-fx-background-color: rgb(255, 255, 255);" );
        primaryStage.setTitle( "Neuronal (tm)" );
        primaryStage.setScene( new Scene( layout, ( Color.rgb( 35, 39,50 ) ) ) );
        primaryStage.show();
        /*
        primaryStage.widthProperty().addListener(observable -> drawShapes(gc, canvas, primaryStage));
        primaryStage.heightProperty().addListener(observable -> drawShapes(gc, canvas, primaryStage));
        drawShapes(gc, canvas, primaryStage);
        */
    }

    private void drawShapes(GraphicsContext gc, Canvas c, Stage primaryStage )
    {
        c.setHeight( primaryStage.getHeight() );
        c.setWidth( primaryStage.getWidth() );
        gc.setStroke(new Color( Math.random(),  Math.random(), Math.random(), 1));
        //gc.setStroke(Color.BLUE);
        //gc.setLineWidth(1);
        //gc.strokeRect( 0, 0, primaryStage.getWidth() / 2, primaryStage.getHeight()/2 );
    }
}

class Axis extends Pane
{
    private NumberAxis yAxis;
    private NumberAxis xAxis;

    public Axis( int width, int height, double xLow, double xHigh, double xTickUnit, double yLow, double yHigh, double yTickUnit )
    {
        setMinSize( Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE );
        setPrefSize( width, height );
        setMaxSize( Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE );

        xAxis = new NumberAxis( xLow, xHigh, xTickUnit );
        xAxis.setSide( Side.BOTTOM );
        xAxis.setMinorTickVisible( false );
        xAxis.setPrefWidth( width );
        xAxis.setLayoutY( height / 2 );

        yAxis = new NumberAxis( yLow, yHigh, yTickUnit );
        yAxis.setSide( Side.LEFT );
        yAxis.setMinorTickVisible( false );
        yAxis.setPrefHeight( height );
        yAxis.layoutXProperty().bind( Bindings.subtract( (width/2)+1, yAxis.widthProperty() ) );

        getChildren().setAll( xAxis, yAxis );
    }
}

class Plot extends Pane
{

}