module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires jakarta.xml.bind;
    requires jakarta.activation;
    requires org.apache.commons.codec;
    //requires spring.boot.starter.web;

    // Spring Framework Modules
    requires spring.boot;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.boot.autoconfigure;
    requires spring.security.core;

    // JWT Modules
    requires jjwt.api;
    
    
    

    opens com.example.view to javafx.fxml, spring.core, spring.context, spring.beans, jjwt.api, jjwt.impl, jjwt.jackson, spring.security.core;
    exports com.example.view;
    //exports com.example.viewmodel;

    //requires transitive spring.context;

    exports com.example.model;

    opens com.example.model to spring.beans, spring.context, jjwt.api, jjwt.impl, jjwt.jackson, spring.security.core;
}
