package com.example.view;

import java.util.Map;

import com.example.dao.StudentDAO;
import com.example.service.StudentService;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class AgeChart {
    private final StudentService studentService = new StudentService(new StudentDAO());

    public void show() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Tranche d'age");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("nombre d'etudiant");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Répartiton des etudiant par tranche d'age");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Etudiant");

        Map<String, Integer> data = studentService.getAgeRangeStats();
        for  (Map.Entry<String, Integer> entry: data.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));

        }
        barChart.getData().add(series);

        BorderPane root = new BorderPane(barChart);
        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Statistique");
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.show();

    }
}
