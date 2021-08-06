package home.transaction.pop_up;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.DBConnection;
import util.PopUpController;
import util.PopUpWindow;


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
                if(inputIsEmpty()){
                    PopUpWindow.Information("DATE IS MISSING", "You have to fill in the date");
                    return;
                }
                    
                
                StringBuilder sql_insert = new StringBuilder();
                sql_insert.append(Integer.parseInt(carIdTextFieldAdd.getText())).append(",");
                sql_insert.append(Integer.parseInt(clientIdTextFieldAdd.getText())).append(",");
                sql_insert.append("'").append(dateBoxAdd.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("'");
                System.out.println("sql insert is " + sql_insert.toString());
                DBConnection.getStatement().executeUpdate("select add_transaction(" + sql_insert.toString() + ")");
                DBConnection.closeConnection();
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                
                PopUpWindow.Information("TRANSACTION ADDED", "You have added successfully a transaction");
        } 
        catch(SQLException ex){
                if(!ex.getSQLState().equals("0100E") && !ex.getSQLState().equals("23505"))
                    PopUpWindow.Information("TRANSACTION ADDED ERROR", ex.getSQLState());
                else if(ex.getSQLState().equals("23505"))
                    PopUpWindow.Information("TRANSACTION ADDED ERROR", "A customer connot buy the same car twice");
                else{
                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                    PopUpWindow.Information("TRANSACTION ADDED", "You have added successfully a transaction");
                }
            }
            catch(Exception ex){
                PopUpWindow.Information("TRANSACTION ADDED ERROR", ex.getLocalizedMessage());
            }
	}	
	
	//Cancel button, Closes Window
	public void btnCancelAddOnAction(ActionEvent event) throws IOException{
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	
        
        private boolean inputIsEmpty(){
            return dateBoxAdd.getValue() == null;
        }
	
	
}