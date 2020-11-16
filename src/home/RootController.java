package app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class RootController {
	
	@FXML
	private Button btnExit;
	@FXML
	private Button btnAbout;
	
	//Exit Button, Asks confirmation, closes application
	public void btnExitlOnAction(ActionEvent event) {
		
		//Show Exit Confirmation Window
		Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.setTitle("Exit Confirmation");
		alert.setHeaderText("Are you sure you want to quit?");
		alert.showAndWait();
		
		//Exit application if "YES" is selected
		if (alert.getResult() == ButtonType.YES) {
			Stage stage = (Stage) btnExit.getScene().getWindow();
			stage.close();
		}
	}
	
	//About Button
	public void btnAboutOnAction(ActionEvent event) {
		//New window, about screen
		try {	
			Stage window = new Stage();				
			BorderPane layout;
			layout = (BorderPane)FXMLLoader.load(getClass().getResource("../style/AboutScreen.fxml"));			
			Scene scene = new Scene(layout);									
			window.setTitle("About");			
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
