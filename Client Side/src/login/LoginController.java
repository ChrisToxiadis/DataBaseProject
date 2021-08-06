package login;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import util.FxmlPath;
import util.PopUpController;
import util.PopUpWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

public class LoginController extends PopUpController{
	
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
			
			Stage window = PopUpWindow.NewBorderPaneWindow("CarDB 2020", FxmlPath.homeFXML, StageStyle.DECORATED, Modality.NONE, false);
			window.setResizable(true);
			//window.setMaximized(true);			
			//Platform.setImplicitExit(false);
			window.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent event) {
		        	//Show Exit Confirmation Window
		      		Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.CANCEL);
		      		alert.setTitle("Exit Confirmation");
		      		alert.setHeaderText("Are you sure you want to quit?");
		      		alert.showAndWait();
		      		
		      		//Exit application if "YES" is selected
		      		if (alert.getResult() == ButtonType.YES) {
		      			window.close();
		      		} else event.consume();
		          }
		      }); 
			
			//Close Login window
			Stage stageOld = (Stage) btnLogin.getScene().getWindow();
			stageOld.close();

		}
		//Wrong User or Password
		else {
			lblLoginError.setText("Invalid login. Please try again.");
		}
	}
}
