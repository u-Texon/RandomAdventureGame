package game.gameElements.system;

public class Settings {
    public static final double MIN_WIDTH = 192;
    public static final double MIN_HEIGHT = 108;

    private double scaleX = 1;
    private double scaleY = 1;
    private boolean isFullscreen = false;
    private Settings instance = null;

    private Settings() {

    }

    public Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
            return instance;
    }

    public double getScaleX() {
        return scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public boolean isFullscreen() {
        return isFullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        isFullscreen = fullscreen;
    }

    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }
}
