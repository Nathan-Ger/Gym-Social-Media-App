package com.example.view;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.net.URL;

public class TrackingController {

    @FXML
    private void handleDashBoardInBtnAction(ActionEvent event) {
        try {
            Parent signUpPageParent = FXMLLoader.load(getClass().getResource("/com/example/dashboard.fxml"));
            Scene signUpPageScene = new Scene(signUpPageParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(signUpPageScene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleMapBtnAction(ActionEvent event) {
        try {
            // Load the sign-up page FXML
            Parent signUpPageParent = FXMLLoader.load(getClass().getResource("/com/example/map.fxml"));
            Scene signUpPageScene = new Scene(signUpPageParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(signUpPageScene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //this is for the edit btn
    @FXML
    private void handleEditButtonAction(ActionEvent event) {
        try {
            // Load the sign-up page FXML
            Parent profile = FXMLLoader.load(getClass().getResource("/com/example/profile.fxml"));
            Scene profileView = new Scene(profile);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(profileView);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public WebView weatherWebView;
    public WebView webViewMessageWeather;
    @FXML
    private PieChart cardioPieChart;
    @FXML
    private LineChart<String, Number> weeklyProgressChart;
    @FXML
    private BarChart<String, Number> weeklyTotalTimeChart;
    @FXML
    public Pane rootPane;

    @FXML
    public void initialize() {
        //weather application features here
        try {
            // Load the HTML file into the WebView
            URL urlHtml = getClass().getResource("/weather.html");
            URL urlHtml2 = getClass().getResource("/weatherMessage.html");
            weatherWebView.getEngine().load(urlHtml.toExternalForm());
            webViewMessageWeather.getEngine().load(urlHtml2.toExternalForm());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Initializing controller: Applying smooth color transition...");
        // Creating a rectangle with rounded corners and the appropriate size
        Rectangle rect = new Rectangle();
        rect.widthProperty().bind(rootPane.widthProperty());
        rect.heightProperty().bind(rootPane.heightProperty());
        rect.setArcWidth(40); // Adjust to match your rounded corner radius
        rect.setArcHeight(40);
        // Initial fill color
        rect.setFill(Color.web("#FB6D48"));
        // Add the rectangle as the first child of the pane
        rootPane.getChildren().add(0, rect);
        // Applying the smooth fill transition
        applySmoothFillTransition(rect, Color.web("#FB6D48"), Color.web("#111B18"));

        //this is for the weekly chart
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Days");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Progress");
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Gym Visits");
        series1.getData().add(new XYChart.Data<>("Mon", 4));
        series1.getData().add(new XYChart.Data<>("Tue", 5));
        series1.getData().add(new XYChart.Data<>("Wed", 3));
        series1.getData().add(new XYChart.Data<>("Thu", 6));
        series1.getData().add(new XYChart.Data<>("Fri", 2));
        series1.getData().add(new XYChart.Data<>("Sat", 8));
        series1.getData().add(new XYChart.Data<>("Sun", 7));

        // Add the data series to the Piechart
        weeklyProgressChart.getData().add(series1);
        PieChart.Data slice1 = new PieChart.Data("Running", 25);
        PieChart.Data slice2 = new PieChart.Data("Cycling", 30);
        PieChart.Data slice3 = new PieChart.Data("Swimming", 20);
        PieChart.Data slice4 = new PieChart.Data("Walking", 25);
        cardioPieChart.getData().addAll(slice1, slice2, slice3, slice4);
        cardioPieChart.setTitle("Cardio Activities");
        Timeline timeline = new Timeline();
        // each slice to add animation effects
        for (PieChart.Data data : cardioPieChart.getData()) {
            // ScaleTransition for each slice
            ScaleTransition st = new ScaleTransition(Duration.millis(500), data.getNode());
            st.setFromX(1.0);
            st.setFromY(1.0);
            st.setToX(1.2);
            st.setToY(1.2);
            st.setAutoReverse(true);
            st.setCycleCount(2); // Grow and shrink

            // keyframe to the timeline for delayed execution
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(timeline.getKeyFrames().size()),
                            new KeyValue(data.getNode().scaleXProperty(), 1.2),
                            new KeyValue(data.getNode().scaleYProperty(), 1.2)
                    )
            );
        }

        // timeline to loop indefinitely
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Initialize the BarChart with sample data BARCHART
        XYChart.Series<String, Number> barSeries = new XYChart.Series<>();
        barSeries.setName("Total Time");
        barSeries.getData().add(new XYChart.Data<>("Mon", 60));
        barSeries.getData().add(new XYChart.Data<>("Tue", 45));
        barSeries.getData().add(new XYChart.Data<>("Wed", 50));
        barSeries.getData().add(new XYChart.Data<>("Thu", 70));
        barSeries.getData().add(new XYChart.Data<>("Fri", 30));
        barSeries.getData().add(new XYChart.Data<>("Sat", 80));
        barSeries.getData().add(new XYChart.Data<>("Sun", 90));

        weeklyTotalTimeChart.getData().add(barSeries);
    }


    @FXML
    /**
     * Applies a fill transition to the given rectangle.
     * @param rect The rectangle to apply the effect on.
     * @param fromColor The starting color.
     * @param toColor The ending color.
     */
    private void applySmoothFillTransition(Rectangle rect, Color fromColor, Color toColor) {
        // Create the fill transition with a longer duration (10 seconds)
        FillTransition fillTransition = new FillTransition(Duration.seconds(10), rect, fromColor, toColor);
        fillTransition.setCycleCount(FillTransition.INDEFINITE); // Loop indefinitely
        fillTransition.setAutoReverse(true); // Reverse the direction
        fillTransition.setInterpolator(Interpolator.LINEAR); // Ensuringthe  smooth interpolation

        System.out.println("FillTransition started with a linear interpolator.");
        fillTransition.play();
    }

}
