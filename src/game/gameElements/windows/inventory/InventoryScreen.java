package game.gameElements.windows.inventory;

import game.gameElements.environment.Player;
import game.gameElements.environment.items.Item;
import game.gameElements.windows.fightingscreen.FightWindow;
import game.resources.Resource;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InventoryScreen {

    private InventoryScreen() {
    }

    public static void show() {
        Stage stage = new Stage();
        stage.setTitle("Items");
        stage.setHeight(400);
        stage.setWidth(400);

        GridPane itemPane = new GridPane();
        itemPane.setGridLinesVisible(true);
        itemPane.setStyle("-fx-background-color: black");

        Scene scene = new Scene(itemPane);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        int i = 0;
        for (Item item: Player.getPlayer().getItems().keySet()) {
            ImageView icon = item.getImage();
            Label itemLabel = new Label(item.getName());
            Label itemAmount = new Label("x" + Player.getPlayer().getItems().get(item));
            itemAmount.setFont(Resource.getFont(20));
            itemLabel.setFont(Resource.getFont(20));

            VBox itemBox = new VBox();
            itemBox.setPrefHeight(50);
            itemBox.setPrefWidth(300);
            itemBox.setAlignment(Pos.CENTER);
            itemBox.setStyle("-fx-background-color: #222222");
            itemLabel.setStyle("-fx-text-fill: white");
            itemBox.getChildren().add(itemLabel);

            VBox amountBox = new VBox();
            amountBox.setPrefWidth(50);
            amountBox.setPrefHeight(50);
            amountBox.setAlignment(Pos.CENTER);
            amountBox.setStyle("-fx-background-color: #222222");
            itemAmount.setStyle("-fx-text-fill: white");
            amountBox.getChildren().add(itemAmount);

            itemPane.add(icon, 0, i);
            itemPane.add(itemBox, 1, i);
            itemPane.add(amountBox, 2, i);

            itemBox.setOnMouseClicked(action -> {
                Player.getPlayer().useItem(item);
                stage.close();
                FightWindow.animateText("You used " + item.getName());
            });
            i++;
        }
        stage.show();

    }

}
