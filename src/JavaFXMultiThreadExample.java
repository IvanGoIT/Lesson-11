import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by baizor on 05.09.17.
 */
public class JavaFXMultiThreadExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();

        final Button button = new Button("text");

        button.setTranslateX(10);
        button.setTranslateY(10);

        root.getChildren().addAll(button);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();


        final Thread thread = new Thread(() -> {
            while(true) {
                final double x = button.getTranslateX() + 2;
                System.out.println("х=" + x);

                Platform.runLater(() -> {
                    button.setTranslateX(x);
                });

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        button.setOnMouseClicked(event -> {
            thread.start();
        });
    }
}
