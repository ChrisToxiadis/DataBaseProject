package home.transaction;

import data_base_interface.DBConnection;
import home.car_inventory.CarInventoryController;
import home.car_inventory.CarInventoryModel;
import home.client_list.ClientListModel;
import home.create_observable_list.ObservableListCreator;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class TransactionController implements Initializable{
    @FXML
    private TableView transactionTable;
    
    @FXML
    private TableColumn<TransactionModel, String> dateColumn;
    @FXML
    private TableColumn<TransactionModel, Integer> carIdColumn;
    @FXML
    private TableColumn<TransactionModel, String> brandColumn;
    @FXML
    private TableColumn<TransactionModel, String> modelColumn;
    @FXML
    private TableColumn<TransactionModel, String> colorColumn;
    @FXML
    private TableColumn<TransactionModel, String> conditionColumn;
    @FXML
    private TableColumn<TransactionModel, Double> priceColumn;
    @FXML
    private TableColumn<TransactionModel, Integer> clientIdColumn;
    @FXML
    private TableColumn<TransactionModel, String> nameColumn;
    @FXML
    private TableColumn<TransactionModel, String> surnameColumn;
    @FXML
    private TableColumn<TransactionModel, String> countryColumn;
    
    @FXML
    private ChoiceBox<String> brandBox;
    @FXML
    private ChoiceBox<String> colorBox;
    @FXML
    private ChoiceBox<String> countryBox;
    @FXML
    private TextField phoneTextField;
    
    private ObservableList<TransactionModel> list = FXCollections.observableArrayList();
    private final String DEFAULT_CHOICE_BOX_VALUE = "All";
    private String[][] array = new String[3][2];
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            initializeArray(array);
            
            ObservableListCreator list_creator1 = new ObservableListCreator();
            ObservableListCreator list_creator2 = new ObservableListCreator();
            ObservableListCreator list_creator3 = new ObservableListCreator();
            
            ObservableList<String> list1;
            ObservableList<String> list2;
            ObservableList<String> list3;
            
            ResultSet res = DBConnection.getStatement().executeQuery("select getTable3() as getTable");
            Vector<Vector<String>> vector = DBConnection.loadDBinVector(res, "getTable");
            for(int i = 0; i < vector.size(); i++){
                int j = 0;
                list.add(new TransactionModel(
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++)
                ));
            }
            DBConnection.closeConnection();
            
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            carIdColumn.setCellValueFactory(new PropertyValueFactory<>("car_id"));
            brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
            modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
            colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
            conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            clientIdColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
            countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
            
            transactionTable.setItems(list);
            
            
            //Create Choice box for brand
            list1 = list_creator1.getObserverList(DBConnection.getStatement().executeQuery("select getTransactionBrands() as Brands"), "Brands");
            DBConnection.closeConnection();
            brandBox.setItems(list1);
            
            //Create Choice box for color
            list2 = list_creator2.getObserverList(DBConnection.getStatement().executeQuery("select getTransactionColors() as Colors"), "Colors");
            DBConnection.closeConnection();
            colorBox.setItems(list2);
            
            //Create Choice box for country
            list3 = list_creator3.getObserverList(DBConnection.getStatement().executeQuery("select getTransactionCountries() as Countries"), "Countries");
            DBConnection.closeConnection();
            countryBox.setItems(list3);
            
            
            //give default values in choice boxes
            brandBox.getSelectionModel().selectFirst();
            colorBox.getSelectionModel().selectFirst();
            countryBox.getSelectionModel().selectFirst();
            
            // add Listeners for Choice Boxes
            setChoiceBoxListeners();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CarInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //Set listners for Choice boxes
    private void setChoiceBoxListeners(){
        brandBox.getSelectionModel().selectedItemProperty().addListener((ev,oldValue, newValue) -> createsSQLQuery(newValue,0));
        colorBox.getSelectionModel().selectedItemProperty().addListener((ev,oldValue, newValue) -> createsSQLQuery(newValue,1));
        countryBox.getSelectionModel().selectedItemProperty().addListener((ev,oldValue, newValue) -> createsSQLQuery(newValue,2));
    }
    
    //This method uses the sql query from getSQLQuery in order to retrieve data from data base and then shows it on car Inventory table
    private void createsSQLQuery(String value, int position){
        try {
            //Delete elements from car table
            while(list.size() > 0 )
                list.remove(0);
            transactionTable.setItems(list);
            
            //Retrieve data from data base
            String sql_query = getSQLQuery(value,position);;
            ResultSet res = DBConnection.getStatement().executeQuery(sql_query);
            Vector<Vector<String>> vector = DBConnection.loadDBinVector(res, "select_date");
            
            //Shows teh results in table
            for(int i = 0; i < vector.size(); i++){
                int j = 0;
                list.add(new TransactionModel(
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++)
                ));
            }
            transactionTable.setItems(list);
            DBConnection.closeConnection();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CarInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //This method craetes dynamically an sql query based on the filter choices made by user
    private String getSQLQuery(String value, int position){
        if(!value.equals(DEFAULT_CHOICE_BOX_VALUE)) //The element is string so it needs ' ' 
            array[position][1] = "'" + value + "'";
        else
            array[position][1] = DEFAULT_CHOICE_BOX_VALUE;//The element is the default (All). User didn't choose a filter option
        
        StringBuilder sql_query = new StringBuilder();
        
        //craetes dynamically an sql query based on the combined filter options
        for(int i = 0; i < array.length; i++){
            if(array[i][1].equals(DEFAULT_CHOICE_BOX_VALUE))
                continue;
            for(int j = 0; j < array[i].length; j++)
                sql_query.append(array[i][j]);
            sql_query.append(") as select_date intersect ");
        }
        return sql_query.length() > 0 ? sql_query.substring(0, sql_query.length() - 10) : "select getTable3() as select_date"; // Because the word intersect contains 9 letters + 1 for the space
    }
    
    
    //This method Initializes the array that stores sql queries
    private void initializeArray(String[][] array){
        for(int i = 0; i < array.length; i++)
            for(int j = 0; j < array[i].length; j++)
                array[i][j] = j != 1 ? new String("") : new String(DEFAULT_CHOICE_BOX_VALUE);
        
        array[0][0] = "select select_transaction_brand(";
        array[1][0] = "select select_transaction_color(";
        array[2][0] = "select select_transaction_country(";
    }
    

    
}