/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.client_list;

import data_base_interface.DBConnection;
import home.car_inventory.CarInventoryController;
import home.car_inventory.CarInventoryModel;
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

/**
 *
 * @author chris
 */
public class ClientListController implements Initializable{
    @FXML
    private TableView clientTable;
    
    @FXML
    private TableColumn<ClientListModel, String> nameColumn; 
    @FXML
    private TableColumn<ClientListModel, String> surnameColumn;
    @FXML
    private TableColumn<ClientListModel, String> phoneColumn;
    @FXML
    private TableColumn<ClientListModel, String> countryColumn;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //throw new UnsupportedOperationException("Not supported yet.");
            
            ObservableList<ClientListModel> list = FXCollections.observableArrayList();
            
            ResultSet res = DBConnection.getStatement().executeQuery("select getTable2() as getTable");
            Vector<String[]> vector = DBConnection.loadDBinVector(res, "getTable");
            for(int i = 0; i < vector.size(); i++){
                int j = 1;
                list.add(new ClientListModel(
                        vector.elementAt(i)[j++],
                        vector.elementAt(i)[j++],
                        vector.elementAt(i)[j++],
                        vector.elementAt(i)[j++]
                ));
            }
            
            DBConnection.closeConnection();
            
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
            countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
            clientTable.setItems(list);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CarInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
