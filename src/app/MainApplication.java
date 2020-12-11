package app;
	
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.FxmlPath;
import util.PopUpWindow;


public class MainApplication extends Application {
	@Override
	public void start(Stage primaryStage) {		
		
		PopUpWindow.NewBorderPaneWindow("login", FxmlPath.loginFXML, StageStyle.UNDECORATED, Modality.NONE, false);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
