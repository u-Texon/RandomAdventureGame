package game.gameElements.windows.shop;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Shop {
    private GridPane parent;

    public Shop() {
        initParent();
    }
    
    private void initParent() {
        parent = new GridPane();
    }

    public void goToShop(Stage stage) {
        stage.setTitle("Shop");

        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }
}
