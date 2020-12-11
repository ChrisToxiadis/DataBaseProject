package home.transaction;

import data_base_interface.DBConnection;
import home.car_inventory.CarInventoryController;
import home.create_observable_list.ObservableListCreator;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import util.Controller;
import util.FxmlPath;
import util.PopUpWindow;


public class TransactionController extends Controller implements Initializable{
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
    @FXML
    private DatePicker dateBox;

	@FXML
	private Button btnAddTransaction;

    
    private ObservableList<TransactionModel> list = FXCollections.observableArrayList();
    private final String DEFAULT_CHOICE_BOX_VALUE = "All";
    private String[][] array = new String[5][2];
    ObservableListCreator list_creator1 = new ObservableListCreator();
            ObservableListCreator list_creator2 = new ObservableListCreator();
            ObservableListCreator list_creator3 = new ObservableListCreator();
            
            ObservableList<String> list1;
            ObservableList<String> list2;
            ObservableList<String> list3;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            initializeArray(array);
            
            
            
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
            list1 = list_creator1.getObservableList(DBConnection.getStatement().executeQuery("select getTransactionBrands() as Brands"), "Brands");
            DBConnection.closeConnection();
            brandBox.setItems(list1);
            
            //Create Choice box for color
            list2 = list_creator2.getObservableList(DBConnection.getStatement().executeQuery("select getTransactionColors() as Colors"), "Colors");
            DBConnection.closeConnection();
            colorBox.setItems(list2);
            
            //Create Choice box for country
            list3 = list_creator3.getObservableList(DBConnection.getStatement().executeQuery("select getTransactionCountries() as Countries"), "Countries");
            DBConnection.closeConnection();
            countryBox.setItems(list3);
            
            
            //give default values in choice boxes
            brandBox.getSelectionModel().selectFirst();
            colorBox.getSelectionModel().selectFirst();
            countryBox.getSelectionModel().selectFirst();
            
          //  brandBox.getSelectionModel().clearSelection();
            
