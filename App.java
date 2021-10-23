package BirthdayTemplate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    Scene scene1;
    Stage window;

    @Override
    public void start(Stage primaryStage) {
        this.window = primaryStage;
        PaneOrganizer organizer = new PaneOrganizer(this.window);
        this.scene1 = new Scene(organizer.getRoot(), 500, 400);
        this.window.setScene(this.scene1);
        this.window.setTitle(Constants.TITLE);
        this.window.show();
    }

    public static void main(String[] argv) {
        launch(argv);
    }
}
