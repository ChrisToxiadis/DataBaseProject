package test_for_Jim;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author chris
 */
public class ChoiceBoxesTesting {
    
    
    
    @FXML
    private ChoiceBox<String> brandBox;
    
            
            ObservableList<String> list1 = FXCollections.observableArrayList();
    
    
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        list1.add("1"); //Logger.getLogger(CarInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        //Logger.getLogger(CarInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        list1.add("2");
        list1.add("3");
        brandBox.setItems(list1);
        
        brandBox.getSelectionModel().selectFirst();
        
        
        // add Listeners for Choice Boxes
        setChoiceBoxListeners();
    }
    
    
    
    
    
    //Set listners for Choice boxes
    private void setChoiceBoxListeners(){
        brandBox.getSelectionModel().selectedItemProperty().addListener((ev,oldValue, newValue) -> btnDeleteCarOnAction(newValue));
    }
    
    @FXML
    public void btnDeleteCarOnAction(String value){
       brandBox.getSelectionModel().clearSelection(); //Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        //    Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
       //deleter(list1);
       list1.add("32");
       brandBox.setItems(list1);
       brandBox.getSelectionModel().select("2");
       brandBox.getSelectionModel().selectLast();
       while(list1.size() > 0)
          list1.remove(0);
        
        brandBox.getSelectionModel().select(2);
        brandBox.getSelectionModel().clearSelection();
        
        list1.remove(2);
    }
    
    
    
    private void deleter(ObservableList<String> list){
        while(list.size() > 0)
            list.remove(0);
    }
}