package home.transaction;

import data_base_interface.DBConnection;
import home.car_inventory.CarInventoryController;
import home.client_list.ClientListModel;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class TransactionController implements Initializable{
    @FXML
    private TableView transactionTable;
    
    @FXML
    private TableColumn<TransactionModel, String> brandColumn;
    @FXML
    private TableColumn<TransactionModel, String> modelColumn;
    @FXML
    private TableColumn<TransactionModel, String> colorColumn;
    @FXML
    private TableColumn<TransactionModel, String> conditionColumn;
    @FXML
    private TableColumn<TransactionModel, String> nameColumn;
    @FXML
    private TableColumn<TransactionModel, String> surnameColumn;
    @FXML
    private TableColumn<TransactionModel, String> dateColumn;
    @FXML
    private TableColumn<TransactionModel, String> priceColumn;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            //throw new UnsupportedOperationException("Not supported yet.");
            
            ObservableList<TransactionModel> list = FXCollections.observableArrayList();
            
            ResultSet res = DBConnection.getStatement().executeQuery("select getTable3() as getTable");
            Vector<String[]> vector = DBConnection.loadDBinVector(res, "getTable");
            for(int i = 0; i < vector.size(); i++){
                int j = 0;
                list.add(new TransactionModel(
                        vector.elementAt(i)[j++],
                        vector.elementAt(i)[j++],
                        vector.elementAt(i)[j++],
                        vector.elementAt(i)[j++],
                        vector.elementAt(i)[j++],
                        vector.elementAt(i)[j++],
                        vector.elementAt(i)[j++],
                        vector.elementAt(i)[j++]
                ));
            }
            
            DBConnection.closeConnection();
            brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
            modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
            colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
            conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            transactionTable.setItems(list);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CarInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}