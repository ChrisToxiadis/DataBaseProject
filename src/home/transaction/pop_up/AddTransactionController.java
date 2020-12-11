package home.transaction.pop_up;

import data_base_interface.DBConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.PopUpController;


public class AddTransactionController extends PopUpController{

	@FXML
	private DatePicker dateBoxAdd;
	@FXML
	private TextField carIdTextFieldAdd;
	@FXML
	private TextField clientIdTextFieldAdd;
	
	@Override
	public void InitializeController() {
		carIdTextFieldAdd.setText(this.getHomeController().getSelectedCar());
                carIdTextFieldAdd.setEditable(false);
		clientIdTextFieldAdd.setText(this.getHomeController().getSelectedClient());
                clientIdTextFieldAdd.setEditable(false);
                
	}
	
	//Add Transaction Button Action Handler
	public void btnAddTransactionAddOnAction(ActionEvent event) throws IOException{
            try{
                StringBuilder sql_insert = new StringBuilder();
                sql_insert.append(Integer.parseInt(carIdTextFieldAdd.getText())).append(",");
                sql_insert.append(Integer.parseInt(clientIdTextFieldAdd.getText())).append(",");
                sql_insert.append("'").append(dateBoxAdd.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("'");
                System.out.println("sql insert is " + sql_insert.toString());
                DBConnection.getStatement().executeUpdate("select add_transaction(" + sql_insert.toString() + ")");
                //DBConnection.getStatement().execute("select add(" + sql_insert.toString() + ")");
                DBConnection.closeConnection();
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }
            catch(SQLException ex){
                System.out.println("sql ex" + ex.toString());
                System.out.println("sql ex" + ex.getMessage());
                System.out.println("sql ex" + ex.getLocalizedMessage());
                System.out.println("sql ex" + ex.getErrorCode());
                System.out.println("sql ex" + ex.getSQLState());
            }
            catch(Exception ex){
                System.out.println(ex.getStackTrace());
                System.out.println(ex.toString());
                System.out.println(ex.getMessage());
                System.out.println(ex.getLocalizedMessage());
            }
        //exception handling must be implemented
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}	
	
	//Cancel button, Closes Window
	public void btnCancelAddOnAction(ActionEvent event) throws IOException{
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	
	
	
}