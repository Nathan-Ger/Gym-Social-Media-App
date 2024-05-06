package com.example.view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import javafx.util.Duration;

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


    @FXML
    private PieChart cardioPieChart;
    @FXML
    private LineChart<String, Number> weeklyProgressChart;
    @FXML
    private BarChart<String, Number> weeklyTotalTimeChart;

    @FXML
    public void initialize() {
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

}
