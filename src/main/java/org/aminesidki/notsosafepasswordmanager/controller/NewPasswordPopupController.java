package org.aminesidki.notsosafepasswordmanager.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.aminesidki.notsosafepasswordmanager.util.DatabaseUtils;
import org.aminesidki.notsosafepasswordmanager.util.GeneralUtils;

import java.sql.SQLException;

public class NewPasswordPopupController {
    public static String initKey = "", initVal = "";
    public static Stage window;

    private boolean isInit = false;

    @FXML
    private TextField key;

    @FXML
    private TextField value;

    @FXML
    protected void initialize(){
        Platform.runLater(()->{
            if(!initVal.isEmpty() && !initKey.isEmpty()) {
                key.setText(initKey);
                value.setText(initVal);

                initKey = "";
                initVal = "";

                isInit = true;
            }
        });
    }

    @FXML
    protected void onDone(){
        if(!key.getText().isEmpty() && !value.getText().isEmpty()) {
            try {
                if(!isInit) {
                    DatabaseUtils.insert(key.getText(), value.getText());
                }else{
                    DatabaseUtils.update(key.getText(), value.getText());
                    isInit = false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            GeneralUtils.reloadPasswordsContainer(HelloController.staticContainer);
            window.close();
        }
    }

    @FXML
    protected void onClose(){
        window.close();
    }
}
