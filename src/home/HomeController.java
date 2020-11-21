package home;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class HomeController {
	
	@FXML
	private Button btnExit;
	@FXML
	private Button btnAbout;//
	@FXML
	private Button btnTransactionHistory;//
	@FXML
	private Button btnCarInv;//
	
	@FXML
	private AnchorPane anchorRoot;
	
        
        public HomeController(){
            System.out.println("again home");
        }
        
	//Dashboard Side Menu Button
	public void btnDashboardOnAction(ActionEvent event) throws IOException{
		System.out.println("Loading Dashboard FXML");
		AnchorPane anchor = FXMLLoader.load(getClass().getResource("dashboard/Dashboard.fxml"));
		anchorRoot.getChildren().setAll(anchor);
	}
	
	//Car Inventory Side Menu Button
	public void btnCarInvOnAction(ActionEvent event) throws IOException{
		System.out.println("Loading Car Inventory FXML");
		VBox vbox = FXMLLoader.load(getClass().getResource("car_inventory/CarInventory.fxml"));
		anchorRoot.getChildren().setAll(vbox);
	}
	
	//Transaction History Side Menu Button
	public void btnTransactionHistoryOnAction(ActionEvent event) throws IOException{
		System.out.println("Loading Transaction History FXML");
		VBox vbox = FXMLLoader.load(getClass().getResource("transaction/Transaction.fxml"));
		anchorRoot.getChildren().setAll(vbox);
	}
	
	//Client List Side Menu Button
	public void btnClientListOnAction(ActionEvent event) throws IOException{
		System.out.println("Loading Client List FXML");
		VBox vbox = FXMLLoader.load(getClass().getResource("client_list/ClientList.fxml"));
		anchorRoot.getChildren().setAll(vbox);
	}
	
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
	
	//BUG: About page doesn't close when quitting application!!!
	//About Button
	public void btnAboutOnAction(ActionEvent event) {
		//New window, about screen
		try {	
			Stage window = new Stage();				
			BorderPane layout;
			layout = (BorderPane)FXMLLoader.load(getClass().getResource("about/About.fxml"));			
			Scene scene = new Scene(layout);									
			window.setTitle("About");			
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
