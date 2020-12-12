package home.car_inventory;

import util.ObservableListCreator;
import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import util.Controller;
import util.DBConnection;
import util.FxmlPath;
import util.PopUpWindow;




public class CarInventoryController extends Controller implements Initializable{
    @FXML
    private TableView carTable;
    
    @FXML
    private TableColumn<CarInventoryModel,Integer> idColumn;
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
    
    @FXML
    private ChoiceBox<String> brandBox;
    @FXML
    private ChoiceBox<String> modelBox;
    @FXML
    private ChoiceBox<String> colorBox;
    @FXML
    private ChoiceBox<String> priceBox;
    @FXML
    private ChoiceBox<String> conditionBox;
    
    private ObservableListCreator list_creator1;
    private ObservableListCreator list_creator2;
    private ObservableListCreator list_creator3;
    private ObservableListCreator list_creator4;
    private ObservableListCreator list_creator5;
    
    private ObservableList<String> list1;
    private ObservableList<String> list2;
    private ObservableList<String> list3;
    private ObservableList<String> list4;
    private ObservableList<String> list5;
    
    private ObservableList<CarInventoryModel> list = FXCollections.observableArrayList();
    
    private String[][] array = new String[5][2];
    private final String DEFAULT_CHOICE_BOX_VALUE = "All";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initializeArray(array);
            initializeCarTable();
            initializeChoiceBox();
            setChoiceBoxListeners();   
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    
    
