module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires jakarta.xml.bind;
    requires jakarta.activation;
    requires org.apache.commons.codec;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires org.json;

    opens com.example.view to javafx.fxml;
    exports com.example.view;
    //exports com.example.viewmodel;
    exports com.example.model;
}
