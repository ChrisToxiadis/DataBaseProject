package home.client_list.pop_up;

import data_base_interface.DBConnection;
import home.client_list.ClientListModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.PopUpController;

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


	@FXML
	private TextField idTextFieldAdd;
	
	int random_id;
	
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
	
	//Cancel button, Closes Window
	public void btnCancelAddOnAction(ActionEvent event) throws IOException{
            getTable();
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}

    
        
        private int createId(){
            Random rand = new Random();
            int id;
            ObservableList<ClientListModel> list = this.getParent().getClientTableList();
            
            do{
                id = rand.nextInt(2000000000);
            }while(idFound(list,id));
            return id;
        }
	
        
        private boolean idFound(ObservableList<ClientListModel> list, int id){
            for(int i = 0; i < list.size(); i++)
                if(list.get(i).getId().equals(String.valueOf(id)))
                    return true;
            return false;
        }
        
        
        


//Generate Random ID
	public void btnRandomOnAction(ActionEvent event) throws IOException{
	
		Random rand = new Random(); 
		random_id = rand.nextInt(10000); 
		
		idTextFieldAdd.setText( String.valueOf(random_id));	
	}
	
}
