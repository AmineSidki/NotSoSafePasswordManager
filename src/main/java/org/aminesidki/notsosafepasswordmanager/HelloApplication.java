package org.aminesidki.notsosafepasswordmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Scene scene;
    private static FXMLLoader fxmlLoader;

    public static Scene getScene()
    {
        return scene;
    }

    public static Parent loadView(String viewName) throws IOException{
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(viewName));
        return fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        scene = new Scene(fxmlLoader.load(), 500, 500 , false , SceneAntialiasing.BALANCED);
        stage.initStyle(StageStyle.UNIFIED);
        stage.setTitle("Passwords");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
        stage.setAlwaysOnTop(true);
    }

    public static void main(String[] args) {
        launch();
    }
}