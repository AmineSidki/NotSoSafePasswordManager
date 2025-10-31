package org.aminesidki.notsosafepasswordmanager.util;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.aminesidki.notsosafepasswordmanager.HelloApplication;
import org.aminesidki.notsosafepasswordmanager.controller.NewPasswordPopupController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class GeneralUtils {

    public static void reloadPasswordsContainer(VBox container){
        container.getChildren().clear();
        final HashMap<String,String> passwords;

        try {
            passwords = DatabaseUtils.fetch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for(String key : passwords.keySet()){
            HBox passwordContainer = new HBox();

            Text passKey = new Text(key);
            passKey.setFill(Color.GREEN);
            passKey.setWrappingWidth(100);

            VBox keySizeControl = new VBox();
            keySizeControl.getChildren().add(passKey);
            keySizeControl.setMaxWidth(100);
            keySizeControl.setMinWidth(100);
            keySizeControl.setAlignment(Pos.CENTER);

            Button delete = new Button("-");
            delete.setStyle("-fx-background-color: #ffb6b6;-fx-text-fill: #c70909");
            delete.setOnAction((event->{
                try {
                    DatabaseUtils.delete(key);
                    reloadPasswordsContainer(container);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }));

            Button copyToClipboard = new Button("Copy");
            copyToClipboard.setStyle("-fx-background-color: #d1e0ff;-fx-text-fill: #0033a1");
            copyToClipboard.setOnAction(event->{
                Clipboard cb = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(passwords.get(key));
                cb.setContent(content);
                copyToClipboard.setStyle("-fx-background-color: #a5ffba;-fx-text-fill: #0ea22e");
            });

            Button editButton = new Button("Edit");
            editButton.setStyle("-fx-background-color: #d3d3d3;-fx-text-fill: #737373");
            editButton.setOnAction(event->{
                NewPasswordPopupController.initKey = key;
                NewPasswordPopupController.initVal = passwords.get(key);
                loadPopup();
            });

            passwordContainer.setSpacing(20);
            passwordContainer.setAlignment(Pos.CENTER);
            passwordContainer.getChildren().addAll(copyToClipboard, editButton ,keySizeControl,delete);

            container.getChildren().add(passwordContainer);
        }
    }

    public static void loadPopup()
    {
        try{
            Parent parent = HelloApplication.loadView("new-password-popup.fxml");
            Stage secondaryStage = new Stage();

            secondaryStage.setWidth(400);
            secondaryStage.setHeight(150);

            Scene parentScene = HelloApplication.getScene();
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            secondaryStage.setResizable(false);
            secondaryStage.setScene(scene);

            secondaryStage.initOwner(parentScene.getWindow());
            secondaryStage.initModality(Modality.WINDOW_MODAL);
            secondaryStage.initStyle(StageStyle.TRANSPARENT);
            secondaryStage.setX(parentScene.getWindow().getX() + (parentScene.getWidth()- secondaryStage.getWidth())*0.5 );
            secondaryStage.setY(parentScene.getWindow().getY()  + (parentScene.getHeight()- secondaryStage.getHeight())*0.5);

            NewPasswordPopupController.window = secondaryStage;

            secondaryStage.show();
        }catch (IOException e)
        {
            System.out.println("Popup : No such view");
        }
    }
}
