package home.transaction.pop_up;

import data_base_interface.DBConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import util.PopUpController;

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
                } catch(SQLException ex){
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
        }
    }
	
	//Cancel button, closes Window
	public void btnCancelEditOnAction(ActionEvent event) throws IOException{
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
        
        
        
        private String[] input_toArray(){
            String[] array = new String[1];
            
            if(dateBoxEdit.getValue() != null){
                String date = dateBoxEdit.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                array[0] = date;
            }
            return array;
        }
        
        
        private boolean isChanged(String[] row_array, String[] input_array){
            return !row_array[2].equals(input_array[0]);
        }
        
        
        
        private void initialize(){
           // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            //dateBoxEdit.setValue(LOCAL_DATE(row_array[2]));
        }
	
}
