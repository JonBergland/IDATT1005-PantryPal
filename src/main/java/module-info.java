module stud.ntnu.idatt1005.pantrypal {
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

  opens stud.ntnu.idatt1005.pantrypal to javafx.fxml;
  exports stud.ntnu.idatt1005.pantrypal;
  exports stud.ntnu.idatt1005.pantrypal.views.components;
  exports stud.ntnu.idatt1005.pantrypal.models;
  exports stud.ntnu.idatt1005.pantrypal.controllers;
  exports stud.ntnu.idatt1005.pantrypal.utils;
}