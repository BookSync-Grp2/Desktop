module poo.booksync.booksync {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    // Opens packages to JavaFX reflection
    opens poo.booksync to javafx.fxml;
    opens poo.booksync.model to javafx.base; // Add this line
    opens poo.booksync.controller to javafx.fxml;

    // Exports packages
    exports poo.booksync;
    exports poo.booksync.controller;
}
