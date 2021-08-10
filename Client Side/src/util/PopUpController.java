package util;

public class PopUpController extends Controller{
	private Controller Parent;
	
	public void setParent(Controller parent) {
		Parent = parent;
	}
	public Controller getParent() {
		
		return Parent;
		
	}
}
