package util;
import home.HomeController;
import home.car_inventory.CarInventoryModel;
import home.client_list.ClientListModel;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Controller {
	private HomeController HomeController;
	

	public void setHomeController(HomeController homeController){
		HomeController = homeController;
	}
	public HomeController getHomeController() {
		return HomeController;		
	}
	public void InitializeController() {
		
	}
	
	public TableView getTable() {
		System.out.println("I DONT! override the method@");
		return null;
		
	}
        
        public ObservableList<CarInventoryModel> getCarTableList(){
            return null;
        }
        
        public ObservableList<ClientListModel> getClientTableList(){
            return null;
        }
        
        public String[] getRow(){
            System.out.println("I DONT!4 override the method@");
            return null;
        }
}
