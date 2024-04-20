package com.example.view;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;
    private static ConfigurableApplicationContext applicationContext;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/com/example/loginPage.fxml"), 1000, 700);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void init() {
        List<String> args = getParameters().getRaw();
        applicationContext = new SpringApplicationBuilder()
                .sources(SpringBootMain.class) // This is your Spring Boot application class
                .run(args.toArray(new String[0]));
    }

    @Override
    public void stop() {
        applicationContext.close();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
