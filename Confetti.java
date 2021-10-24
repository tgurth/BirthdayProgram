package BirthdayTemplate;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class Confetti {

    private Pane birthdayPane;
    private Pane confettiHolder;
    private Rectangle confetti;

    public Confetti(Pane confettiPane) {
        this.birthdayPane = confettiPane;
        this.confettiHolder = new Pane();

        this.create();
        this.setTimeline();
    }

    public void create() {

        Random rand = new Random();

        int index = rand.nextInt(Constants.CONFETTI_COLORS.length - 1);

        this.confetti = new Rectangle(4, 3, Constants.CONFETTI_COLORS[index]);
        this.confetti.setRotate(Math.random() * 30);
        this.confetti.setX(Math.random() * 500);
        this.confetti.setY(-30);
        this.confettiHolder.getChildren().add(this.confetti);
        this.birthdayPane.getChildren().add(this.confettiHolder);
    }

    public void setTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(0.008), (e) -> this.moveConfetti());
        Timeline tm = new Timeline(kf);
        tm.setCycleCount(Animation.INDEFINITE);
        tm.play();

    }

    public void moveConfetti() {

        double rotFactor;

        if (Math.random() <= 0.5) {
            rotFactor = 5;
        } else {
            rotFactor = -5;
        }

        if (this.confetti.getY() < 400) {
            this.confetti.setY(this.confetti.getY() + 0.4);
            this.confetti.setRotate(this.confetti.getRotate() + rotFactor);

        } else {
            this.birthdayPane.getChildren().remove(this.confettiHolder);
            this.confettiHolder = null;
        }
    }
}
