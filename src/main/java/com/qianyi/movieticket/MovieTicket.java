package com.qianyi.movieticket;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MovieTicket extends Application {
    private ComboBox<String> category;
    private ToggleGroup tgSession;
    private TextField tfSeats;
    private ToggleGroup tgFnB;
    @Override
    public void start(Stage stage) throws IOException {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(15);
        grid.setVgap(10);

        //movie
        Label lblMovie = new Label("Movie");
        TextField tfMovie = new TextField();
        grid.add(lblMovie,0,0,1,1);
        grid.add(tfMovie,1,0,1,1);

        //experience
        Label lblExperience = new Label("Experience");
        category = new ComboBox<>();
        category.setPrefWidth(250);
        category.getItems().addAll("Beanie", "Classic", "Deluxe", "Family-Friendly","Flexound", "IMAX", "Indulge", "Infinity", "Junior", "Onyx");
        //category.getSelectionModel().selectFirst();

        grid.add(lblExperience,0,1,1,1);
        grid.add(category,1,1,1,1);

        //session
        Label lblSession = new Label("Session");
        RadioButton rbShowTime1 = new RadioButton("11:00 AM");
        RadioButton rbShowTime2 = new RadioButton("01:30 PM");
        RadioButton rbShowTime3 = new RadioButton("04:00 PM");
        RadioButton rbShowTime4 = new RadioButton("06:30 PM");
        RadioButton rbShowTime5 = new RadioButton("09:00 PM");
        tgSession = new ToggleGroup();
        rbShowTime1.setToggleGroup(tgSession);
        rbShowTime2.setToggleGroup(tgSession);
        rbShowTime3.setToggleGroup(tgSession);
        rbShowTime4.setToggleGroup(tgSession);
        rbShowTime5.setToggleGroup(tgSession);
        rbShowTime1.setSelected(false);

        VBox sessions = new VBox(rbShowTime1, rbShowTime2, rbShowTime3, rbShowTime4, rbShowTime5);
        sessions.setSpacing(5);
        grid.add(lblSession,0,2,1,1);
        grid.add(sessions,1,2,1,1);

        //seats
        Label lblSeats = new Label("Seats");
        tfSeats = new TextField();
        grid.add(lblSeats,0,3,1,1);
        grid.add(tfSeats,1,3,1,1);

        //Food & Beverages
        Label lblFnB = new Label("Food & Beverages");

        grid.add(lblFnB,0,4,1,1);

        Image imgPopcorn1 = new Image(MovieTicket.class.getResource("popcorn1.png").toString());
        ImageView ivPopcorn1 = new ImageView(imgPopcorn1);
        ivPopcorn1.setFitHeight(170);
        ivPopcorn1.setFitWidth(230);

        Image imgPopcorn2 = new Image(MovieTicket.class.getResource("popcorn2.png").toString());
        ImageView ivPopcorn2 = new ImageView(imgPopcorn2);
        ivPopcorn2.setFitHeight(170);
        ivPopcorn2.setFitWidth(230);

        Image imgPopcorn3 = new Image(MovieTicket.class.getResource("popcorn3.png").toString());
        ImageView ivPopcorn3 = new ImageView(imgPopcorn3);
        ivPopcorn3.setFitHeight(170);
        ivPopcorn3.setFitWidth(230);

        HBox imageView = new HBox(ivPopcorn1, ivPopcorn2, ivPopcorn3);

        imageView.setSpacing(10);
        grid.add(imageView,1,4,1,1);

        HBox txtPopChoice = new HBox();
        Text txtPopChoice1 = new Text("Royal Popcorn Combo – Member Special");
        Text txtPopChoice2 = new Text("Royal Popcorn");
        Text txtPopChoice3 = new Text("Royal Popcorn Combo");

        txtPopChoice.getChildren().addAll(txtPopChoice1,txtPopChoice2, txtPopChoice3);
        txtPopChoice.setSpacing(95);

        grid.add(txtPopChoice,1,5,1,1);

        RadioButton rbPopChoice1 = new RadioButton("RM 19.90");
        RadioButton rbPopChoice2 = new RadioButton("RM 17.90");
        RadioButton rbPopChoice3 = new RadioButton("RM 21.90");
        tgFnB = new ToggleGroup();
        rbPopChoice1.setToggleGroup(tgFnB);
        rbPopChoice2.setToggleGroup(tgFnB);
        rbPopChoice3.setToggleGroup(tgFnB);

        rbPopChoice1.setSelected(false);

        HBox popChoice = new HBox(rbPopChoice1, rbPopChoice2, rbPopChoice3);
        popChoice.setSpacing(170);
        grid.add(popChoice,1,6,1,1);

        //button submit
        Button btnSubmit = new Button("Submit");
        btnSubmit.setPrefWidth(100);
        btnSubmit.setOnAction(e -> {
            // Calculate the total amount
            double totalAmount = calculateTotalAmount();

            // Display the dialog pane or alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thank You!");
            alert.setHeaderText("Confirmation");
            alert.setContentText("You selected " + tfMovie.getText() +
                    " with " + category.getValue() +
                    " experience at " + ((RadioButton) tgSession.getSelectedToggle()).getText() +
                    " for " + tfSeats.getText() +
                    " and a " + ((RadioButton) tgFnB.getSelectedToggle()).getText() +
                    "\nThe total is RM " + totalAmount); // Modify this to display the total amount

            alert.showAndWait();
        });

        HBox button = new HBox(btnSubmit);
        button.setAlignment(Pos.BASELINE_RIGHT);
        grid.add(button,1,7,1,1);

        Scene scene = new Scene(grid);
        stage.setTitle("Movie Ticketing System");
        stage.setScene(scene);
        stage.show();
    }

    private double calculateTotalAmount() {
        // Get the selected values from the ComboBox, RadioButtons, and ToggleGroups
        String selectedExperience = category.getValue().toString();
        RadioButton selectedSessionRadioButton = (RadioButton) tgSession.getSelectedToggle();
        String selectedSession = selectedSessionRadioButton.getText();
        String selectedSeats = tfSeats.getText();


        //  calculations:
        double ticketAmount = 0.00; // Base ticket price
        double popcornAmount = 0.00;
        if (selectedExperience.equals("Beanie")) {
            ticketAmount += 19.90;
        } else if (selectedExperience.equals("Classic")) {
            ticketAmount += 15.90;
        } else if (selectedExperience.equals("Deluxe")) {
            ticketAmount += 23.90;
        } else if (selectedExperience.equals("Family-Friendly")) {
            ticketAmount += 23.90;
        } else if (selectedExperience.equals("Flexound")) {
            ticketAmount += 25.90;
        } else if (selectedExperience.equals("IMAX")) {
            ticketAmount += 25.90;
        } else if (selectedExperience.equals("Indulge")) {
            ticketAmount += 120.00;
        } else if (selectedExperience.equals("Infinity")) {
            ticketAmount += 120.00;
        } else if (selectedExperience.equals("Junior")) {
            ticketAmount += 15.90;
        }else {
            ticketAmount += 85.90;
        }

        // calculate the food and beverage amount based on the selected radio button
        RadioButton selectedFnBRadioButton = (RadioButton) tgFnB.getSelectedToggle();
        String selectedFnB = selectedFnBRadioButton.getText();
        // Add calculations for food and beverages
        double fnBAmount = 0.00;
        if (selectedFnB.equals("RM 19.90")) {
            fnBAmount += 19.90;
        } else if (selectedFnB.equals("RM 17.90")) {
            fnBAmount += 17.90;
        } else if (selectedFnB.equals("RM 21.90")) {
            fnBAmount += 21.90;
        }
        // Calculate the total amount
        double totalAmount = ticketAmount + fnBAmount;

        return totalAmount;
    }


    public static void main(String[] args) {
        launch();
    }
}