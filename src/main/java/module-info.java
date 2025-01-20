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

    opens poo.booksync to javafx.fxml;
    exports poo.booksync;
    exports poo.booksync.controller;
    opens poo.booksync.controller to javafx.fxml;
}