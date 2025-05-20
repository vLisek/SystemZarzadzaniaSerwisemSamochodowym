module project.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.desktop;
    requires org.hibernate.orm.core;

    opens project.app to javafx.fxml;
    exports project.app;
    exports project.app.utils;
    opens project.app.utils to javafx.fxml;
    exports project.app.controllers;
    opens project.app.controllers to javafx.fxml;
}