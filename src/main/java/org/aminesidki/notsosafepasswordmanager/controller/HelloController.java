package org.aminesidki.notsosafepasswordmanager.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.aminesidki.notsosafepasswordmanager.util.DatabaseUtils;
import org.aminesidki.notsosafepasswordmanager.util.GeneralUtils;

public class HelloController {
    //To access the container from the popup.
    public static VBox staticContainer ;

    @FXML
    protected VBox container;

    @FXML
    protected void initialize(){
        DatabaseUtils.initializeConnection();
        GeneralUtils.reloadPasswordsContainer(container);
        staticContainer = container;
    }

    @FXML
    protected void newPwd(){
        GeneralUtils.loadPopup();
    }
}