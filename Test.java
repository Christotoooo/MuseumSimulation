package ProjectWithGUI;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Database db=Database.getInstance();
		UserInterface gui = new UserInterface(db); 
		Thread t=new Thread(gui);
		Runtime.getRuntime().addShutdownHook(new Hook(db));
		t.start();
	}

}