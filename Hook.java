package ProjectWithGUI;

public class Hook extends Thread {
	
	Database db;
	
	Hook(Database db){
		this.db=db;
	}
	
	public void run(){
		db.quit();
		System.out.println("Application terminated");
	}

}