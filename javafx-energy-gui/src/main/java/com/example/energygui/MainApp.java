package com.example.energygui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainApp extends Application {

    private Label resultLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Energy GUI");

        resultLabel = new Label("Data will be shown here...");

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> fetchCurrentData());

        VBox vbox = new VBox(10, refreshButton, resultLabel);
        vbox.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(vbox, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void fetchCurrentData() {
        try {
            URL url = new URL("http://localhost:8080/energy/current");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine).append("\n");
            }

            in.close();
            conn.disconnect();

            resultLabel.setText(content.toString());
        } catch (Exception e) {
            resultLabel.setText("Error: " + e.getMessage());
        }
    }
}
