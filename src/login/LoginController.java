package app;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class LoginController{
	
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnLogin;
	@FXML
	private Label lblLoginError;
	@FXML
	private TextField txtFieldUser;
	@FXML
	private PasswordField txtFieldPassword;
	
	public String Username = "admin";//Place-Holder
	public String Password = "admin";//Place-Holder

	//Login Button, Validates User and Password
	public void btnLoginOnAction(ActionEvent event) {
		validateLogin(txtFieldUser.getText(), txtFieldPassword.getText());

	}
	
	//Cancel Button, closes window
	public void btnCancelOnAction(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	}
	
	//Checks if User and Password are correct,then opens main screen (new window)
	public void validateLogin(String user, String password) {
		//No User
		if (user.isBlank()) {
			lblLoginError.setText("Please enter the User.");
		}
		//No Password
		else if(password.isBlank()) {
			lblLoginError.setText("Please enter the Password.");
		}
		//Correct User and Password
		else if(user.equals(Username) && password.equals(Password)) {
			lblLoginError.setText("");
			System.out.println("YOU LOGGED IN!!!!");			

			try {
				//Open main screen on new window
				Stage window = new Stage();				
				BorderPane layout;
				layout = (BorderPane)FXMLLoader.load(getClass().getResource("../style/RootScreen.fxml"));			
				Scene scene = new Scene(layout);									
				window.setMaximized(true);
				window.setTitle("CarDB 2020");			
				window.setScene(scene);
				window.show();
				
				//Close Login window
				Stage stageOld = (Stage) btnLogin.getScene().getWindow();
				stageOld.close();
			
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}
		//Wrong User or Password
		else {
			lblLoginError.setText("Invalid login. Please try again.");
		}
	}
}
