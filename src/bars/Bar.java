package bars;

import environment.mobs.Mob;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Bar extends ProgressBar {
    protected final Mob mob;
    protected Label status;


    public Bar(Mob mob, double width, int fontSize) {
        this.mob = mob;
        style();
        setPrefWidth(width);

        this.status = new Label(getStatusText(), this);
        Font font = null;
        try {
            font = Font.loadFont(new FileInputStream("src/resources/Pixel_Bold.ttf"), fontSize);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        status.setFont(font);
        status.setTextFill(Color.web("#d4c4be"));
        setProgress(calcNewProgress());
    }

    public Label getStatus() {
        return status;
    }

    public void updateAndAnimate() {
        int weirdAmountOfLoopAccess = 65;
        int duration = 1000;
        double oldValue = this.getProgress();
        double newValue = calcNewProgress();
        double fraction = (newValue - oldValue) / weirdAmountOfLoopAccess;


        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(duration));
            }

            @Override
            protected void interpolate(double v) {
                setProgress(getProgress() + fraction);
            }
        };
        animation.play();

        animation.setOnFinished(e -> {
            setProgress(newValue);
            status.setText(getStatusText());
        });
    }

    protected abstract void style();
    protected abstract double calcNewProgress();
    protected abstract String getStatusText();

}
