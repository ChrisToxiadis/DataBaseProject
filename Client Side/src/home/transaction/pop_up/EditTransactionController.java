package home.transaction.pop_up;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import util.DBConnection;
import util.PopUpController;
import util.PopUpWindow;

public class EditTransactionController extends PopUpController{
    
    @FXML
    private DatePicker dateBoxEdit;
    
    private String[] row_array;
    
    
    
    @Override
    public void InitializeController(){
        row_array = this.getParent().getRow();
        initialize();
    }
    
	//Apply Button Action Handler
	public void btnApplyTransactionEditOnAction(ActionEvent event) throws IOException{
            String[] input_array = input_toArray();
            if(!isChanged(row_array,input_array))
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            else{
                try {
                    StringBuilder sql_update = new StringBuilder();
                    sql_update.append(row_array[0]).append(",");
                    sql_update.append(row_array[1]).append(",");
                    sql_update.append("'").append(input_array[0]).append("'");
                   
                    DBConnection.getStatement().executeUpdate("select edit_transaction(" + sql_update.toString() + ")");
                    DBConnection.closeConnection();
                    
                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                    PopUpWindow.Information("TRANSACTION MODIFICATION", "You have modify successfully a transaction");
                    
                }
                catch(SQLException ex){
                    if(!ex.getSQLState().equals("0100E"))
                        PopUpWindow.Information("TRANSACTION MODIFICATION ERROR", ex.getLocalizedMessage());
                    else{
                        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                        PopUpWindow.Information("TRANSACTION MODIFICATION", "You have modify successfully a transaction");
                    }
                }
                catch(Exception ex){
                    PopUpWindow.Information("TRANSACTION MODIFICATION ERROR", ex.getLocalizedMessage());
                }
        }
    }
	
	//Cancel button, closes Window
	public void btnCancelEditOnAction(ActionEvent event) throws IOException{
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
        
        
        //This method converts all the data from text fields to array
        private String[] input_toArray(){
            String[] array = new String[1];
            
            if(dateBoxEdit.getValue() != null){
                String date = dateBoxEdit.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                array[0] = date;
            }
            return array;
        }
        
        //This method checks if there is indeed any modification in the data
        private boolean isChanged(String[] row_array, String[] input_array){
            return !row_array[2].equals(input_array[0]);
        }
        
        
        public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
        
        //This method auto-completes The datePicker
        private void initialize(){
            dateBoxEdit.setValue(LOCAL_DATE(row_array[2]));
        }
	
}
