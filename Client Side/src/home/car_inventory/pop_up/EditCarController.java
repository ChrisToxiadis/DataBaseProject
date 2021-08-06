package home.car_inventory.pop_up;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

public class EditCarController extends PopUpController implements Initializable{
    
    @FXML
    private TextField brandTextFieldEdit;
    @FXML
    private TextField modelTextFieldEdit;
    @FXML
    private ChoiceBox<String> colorBoxEdit;
    @FXML
    private TextField priceTextFieldEdit;
    @FXML
    private ChoiceBox<String> conditionBoxEdit;
    
    private ObservableList<String> list1;
    private ObservableList<String> list2;
    
    private String[] row_array;
    
    
    public EditCarController(){
        
    }

    
    @Override
    public void InitializeController(){
        row_array = this.getParent().getRow();
        initialize();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeChoiceBox();
    }
    
    
    private void initializeChoiceBox(){
        String[] color_array = {"Silver", "Black", "White", "Red", "Blue"};
        String[] condition_array = {"New", "Used"};
        list1 = FXCollections.observableArrayList();
        list2 = FXCollections.observableArrayList();
        
        for(int i = 0; i < color_array.length; i++)
            list1.add(color_array[i]);
        for(int i = 0; i < condition_array.length; i++)
            list2.add(condition_array[i]);;
        
        colorBoxEdit.setItems(list1);
        conditionBoxEdit.setItems(list2);
        
        colorBoxEdit.getSelectionModel().selectFirst();
        conditionBoxEdit.getSelectionModel().selectFirst();
    }
    
    
	//Apply Button Action Handler
	public void btnApplyEditOnAction(ActionEvent event) throws IOException, ClassNotFoundException{
            String[] input_array = input_toArray();
            if(!isChanged(row_array,input_array))
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            else if(!priceIsNumber())
                PopUpWindow.Information("Wrong Input", "Price field takes only numbers");
            else{
                try {
                    StringBuilder sql_update = new StringBuilder();
                    sql_update.append(row_array[0]).append(",");
                    sql_update.append("'").append(input_array[0]).append("',");
                    sql_update.append("'").append(input_array[1]).append("',");
                    sql_update.append("'").append(input_array[2]).append("',");
                    sql_update.append("'").append(input_array[3]).append("',");
                    sql_update.append("'").append(input_array[4]).append("'");
                    
                    DBConnection.getStatement().executeUpdate("select edit_car(" + sql_update.toString() + ")");
                    DBConnection.closeConnection();
                    
                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                    PopUpWindow.Information("CAR MODIFICATION", "You have modify successfully a car");
                    
                }
                catch(SQLException ex){
                    if(!ex.getSQLState().equals("0100E"))
                        PopUpWindow.Information("CAR MODIFICATION ERROR", ex.getLocalizedMessage());
                    else{
                        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                        PopUpWindow.Information("CAR MODIFICATION", "You have modify successfully a car");
                    }
                }
                catch(Exception ex){
                    PopUpWindow.Information("CAR MODIFICATION ERROR", ex.getLocalizedMessage());
                }
            }
        }
	
	//Cancel button, Closes Window
	public void btnCancelEditOnAction(ActionEvent event) throws IOException{
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
        
        
        //This method converts all the data from text fields to array
        private String[] input_toArray(){
            String[] array = new String[5];
            array[0] = brandTextFieldEdit.getText();
            array[1] = modelTextFieldEdit.getText();
            array[2] = colorBoxEdit.getSelectionModel().getSelectedItem();
            array[3] = priceTextFieldEdit.getText();
            array[4] = conditionBoxEdit.getSelectionModel().getSelectedItem();
            return array;
        }
        
        
        //This method checks if there is indeed any modification in the data
        private boolean isChanged(String[] row_array, String[] input_array){
            for(int i = 0; i < input_array.length; i++)
                if(!row_array[i+1].equals(input_array[i]))
                    return true;
            return false;
        }
        
        //This method check if the value in textfield price is a number
        private boolean priceIsNumber(){
            try{
                Double.parseDouble(priceTextFieldEdit.getText());  
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
            brandTextFieldEdit.setText(row_array[1]);
            modelTextFieldEdit.setText(row_array[2]);
            colorBoxEdit.getSelectionModel().select(row_array[3]);
            priceTextFieldEdit.setText(row_array[4]);
            conditionBoxEdit.getSelectionModel().select(row_array[5]);
        }
        
        
}