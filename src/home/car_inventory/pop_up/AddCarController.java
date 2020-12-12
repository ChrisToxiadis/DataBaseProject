package home.car_inventory.pop_up;

import home.car_inventory.CarInventoryModel;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.DBConnection;
import util.PopUpController;
import util.PopUpWindow;

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
    
    
    private ObservableList<String> list1;
    private ObservableList<String> list2;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        initializeChoiceBox();
    }
    
    public AddCarController(){
        
    }
    
    
    private void initializeChoiceBox(){
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

	//Add Car Button Handler
	public void btnAddCarAddOnAction(ActionEvent event) throws IOException, ClassNotFoundException{
        try{
            if(inputIsEmpty()){
                PopUpWindow.Information("Wrong Input", "You have to fill in all the fields");
                return;
            }
            
            if(!priceIsNumber()){
                PopUpWindow.Information("Wrong Input", "Price field takes only numbers");
                return;
            }
            
            StringBuilder sql_insert = new StringBuilder();
            
            sql_insert.append(createId()).append(",");
            sql_insert.append("'").append(brandTextFieldAdd.getText()).append("',");
            sql_insert.append("'").append(modelTextFieldAdd.getText()).append("',");
            sql_insert.append("'").append(colorBoxAdd.getSelectionModel().getSelectedItem()).append("',");
            sql_insert.append("'").append(priceTextFieldAdd.getText()).append("',");
            sql_insert.append("'").append(conditionBoxAdd.getSelectionModel().getSelectedItem()).append("'");
            System.out.println("sql insert is " + sql_insert.toString());
            DBConnection.getStatement().executeUpdate("select add_car(" + sql_insert.toString() + ")");
            
            DBConnection.closeConnection();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            PopUpWindow.Information("CAR ADDED", "You have added successfully a car");
        }
            catch(SQLException ex){
                if(!ex.getSQLState().equals("0100E"))
                    PopUpWindow.Information("CAR ADDED ERROR", ex.getLocalizedMessage());
                else{
                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                    PopUpWindow.Information("CAR ADDED", "you have added successfully a car");
                }
            }
            catch(Exception ex){
                PopUpWindow.Information("CAR ADDED ERROR", ex.getLocalizedMessage());
            }
        }
	
	//Cancel button, Closes Window
	public void btnCancelAddOnAction(ActionEvent event) throws IOException{
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }
        
        
        //This method creates a unique id for the newly created car
        private int createId(){
            Random rand = new Random();
            int id;
            ObservableList<CarInventoryModel> list = this.getParent().getCarTableList();
            do{
                id = rand.nextInt(2000000000);
            }while(idFound(list,id));
            return id;
        }
	
        
        //This method checks if the randomly created id already exists
        private boolean idFound(ObservableList<CarInventoryModel> list, int id){
            for(int i = 0; i < list.size(); i++)
                if(list.get(i).getId().equals(String.valueOf(id)))
                    return true;
            return false;
        }
        
        //This method checks if there is at least one empty text field when add button is pressed
        private boolean inputIsEmpty(){
            String brand = brandTextFieldAdd.getText();
            String model = modelTextFieldAdd.getText();
            String price = priceTextFieldAdd.getText();
            
            brandTextFieldAdd.setText(brand.trim());
            modelTextFieldAdd.setText(model.trim());
            priceTextFieldAdd.setText(price.trim());
            
            return brand.equals("") || model.equals("") || price.equals("");
        }
        
        //This method check if the value in textfield price is a number
        private boolean priceIsNumber(){
            try{
                Double.parseDouble(priceTextFieldAdd.getText());  
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