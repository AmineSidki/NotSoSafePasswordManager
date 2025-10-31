module org.aminesidki.notsosafepasswordmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.aminesidki.notsosafepasswordmanager to javafx.fxml;
    exports org.aminesidki.notsosafepasswordmanager;
    exports org.aminesidki.notsosafepasswordmanager.controller;
    opens org.aminesidki.notsosafepasswordmanager.controller to javafx.fxml;
    exports org.aminesidki.notsosafepasswordmanager.util;
    opens org.aminesidki.notsosafepasswordmanager.util to javafx.fxml;
}