            // add Listeners for Choice Boxes
            setChoiceBoxListeners();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CarInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //This is the listner for the text field. It is invoked when enter pressed
    @FXML
    public void setTextFieldListeners(ActionEvent ev){
       if(phoneTextField.getText().equals(""))
           createsSQLQuery(DEFAULT_CHOICE_BOX_VALUE,4);
       else
           createsSQLQuery(phoneTextField.getText(),4);
    }
    
    
    //Set listners for Choice boxes
    private void setChoiceBoxListeners(){
        brandBox.getSelectionModel().selectedItemProperty().addListener((ev,oldValue, newValue) -> createsSQLQuery(newValue,1));
        colorBox.getSelectionModel().selectedItemProperty().addListener((ev,oldValue, newValue) -> createsSQLQuery(newValue,2));
        countryBox.getSelectionModel().selectedItemProperty().addListener((ev,oldValue, newValue) -> createsSQLQuery(newValue,3));
    }
    
    
    public void setDatePicker(ActionEvent ev){
        if(dateBox.getValue() != null){
            String date = dateBox.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            createsSQLQuery(date, 0);
        }
        else
            createsSQLQuery(DEFAULT_CHOICE_BOX_VALUE,0);
    }
    
    
    //This method uses the sql query from getSQLQuery in order to retrieve data from data base and then shows it on car Inventory table
    private void createsSQLQuery(String value, int position){
        try {
            //Delete elements from car table
            while(list.size() > 0 )
                list.remove(0);
            transactionTable.setItems(list);
            
            //Retrieve data from data base
            String sql_query = getSQLQuery(value,position);
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
        
        array[0][0] = "select select_date(";
        array[1][0] = "select select_transaction_brand(";
        array[2][0] = "select select_transaction_color(";
        array[3][0] = "select select_transaction_country(";
        array[4][0] = "select select_phone(";
    }
    
    
    @FXML
    public void btnDeleteCarOnAction(ActionEvent ev){
        try {
            if(transactionTable.getSelectionModel().getSelectedIndex() >= 0){
              TransactionModel  row = (TransactionModel) transactionTable.getSelectionModel().getSelectedItem();
              ResultSet res = DBConnection.getStatement().executeQuery("select delete_transaction(" + row.getCar_id() + ","
                    + row.getCustomer_id() + ")");
              DBConnection.closeConnection();
           //   brandBox.getSelectionModel().clearSelection();
              
              deleter(list1);
              deleter(list2);
              deleter(list3);
              
            //This setters will be in a method
            //Create Choice box for brand
            list1 = list_creator1.getObservableList(DBConnection.getStatement().executeQuery("select getTransactionBrands() as Brands"), "Brands");
            DBConnection.closeConnection();
            brandBox.setItems(list1);
            
            //Create Choice box for color
            list2 = list_creator2.getObservableList(DBConnection.getStatement().executeQuery("select getTransactionColors() as Colors"), "Colors");
            DBConnection.closeConnection();
            colorBox.setItems(list2);
            
            //Create Choice box for country
            list3 = list_creator3.getObservableList(DBConnection.getStatement().executeQuery("select getTransactionCountries() as Countries"), "Countries");
            DBConnection.closeConnection();
            countryBox.setItems(list3);
            
            
            
            for(int i = 0; i < array.length; i++)
                if(array[i][1].startsWith("'"))
                    array[i][1] = array[i][1].substring(1, array[i][1].length()-1);
            
            array[1][1] = name_checker(list1,array[1][1]);
            array[2][1] = name_checker(list2,array[2][1]);
            array[3][1] = name_checker(list3,array[3][1]);
            
            
            //for(int i = 0; i < array.length; i++)
                createsSQLQuery(array[0][1], 0);
            
            
            
            
       //     for(int i = 0; i < array.length; i++){
                
                
                //if(array[1][1] = name_checker(list1,array[1][1]))
                
                //brandBox.getSelectionModel().select(array[1][1]);
              ///  colorBox.getSelectionModel().select(array[2][1]);
              ///  countryBox.getSelectionModel().select(array[3][1]);
              
              brandBox.getSelectionModel().selectLast();
              colorBox.getSelectionModel().selectLast();
              countryBox.getSelectionModel().selectLast();
              
              int counter = 0;
          /*    while((counter == 0 || !list1.get(counter).equals(DEFAULT_CHOICE_BOX_VALUE)) && counter < list1.size()){
                  list1.remove(0);
                  counter++;
              }*/
              
                  
          /*        do{
                  list1.remove(0);
              }while
            */  
              
         //   }
              
              ////I have to write cleaner code. I MUST write seperate methods to initiallize etc. in this methods the bugs are many.
              
              /**
               Choice boxes are not loading dynamically. Also if a combination of them givesome results and then
               * the results get deleted although the filter is not cleared the table will load all the other 
               * not deleted rows even if they are not the same values as choice box. Also text Fields have to cleared
               *
               */
              
              
              
              
          /*      while(list.size() > 0)
                  list.remove(0);
                DBConnection.closeConnection();
                res = DBConnection.getStatement().executeQuery("select getTable3() as getTable");
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
            }*/
            }
            else
                System.out.println("Exception");
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    private void deleter(ObservableList<String> list){
        while(list.size() > 0)
            list.remove(0);
    }
    
    
    private String name_checker(ObservableList<String> list, String name){
        for(int i = 0; i < list.size(); i++)
            if(name.equals(list.get(i)) && !name.equals(DEFAULT_CHOICE_BOX_VALUE))
                return new String("'" + name + "'");
        return DEFAULT_CHOICE_BOX_VALUE;
    }
    
    
    
    //Add Transaction Button Action, Opens the Add Car Pop-Up Window
	public void btnAddTransactionOnAction(ActionEvent event) throws IOException{
		PopUpWindow.NewBorderPaneWindow("Adding Transaction", FxmlPath.addTransactionFXML, StageStyle.DECORATED, Modality.APPLICATION_MODAL, true, this.getHomeController(), this);
	} 
	
	//Edit Transaction Button Action, Opens the Edit Car Pop-Up Window
	public void btnEditTransactionOnAction(ActionEvent event) throws IOException{
		PopUpWindow.NewBorderPaneWindow("Editing Transaction", FxmlPath.editTransactionFXML, StageStyle.DECORATED, Modality.APPLICATION_MODAL, true, this.getHomeController(), this);
	}
        
        
        
            @Override
    public String[] getRow(){
        TransactionModel row = getSelectedRow();
      
        String[] array = new String[3];
            
            array[0] = row.getCar_id();
            array[1] = row.getCustomer_id();
            array[2] = row.getDate();
            return array;
        }
    
    
    private TransactionModel getSelectedRow(){
        TransactionModel row;
        if(transactionTable.getSelectionModel().getSelectedIndex() >= 0)
            row = (TransactionModel) transactionTable.getSelectionModel().getSelectedItem();
        else
            row = null;
        System.out.println(row);
        return row;
    }



@Override
	public void InitializeController() {
		String car = this.getHomeController().getSelectedCar();
		String client = this.getHomeController().getSelectedClient();
		
		if(car.equals("none") || client.equals("none"))
                    btnAddTransaction.setDisable(true);
		else
                    btnAddTransaction.setDisable(false);
	}
    
}