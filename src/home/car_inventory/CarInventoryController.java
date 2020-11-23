/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.car_inventory;

import data_base_interface.DBConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author chris
 */
public class CarInventoryController implements Initializable{
    @FXML
    private TableView carTable;
    //@FXML
    //private TableColumn<CarInventoryModel,Integer> idColumn;
    @FXML
    private TableColumn<CarInventoryModel,String> brandColumn;
    @FXML
    private TableColumn<CarInventoryModel,String> modelColumn;
    @FXML
    private TableColumn<CarInventoryModel,String> colorColumn;
    @FXML
    private TableColumn<CarInventoryModel,Double> priceColumn;
    @FXML
    private TableColumn<CarInventoryModel,String> conditionColumn;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //throw new UnsupportedOperationException("Not supported yet.");
            
            ObservableList<CarInventoryModel> list = FXCollections.observableArrayList();
            
            ResultSet res = DBConnection.getStatement().executeQuery("select getTable1() as getTable");
            Vector<String[]> vector = DBConnection.loadDBinVector(res, "getTable");
            for(int i = 0; i < vector.size(); i++){
                int j = 1;
                list.add(new CarInventoryModel(
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
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
            carTable.setItems(list);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CarInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}