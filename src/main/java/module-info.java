module poo.booksync.booksync {
    // Modules nécessaires pour JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    // Dépendances tierces
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires jdk.compiler;
    requires java.desktop;

    opens poo.booksync to javafx.fxml;
    opens poo.booksync.model to javafx.base;
    opens poo.booksync.controller to javafx.fxml;

    exports poo.booksync;
    exports poo.booksync.controller;
    exports poo.booksync.model.dto;
    opens poo.booksync.model.dto to javafx.base;
}
