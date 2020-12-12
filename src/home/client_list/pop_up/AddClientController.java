package home.client_list.pop_up;

import home.client_list.ClientListModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.DBConnection;
import util.PopUpController;
import util.PopUpWindow;

public class AddClientController extends PopUpController implements Initializable{
    
    @FXML
    private TextField nameTextFieldAdd;
    @FXML
    private TextField surnameTextFieldAdd;
    @FXML
    private ChoiceBox<String> genderBoxAdd;
    @FXML
    private ChoiceBox<String> countryBoxAdd;
    @FXML
    private TextField phoneTextFieldAdd;
    
    private ObservableList<String> list1;
    private ObservableList<String> list2;
    
    
    
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
        genderBoxAdd.setItems(list1);
        for(int i = 0; i < country_array.length; i++)
            list2.add(country_array[i]);
        countryBoxAdd.setItems(list2);
        countryBoxAdd.getSelectionModel().selectFirst();
        genderBoxAdd.getSelectionModel().selectFirst();
    }
	//Add Client Button
	public void btnAddClientAddOnAction(ActionEvent event) throws IOException{
        try {
            if(inputIsEmpty()){
                PopUpWindow.Information("Wrong Input", "You have to fill in all the fields");
                return;
            }
            
            if(!phoneIsNumber()){
                PopUpWindow.Information("Wrong Input", "Phone field takes only numbers");
                return;
            }
            
            StringBuilder sql_insert = new StringBuilder();
            sql_insert.append(createId()).append(",");
            sql_insert.append("'").append(nameTextFieldAdd.getText()).append("',");
            sql_insert.append("'").append(surnameTextFieldAdd.getText()).append("',");
            sql_insert.append("'").append(phoneTextFieldAdd.getText()).append("',");
            sql_insert.append("'").append(countryBoxAdd.getSelectionModel().getSelectedItem()).append("',");
            sql_insert.append("'").append(genderBoxAdd.getSelectionModel().getSelectedItem()).append("'");
            
            
            DBConnection.getStatement().executeUpdate("select add_client(" + sql_insert.toString() + ")");
            DBConnection.closeConnection();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            
            PopUpWindow.Information("CLIENT ADDED", "You have added successfully a client");
        } 
        catch(SQLException ex){
                if(!ex.getSQLState().equals("0100E"))
                    PopUpWindow.Information("CLIENT ADDED ERROR", ex.getLocalizedMessage());
                else{
                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                    PopUpWindow.Information("CLIENT ADDED", "You have added successfully a client");
                }
            }
            catch(Exception ex){
                PopUpWindow.Information("CLIENT ADDED ERROR", ex.getLocalizedMessage());
            }
    }
	
	//Cancel button, Closes Window
	public void btnCancelAddOnAction(ActionEvent event) throws IOException{
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}

    
        //This method creates a unique id for the newly created client
        private int createId(){
            Random rand = new Random();
            int id;
            ObservableList<ClientListModel> list = this.getParent().getClientTableList();
            
            do{
                id = rand.nextInt(2000000000);
            }while(idFound(list,id));
            return id;
        }
	
        
        //This method checks if the randomly craeted id exists
        private boolean idFound(ObservableList<ClientListModel> list, int id){
            for(int i = 0; i < list.size(); i++)
                if(list.get(i).getId().equals(String.valueOf(id)))
                    return true;
            return false;
        }
        
        //This method checks if there is data in the text fields
        private boolean inputIsEmpty(){
            String name = nameTextFieldAdd.getText();
            String surname = surnameTextFieldAdd.getText();
            String phone = phoneTextFieldAdd.getText();
            
            nameTextFieldAdd.setText(name.trim());
            surnameTextFieldAdd.setText(surname.trim());
            phoneTextFieldAdd.setText(phone.trim());
            System.out.println(" phone trim = " + phone.trim());
            
            return name.equals("") || surname.equals("") || phone.equals("");
        }
        
        //This method checks if the phone value is number
        private boolean phoneIsNumber(){
            try{
                Double.parseDouble(phoneTextFieldAdd.getText());  
            }
            catch(NumberFormatException ex){
                return false;
            }
            catch(Exception ex){
                
                return false;
            }
            return true;
        }
        
        
}
