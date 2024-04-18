module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires jakarta.xml.bind;
    requires jakarta.activation;
    requires org.apache.commons.codec;
    

    opens com.example.view to javafx.fxml;
    exports com.example.view;
    //exports com.example.viewmodel;
    exports com.example.model;
}
