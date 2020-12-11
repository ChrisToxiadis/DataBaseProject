package home.car_inventory.pop_up;

import data_base_interface.DBConnection;
import home.car_inventory.CarInventoryModel;
import home.create_observable_list.ObservableListCreator;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.PopUpController;

public class AddCarController extends PopUpController implements Initializable{
    
    @FXML
    private TextField brandTextFieldAdd;
    @FXML
    private TextField modelTextFieldAdd;
    @FXML
    private ChoiceBox<String> colorBoxAdd;
    @FXML
    private TextField priceTextFieldAdd;
    @FXML
    private ChoiceBox<String> conditionBoxAdd;


	@FXML
	private TextField idTextFieldAdd;
	
	int random_id;
    
    
    private ObservableList<String> list1;
    private ObservableList<String> list2;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
       // initializeChoiceBox();
        System.out.print(true);
        String[] color_array = {"Silver", "Black", "White", "Red", "Blue"};
        String[] condition_array = {"New", "Used"};
        
        list1 = FXCollections.observableArrayList();
        list2 = FXCollections.observableArrayList();
        
        for(int i = 0; i < color_array.length; i++)
            list1.add(color_array[i]);
        colorBoxAdd.setItems(list1);
        for(int i = 0; i < condition_array.length; i++)
            list2.add(condition_array[i]);
        conditionBoxAdd.setItems(list2);
        colorBoxAdd.getSelectionModel().selectFirst();
        conditionBoxAdd.getSelectionModel().selectFirst();
    }
    
    public AddCarController(){
       // initializeChoiceBox();
    }
    
    
    private void initializeChoiceBox(){
        //System.out.print(true);
        String[] color_array = {"Silver", "Black", "White", "Red", "Blue"};
        String[] condition_array = {"Silver", "Black", "White", "Red", "Blue"};
      //  list1 = list_creator1.getObservableList(color_array);
       // list2 = list_creator2.getObservableList(condition_array);
    /*    colorBoxAdd.setItems(list1);
        conditionBoxAdd.setItems(list2);
    */}

	//Add Car Button Handler
	public void btnAddCarAddOnAction(ActionEvent event) throws IOException, ClassNotFoundException{
        try{
            StringBuilder sql_insert = new StringBuilder();
            
            sql_insert.append(createId()).append(",");
            sql_insert.append("'").append(brandTextFieldAdd.getText()).append("',");
            sql_insert.append("'").append(modelTextFieldAdd.getText()).append("',");
            sql_insert.append("'").append(colorBoxAdd.getSelectionModel().getSelectedItem()).append("',");
            sql_insert.append("'").append(priceTextFieldAdd.getText()).append("',");
            sql_insert.append("'").append(conditionBoxAdd.getSelectionModel().getSelectedItem()).append("'");
            System.out.println("sql insert is " + sql_insert.toString());
            DBConnection.getStatement().executeUpdate("select add_car(" + sql_insert.toString() + ")");
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
        
        
        
        private int createId(){
            Random rand = new Random();
            int id;
            ObservableList<CarInventoryModel> list = this.getParent().getCarTableList();
            
            do{
                id = rand.nextInt(2000000000);
            }while(idFound(list,id));
            return id;
        }
	
        
        private boolean idFound(ObservableList<CarInventoryModel> list, int id){
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