package home.car_inventory.pop_up;

import data_base_interface.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.PopUpController;

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
    //    row_array = getRow();
      //      if(row_array == null)
        //        System.out.println("$#($&#@*($&#*(@$&#@*($&@#*($&#@*($&#@*($&#@(*$&#@*($&@#*(");
    }

    
    @Override
    public void InitializeController(){
        System.out.println("$#($&#@*($&#*(@$&#@*($&@#*($&#@*($&#@*($&#@(*$&#@*($&@#*(");
        row_array = this.getParent().getRow();
        initialize();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //row_array = super.super.getParent().getRow();
        String[] color_array = {"Silver", "Black", "White", "Red", "Blue"};
        String[] condition_array = {"New", "Used"};
        
        
        list1 = FXCollections.observableArrayList();
        list2 = FXCollections.observableArrayList();
        
        
        for(int i = 0; i < color_array.length; i++)
            list1.add(color_array[i]);
        for(int i = 0; i < condition_array.length; i++)
            list2.add(condition_array[i]);
        if(list1 ==null)
            System.exit(343);
        
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
            else{
                try {
                    StringBuilder sql_update = new StringBuilder();
                    sql_update.append(row_array[0]).append(",");
                    sql_update.append("'").append(input_array[0]).append("',");
                    sql_update.append("'").append(input_array[1]).append("',");
                    sql_update.append("'").append(input_array[2]).append("',");
                    sql_update.append("'").append(input_array[3]).append("',");
                    sql_update.append("'").append(input_array[4]).append("'");
                   
        /*           sql_update.append(row_array[0]).append(",");
                    sql_update.append("'").append(brandTextFieldAdd.getText()).append("',");
                    sql_update.append("'").append(modelTextFieldAdd.getText()).append("',");
                    sql_update.append("'").append(colorBoxAdd.getSelectionModel().getSelectedItem().toString()).append("',");
                    sql_update.append("'").append(priceTextFieldAdd.getText()).append("',");
                    sql_update.append("'").append(conditionBoxAdd.getSelectionModel().getSelectedItem().toString()).append("'");
                    */
                    DBConnection.getStatement().executeUpdate("select edit_car(" + sql_update.toString() + ")");
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
	
	//Cancel button, Closes Window
	public void btnCancelEditOnAction(ActionEvent event) throws IOException{
      // System.out.println("The table is:" + this.getParent().getTable());
       //System.out.println("The table is:" + this.getParent().getTable2());
        //String[] row_array = this.getParent().getRow();
       // if(row_array == null)
         //   System.out.println("Errorrrrrrrrr");
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
        
        
        
        
        
        private String[] input_toArray(){
            String[] array = new String[5];
            array[0] = brandTextFieldEdit.getText();
            array[1] = modelTextFieldEdit.getText();
            array[2] = colorBoxEdit.getSelectionModel().getSelectedItem();
            array[3] = priceTextFieldEdit.getText();
            array[4] = conditionBoxEdit.getSelectionModel().getSelectedItem();
            return array;
        }
        
        
        private boolean isChanged(String[] row_array, String[] input_array){
            for(int i = 0; i < input_array.length; i++)
                if(!row_array[i+1].equals(input_array[i]))
                    return true;
            return false;
        }

    
        
        private void initialize(){
            brandTextFieldEdit.setText(row_array[1]);
            modelTextFieldEdit.setText(row_array[2]);
            colorBoxEdit.getSelectionModel().select(row_array[3]);
            priceTextFieldEdit.setText(row_array[4]);
            conditionBoxEdit.getSelectionModel().select(row_array[5]);
        }
        
        
      /*  public void InitializeController() {
            if(row_array == null)
                System.out.println(" It is null over Here !!!!!!!!!!!!!!!!!!!!!");
            getRow1();
	}*/
	
}