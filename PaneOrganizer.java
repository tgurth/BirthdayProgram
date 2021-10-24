package BirthdayTemplate;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class PaneOrganizer {
    private VBox root;
    private Button startButton;
    private TextField enterPass;
    private Label myLabel;
    private Stage mainStage;
    private VBox birthdayRoot;
    private ImageView cake;
    private boolean foo = false;
    private Pane confettiPane;


    public PaneOrganizer(Stage stage) {
        this.birthdayRoot = new VBox();
        this.confettiPane = new Pane();

        this.birthdayRoot.getChildren().add(confettiPane);
        this.mainStage = stage;
        this.root = new VBox();
        this.myLabel = new Label(Constants.HINT);
        this.startButton = new Button("Enter");
        this.root.setAlignment(Pos.CENTER);
        this.enterPass = new TextField();
        this.enterPass.setMaxSize(100, 10);
        this.enterPass.focusedProperty();

        this.startButton.setOnAction((ActionEvent event) -> this.validate());

        this.root.getChildren().addAll(this.myLabel, this.enterPass, this.startButton);
    }

    public VBox getRoot() {
        return this.root;
    }

    public boolean correctPass() {
        Boolean foo = false;

        if (this.enterPass.getText().equalsIgnoreCase(Constants.PASSWORD)) {
            foo = true;
        }
        return foo;
    }

    public void validate() {
        if (correctPass()) {
            this.mainStage.setScene(genAccessedScene());
        } else {
            Label myLabel2 = new Label(Constants.WRONG);
            myLabel2.setTextFill(Color.RED);
            if (this.root.getChildren().size() < 4) {
                this.root.getChildren().add(myLabel2);
            }
        }
    }

    public Scene genAccessedScene() {
        Scene accessedScene = new Scene(getBirthdayRoot(), 500, 400);

        return accessedScene;
    }

    public VBox getBirthdayRoot() {
        Label birthdayLabel = new Label("Happy Birthday!");
        birthdayLabel.setStyle("-fx-font-size: 30");
        String source = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Cartoon_Happy_Birthday_Cake.svg/2048px-Cartoon_Happy_Birthday_Cake.svg.png";
        this.cake = new ImageView(new Image(source));
        this.cake.setFitWidth(200);
        this.cake.setPreserveRatio(true);
        this.cake.setSmooth(true);
        this.cake.setCache(true);

        Label message = new Label(Constants.MESSAGE);
        message.setStyle("-fx-font-size: 20");

        Label from = new Label(Constants.FROM);
        Label margin = new Label(" ");
        margin.setStyle("-fx-font-size: 70");


        this.birthdayRoot.getChildren().addAll(birthdayLabel, this.cake, message, from, margin);
        this.birthdayRoot.setStyle(Constants.BACKGROUND_COLOR);
        this.birthdayRoot.setAlignment(Pos.CENTER);
        createTimeLine();

        return this.birthdayRoot;
    }

    /**
     * Method to create the timeline the animation plays off of
     */
    public void createTimeLine() {
        KeyFrame kf = new KeyFrame(Duration.seconds(0.01), (e) -> this.animateCake());

        Timeline tm = new Timeline(kf);
        tm.setCycleCount(Animation.INDEFINITE);
        tm.play();

        KeyFrame kfStreamers = new KeyFrame(Duration.seconds(0.03), (e) -> this.animateStreamers());

        Timeline tmStreamers = new Timeline(kfStreamers);
        tmStreamers.setCycleCount(500);
        tmStreamers.play();
    }

    /**
     * Method to make the cake rotate back and forth, changing direction every time
     * it reaches a rotation of 10 in either direction
     */
    public void animateCake() {
        if (Math.abs(this.cake.getRotate() - 10) <= 0.0001) {
            this.foo = true;
        } else if (Math.abs(this.cake.getRotate() + 10) <= 0.0001) {
            this.foo = false;
        }

        if (this.foo) {
            this.cake.setRotate(this.cake.getRotate() - 0.1);
        } else {
            this.cake.setRotate(this.cake.getRotate() + 0.1);
        }
    }

    /**
     * Method used to animate the streamers and confetti that will fall from the top of the screen.
     */
    public void animateStreamers() {
        new Confetti(this.confettiPane);
    }
}
