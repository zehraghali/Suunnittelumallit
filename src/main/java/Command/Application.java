import Application.Application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PixelArtEditor extends Application {

    private static final int GRID_SIZE = 8;
    private final boolean[][] pixelGrid = new boolean[GRID_SIZE][GRID_SIZE];
    private int cursorX = 0;
    private int cursorY = 0;
    private GridPane gridPane;

    @Override
    public void start(Stage primaryStage) {
        gridPane = createGrid();

        Button generateCodeButton = new Button("Create Code");
        generateCodeButton.setOnAction(e -> generateCode());

        Scene scene = new Scene(gridPane);
        scene.setOnKeyPressed(this::handleKeyPress);

        gridPane.add(generateCodeButton, 0, GRID_SIZE, GRID_SIZE, 1);

        primaryStage.setTitle("Pixel Art Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
        updateGridDisplay();
    }

    private GridPane createGrid() {
        GridPane gridPane = new GridPane();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Rectangle pixel = new Rectangle(30, 30);
                pixel.setFill(Color.WHITE);
                pixel.setStroke(Color.BLACK);
                gridPane.add(pixel, j, i);
            }
        }
        return gridPane;
    }

    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> moveCursor(-1, 0);
            case DOWN -> moveCursor(1, 0);
            case LEFT -> moveCursor(0, -1);
            case RIGHT -> moveCursor(0, 1);
            case SPACE -> togglePixel();
            default -> {}
        }
    }

    private void moveCursor(int dx, int dy) {
        cursorX = Math.max(0, Math.min(GRID_SIZE - 1, cursorX + dx));
        cursorY = Math.max(0, Math.min(GRID_SIZE - 1, cursorY + dy));
        updateGridDisplay();
    }

    private void togglePixel() {
        pixelGrid[cursorX][cursorY] = !pixelGrid[cursorX][cursorY];
        updateGridDisplay();
    }

    private void updateGridDisplay() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Rectangle pixel = (Rectangle) gridPane.getChildren().get(i * GRID_SIZE + j);
                if (pixelGrid[i][j]) {
                    pixel.setFill(i == cursorX && j == cursorY ? Color.DARKGRAY : Color.BLACK);
                } else {
                    pixel.setFill(i == cursorX && j == cursorY ? Color.LIGHTGRAY : Color.WHITE);
                }
            }
        }
    }

    private void generateCode() {
        System.out.println("int[][] pixelArt = {");
        for (int i = 0; i < GRID_SIZE; i++) {
            System.out.print("    {");
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(pixelGrid[i][j] ? "1" : "0");
                if (j < GRID_SIZE - 1) System.out.print(", ");
            }
            System.out.println("},");
        }
        System.out.println("};");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
