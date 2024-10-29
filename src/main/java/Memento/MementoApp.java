package Memento;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class MementoApp extends Application {
    private Caretaker caretaker = new Caretaker();
    private Color color1 = Color.RED;
    private Color color2 = Color.GREEN;
    private Color color3 = Color.BLUE;
    private boolean checkboxStatus = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox, 400, 300);

        Button historyButton = new Button("Show History");
        historyButton.setOnAction(e -> showHistory());

        vbox.getChildren().add(historyButton);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Memento Pattern Example");
        primaryStage.show();
    }

    private void showHistory() {
        Stage historyStage = new Stage();
        ListView<String> historyList = new ListView<>();

        for (Memento memento : caretaker.getUndoStack()) {
            historyList.getItems().add(memento.getTimestamp() + ": " + memento.getColor1() + ", " + memento.getColor2() + ", " + memento.getColor3() + ", " + memento.getCheckboxStatus());
        }

        historyList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                int index = historyList.getSelectionModel().getSelectedIndex();
                Memento selectedState = caretaker.getUndoStack().get(index); // Varmista, että get() toimii
                restoreState(selectedState);
            }
        });

        historyStage.setScene(new Scene(historyList, 400, 300));
        historyStage.setTitle("History");
        historyStage.show();
    }

    private void restoreState(Memento memento) {
        this.color1 = memento.getColor1();
        this.color2 = memento.getColor2();
        this.color3 = memento.getColor3();
        this.checkboxStatus = memento.getCheckboxStatus();
    }
}
