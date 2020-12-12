package home.dashboard;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import util.Controller;
import util.FxmlPath;
import util.PopUpWindow;

public class DashboardController extends Controller{
	@FXML
	private Button btnAddTransaction;
	
	@Override
	public void InitializeController() {
		String car = this.getHomeController().getSelectedCar();
		String client = this.getHomeController().getSelectedClient();
		
		if(car.equals("none") || client.equals("none") ) btnAddTransaction.setDisable(true);
		else btnAddTransaction.setDisable(false);
	}			
	public void btnAddCarOnAction(ActionEvent event) throws IOException{
		this.getHomeController().LoadCarInv(true);
	}
	public void btnAddClientOnAction(ActionEvent event) throws IOException{
		this.getHomeController().LoadClientList(true);
	}
	public void btnAddTransactionOnAction(ActionEvent event) throws IOException{
		this.getHomeController().LoadTransactionHistory(true);
	}
	
	public void btnAboutOnAction(ActionEvent event) throws IOException{
		PopUpWindow.NewBorderPaneWindow("About", FxmlPath.aboutFXML, StageStyle.DECORATED, Modality.APPLICATION_MODAL, false);	
	}

}