    private void initializeChoiceBox() throws ClassNotFoundException, SQLException{
        list_creator1 = new ObservableListCreator();
        list_creator2 = new ObservableListCreator();
        list_creator3 = new ObservableListCreator();
        list_creator4 = new ObservableListCreator();
        list_creator5 = new ObservableListCreator();
            
        //Create Choice box for brand
        list1 = list_creator1.getObservableList(DBConnection.getStatement().executeQuery("select getBrands() as Brands"), "Brands");
        DBConnection.closeConnection();
        brandBox.setItems(list1);
            
        //Create Choice box for model
        list2 = list_creator2.getObservableList(DBConnection.getStatement().executeQuery("select getModels() as Models"), "Models");
        DBConnection.closeConnection();
        modelBox.setItems(list2);
           
        //Create Choice box for color
        list3 = list_creator3.getObservableList(DBConnection.getStatement().executeQuery("select getColors() as Colors"), "Colors");
        DBConnection.closeConnection();
        colorBox.setItems(list3);
            
        //Create Choice box for price
        list4 = list_creator4.getObservableList(DBConnection.getStatement().executeQuery("select getPrices() as Prices"), "Prices");
        DBConnection.closeConnection();
        priceBox.setItems(list4);
            
        //Create Choice box for condition
        list5 = list_creator5.getObservableList(DBConnection.getStatement().executeQuery("select getConditions() as Conditions"), "Conditions");
        DBConnection.closeConnection();
        conditionBox.setItems(list5);
            
        //give default values in choice boxes
        brandBox.getSelectionModel().selectFirst();
        modelBox.getSelectionModel().selectFirst();
        colorBox.getSelectionModel().selectFirst();
        priceBox.getSelectionModel().selectFirst();
        conditionBox.getSelectionModel().selectFirst();
    }
    
    
    private void initializeCarTable() throws ClassNotFoundException, SQLException{
        ResultSet res = DBConnection.getStatement().executeQuery("select getTable1() as getTable");
            Vector<Vector<String>> vector = DBConnection.loadDBinVector(res, "getTable");
            for(int i = 0; i < vector.size(); i++){
                int j = 0;
                list.add(new CarInventoryModel(
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++)
                ));
            }
            DBConnection.closeConnection();
            
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
            modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
            colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
            carTable.setItems(list);
    }
    
    
    //Set listners for Choice boxes
    private void setChoiceBoxListeners(){
        brandBox.getSelectionModel().selectedItemProperty().addListener((ev,oldValue, newValue) -> createsSQLQuery(newValue,0));
        modelBox.getSelectionModel().selectedItemProperty().addListener((ev,oldValue, newValue) -> createsSQLQuery(newValue,1));
        colorBox.getSelectionModel().selectedItemProperty().addListener((ev,oldValue, newValue) -> createsSQLQuery(newValue,2));
        priceBox.getSelectionModel().selectedItemProperty().addListener((ev,oldValue, newValue) -> createsSQLQuery(newValue,3));
        conditionBox.getSelectionModel().selectedItemProperty().addListener((ev,oldValue, newValue) -> createsSQLQuery(newValue,4));
    }
    
    //This method uses the sql query from getSQLQuery in order to retrieve data from data base and then shows it on car Inventory table
    private void createsSQLQuery(String value, int position){
        try {
            //Delete elements from car table
            while(list.size() > 0 )
                list.remove(0);
            carTable.setItems(list);
            
            //Retrieve data from data base
            String sql_query = getSQLQuery(value,position);;
            ResultSet res = DBConnection.getStatement().executeQuery(sql_query);
            Vector<Vector<String>> vector = DBConnection.loadDBinVector(res, "select_brand");
            
            //Shows teh results in table
            for(int i = 0; i < vector.size(); i++){
                int j = 0;
                list.add(new CarInventoryModel(
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++),
                        vector.elementAt(i).elementAt(j++)
                ));
            }
            carTable.setItems(list);
            DBConnection.closeConnection();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CarInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //This method craetes dynamically an sql query based on the filter choices made by user
    private String getSQLQuery(String value, int position){
        if(position == 3 && !value.equals(DEFAULT_CHOICE_BOX_VALUE)) //The element is numeric and not string
            array[position][1] = value;
        else if(!value.equals(DEFAULT_CHOICE_BOX_VALUE)) //The element is string so it needs ' ' 
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
            sql_query.append(") as select_brand intersect ");
        }
        return sql_query.length() > 0 ? sql_query.substring(0, sql_query.length() - 10) : "select getTable1() as select_brand"; // Because the word intersect contains 9 letters + 1 for the space
    }
    
    
    //This method Initializes the array that stores sql queries
    private void initializeArray(String[][] array){
        for(int i = 0; i < array.length; i++)
            for(int j = 0; j < array[i].length; j++)
                array[i][j] = j != 1 ? new String("") : new String(DEFAULT_CHOICE_BOX_VALUE);
        
        array[0][0] = "select select_brand(";
        array[1][0] = "select select_model(";
        array[2][0] = "select select_color(";
        array[3][0] = "select select_price(";
        array[4][0] = "select select_condition(";
    }
    
    
    



        //Add Car Button Action, Opens Add Car Window
	public void btnAddCarOnAction(ActionEvent event) throws IOException{
		PopUpWindow.NewBorderPaneWindow("Adding Car", FxmlPath.addCarFXML, StageStyle.DECORATED, Modality.APPLICATION_MODAL, false, this.getHomeController(), this);
	}
    
	//Edit Car Button Action. Opens Edit Car Window
	public void btnEditCarOnAction(ActionEvent event) throws IOException{
            if(carTable.getSelectionModel().getSelectedIndex() < 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("NOTHING IS SELECTED");
                alert.setHeaderText("you have to select a row first");
                alert.showAndWait();
        }
            else
		PopUpWindow.NewBorderPaneWindow("Editing Car", FxmlPath.editCarFXML, StageStyle.DECORATED, Modality.APPLICATION_MODAL, true, this.getHomeController(), this);
	}
        
    @FXML
    public void btnDeleteCarOnAction(ActionEvent ev){
        try {
            if(carTable.getSelectionModel().getSelectedIndex() >= 0){
              CarInventoryModel  row = (CarInventoryModel) carTable.getSelectionModel().getSelectedItem();
              DBConnection.getStatement().executeQuery("select delete_car(" + row.getId() + ")");
              DBConnection.closeConnection();
              this.getHomeController().LoadCarInv(Boolean.FALSE);
              PopUpWindow.Information("CAR DELETION", "You have deleted successfully a car");
            }
            else
                PopUpWindow.Information("NOTHING IS SELECTED", "You have to select a row first");
        }
        catch(SQLException ex){
            PopUpWindow.Information("CAR DELETION ERROR", ex.getLocalizedMessage());
        }
        catch(Exception ex){
            PopUpWindow.Information("CAR DELETION ERROR", ex.getLocalizedMessage());
        }
    }


	//Add chosen car to selection
	public void btnSelectOnAction(ActionEvent event) throws IOException{
            CarInventoryModel row;
            if(carTable.getSelectionModel().getSelectedIndex() < 0)
                PopUpWindow.Information("NOTHING IS SELECTED", "You have to select a row first");
            else{
                row = (CarInventoryModel) carTable.getSelectionModel().getSelectedItem();
		this.getHomeController().setSelectedCar(row.getId());
            }
	}
        
        
     @Override
    public ObservableList<CarInventoryModel> getCarTableList(){
        return list;
    }
    
    @Override
	public TableView getTable() {
		return carTable;
	}
    
        
    //This method returns the selected line data
    @Override
    public String[] getRow(){
        CarInventoryModel row = getSelectedRow();
        
        String[] array = new String[6];
            
            array[0] = row.getId();
            array[1] = row.getBrand();
            array[2] = row.getModel();
            array[3] = row.getColor();
            array[4] = row.getPrice();
            array[5] = row.getCondition();
            return array;
        }
    
    //This method returns the selected line
    private CarInventoryModel getSelectedRow(){
        CarInventoryModel row;
        if(carTable.getSelectionModel().getSelectedIndex() >= 0)
            row = (CarInventoryModel) carTable.getSelectionModel().getSelectedItem();
        else
            row = null;
        return row;
    }
    
}