package edu.fiuba.algoChess.interfaz.vista;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AlgoChessApp extends Application implements EventHandler<ActionEvent> {


    private PieceView player;
    private MapView mapView;
    private Inicio inicio;
    private Button button;

    @Override
    public void start(Stage firstStage) {
        firstStage.setTitle("AlgoChess");
        inicio = new Inicio(firstStage);

        button = new Button();
        button.setText("Soy un boton");

        button.setOnAction(this);

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(inicio, Color.BLACK);
        firstStage.setScene(scene);
        firstStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource() == button){
            System.out.println("log de consola");
        }
    }
}