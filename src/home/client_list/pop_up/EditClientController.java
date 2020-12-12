package home.client_list.pop_up;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.DBConnection;
import util.PopUpController;
import util.PopUpWindow;

public class EditClientController extends PopUpController implements Initializable{

    @FXML
    private TextField nameTextFieldEdit;
    @FXML
    private TextField surnameTextFieldEdit;
    @FXML
    private ChoiceBox<String> genderBoxEdit;
    @FXML
    private ChoiceBox<String> countryBoxEdit;
    @FXML
    private TextField phoneTextFieldEdit;
    
    
    private ObservableList<String> list1;
    private ObservableList<String> list2;
    
    private String[] row_array;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeChoiceBox();
    }
    
    
    private void initializeChoiceBox(){
        String[] gender_array = {"Male", "Female"};
        String[] country_array = {"GR", "CY", "ES", "IT"};

        list1 = FXCollections.observableArrayList();
        list2 = FXCollections.observableArrayList();
        
        for(int i = 0; i < gender_array.length; i++)
            list1.add(gender_array[i]);
        for(int i = 0; i < country_array.length; i++)
            list2.add(country_array[i]);
        
        genderBoxEdit.setItems(list1);
        countryBoxEdit.setItems(list2);
        genderBoxEdit.getSelectionModel().selectFirst();
        countryBoxEdit.getSelectionModel().selectFirst();
    }
    
    
    @Override
    public void InitializeController(){
        row_array = this.getParent().getRow();
        initialize();
    }
    
	//Apply Button Action Handler
	public void btnApplyEditOnAction(ActionEvent event) throws IOException, ClassNotFoundException, ClassNotFoundException{
            String[] input_array = input_toArray();
            if(!isChanged(row_array,input_array))
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            else if(!phoneIsNumber())
                PopUpWindow.Information("Wrong Input", "Phone field takes only numbers");
            else{
                try {
                    StringBuilder sql_update = new StringBuilder();
                    sql_update.append(row_array[0]).append(",");
                    sql_update.append("'").append(input_array[0]).append("',");
                    sql_update.append("'").append(input_array[1]).append("',");
                    sql_update.append("'").append(input_array[2]).append("',");
                    sql_update.append("'").append(input_array[3]).append("',");
                    sql_update.append("'").append(input_array[4]).append("'");
                    
                    DBConnection.getStatement().executeUpdate("select edit_client(" + sql_update.toString() + ")");
                    DBConnection.closeConnection();
                    
                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                    PopUpWindow.Information("CLIENT MODIFICATION", "You have modify successfully a client");
                    
                }
                catch(SQLException ex){
                    if(!ex.getSQLState().equals("0100E"))
                        PopUpWindow.Information("CLIENT MODIFICATION ERROR", ex.getLocalizedMessage());
                    else{
                        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                        PopUpWindow.Information("CLIENT MODIFICATION", "You have modify successfully a client");
                    }
                }
                catch(Exception ex){
                    PopUpWindow.Information("CLIENT MODIFICATION ERROR", ex.getLocalizedMessage());
                }
            }
        }
	
	//Cancel button, Closes Window
	public void btnCancelEditOnAction(ActionEvent event) throws IOException{
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
        
        
    @Override
        public String[] getRow(){
            String[] array = new String[6];
            return array;
        }
        
        
        //This method converts all the data from text fields to array
        private String[] input_toArray(){
            String[] array = new String[5];
            array[0] = nameTextFieldEdit.getText();
            array[1] = surnameTextFieldEdit.getText();
            array[2] = phoneTextFieldEdit.getText();
            array[3] = countryBoxEdit.getSelectionModel().getSelectedItem();
            array[4] = genderBoxEdit.getSelectionModel().getSelectedItem();
            return array;
        }
        
        //This method checks if there is indeed any modification in the data
        private boolean isChanged(String[] row_array, String[] input_array){
            for(int i = 0; i < input_array.length; i++)
                if(!row_array[i+1].equals(input_array[i]))
                    return true;
            return false;
        }
        
        
        //This method check if the value in textfield phone is a number
        private boolean phoneIsNumber(){
            try{
                Double.parseDouble(phoneTextFieldEdit.getText());  
            }
            catch(NumberFormatException ex){
                return false;
            }
            catch(Exception ex){
                return false;
            }
            return true;
        }
	
        
        //This method auto completes the fields in the window
        private void initialize(){
            nameTextFieldEdit.setText(row_array[1]);
            surnameTextFieldEdit.setText(row_array[2]);
            phoneTextFieldEdit.setText(row_array[3]);
            countryBoxEdit.getSelectionModel().select(row_array[4]);
            genderBoxEdit.getSelectionModel().select(row_array[5]);
        }
}