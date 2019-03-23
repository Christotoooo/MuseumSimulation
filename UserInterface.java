package ProjectWithGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;



public class UserInterface extends JFrame implements Runnable{

	private JPanel pvisitor;
	private JPanel pstaff;
	private Database db;
	
	static final int TABSIZE=20;  
	static final int BWIDTH = 250;
	static final int BLENGTH = 40;
	static final int FRAME_LENGTH=800;
	static final int FRAME_WIDTH=800;
	
	
	private static final String PWD = "COMP421G66";
    private JPasswordField password;
    private String typedPassword;
	
    
	UserInterface(Database db){
		
		this.db=db;
		
		pvisitor = new JPanel();
		pstaff = new JPanel();
		initVisitor();
		initStaff();
		
		JTabbedPane tp=new JTabbedPane();  
		tp.setBounds(0,0,FRAME_LENGTH,FRAME_WIDTH);  
	    tp.add("Visitor",pvisitor);  
	    tp.add("Staff",pstaff);    
	
	    add(tp);  
	    setSize(FRAME_LENGTH,FRAME_WIDTH);  
	    setLayout(null);  
	    setVisible(true);  
	    
	    tp.setFont(new Font("TimesRoman", Font.BOLD, TABSIZE));
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	}
	
	//creating the first view for visitor 
	
	void initVisitor(){
		
		pvisitor.removeAll();
		pvisitor.setLayout(null);
		
		JButton b1=new JButton("Search Activities");
		
		b1.setBounds(250, 280 , 300, 100);
		b1.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		b1.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchActivity();
			}
		});
		
		pvisitor.add(b1);
		
		repaint();
		revalidate();
		
	}
	


	@Override
	
	public void run() {
		// TODO Auto-generated method stub
		
	}

	void initStaff(){
		pstaff.removeAll();
		// Login button
		JButton login = new JButton("Login");
		login.setFont(new Font("TimesRoman", Font.BOLD, 36));
		login.setBounds(230,400,300,80);
		// Text label
		JLabel txt = new JLabel();
		txt.setText("Please Enter Password :");
		txt.setFont(new Font("TimesRoman", Font.BOLD, 48));
		txt.setBounds(100, 200, 600, 70);

		// Initialize password field
		ActionListener pwdListener = new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            char [] tempPass = password.getPassword();
	            typedPassword = new String(tempPass);
	
	            if (!typedPassword.equals(PWD)){
	                JOptionPane.showMessageDialog(null, "Your password is not correct", "WRONG PASSWORD!", JOptionPane.ERROR_MESSAGE);
	            }else {
	            	staffMenu();
	            }
	        }
	    };
	    // Action listener
	 	login.addActionListener(pwdListener); 
	    createPasswordField(pwdListener);
		
		// Add to frame
		pstaff.add(password);
		pstaff.add(txt);
		pstaff.add(login);    
		pstaff.setSize(300,300);    
		pstaff.setLayout(null);    
		pstaff.setVisible(true);     
	}
	
	// Create a password field
    private void createPasswordField(ActionListener pwdListener){

	    password = new JPasswordField(30);
	    password.setBounds(180, 300, 400, 70);
	    password.setEchoChar('*');
	    password.setBackground(Color.white);
	 	password.setFont(new Font("TimesRoman", Font.PLAIN ,36));
	    password.addActionListener(pwdListener);
	}
    
    private void staffMenu() {
    	pstaff.removeAll();
    	// New Activity button
		JButton newAct = new JButton("New Activity");
		newAct.setFont(new Font("TimesRoman", Font.BOLD, 36));
		newAct.setBounds(200,50,360,80);
		
		// Staff Management button
		JButton staffManage = new JButton("Staff Management");
		staffManage.setFont(new Font("TimesRoman", Font.BOLD, 36));
		staffManage.setBounds(200,170,360,80);
		
		// Income button
		JButton income = new JButton("Income");
		income.setFont(new Font("TimesRoman", Font.BOLD, 36));
		income.setBounds(200,290,360,80);
		
		// Modify Price button
		JButton modPrice = new JButton("Modify Price");
		modPrice.setFont(new Font("TimesRoman", Font.BOLD, 36));
		modPrice.setBounds(200,410,360,80);
		
		// Display assistance needs button
		JButton assis = new JButton("Assistance List");
		assis.setFont(new Font("TimesRoman", Font.BOLD, 36));
		assis.setBounds(200,530,360,80);
		
		// Add all action listener
		newAct.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            newActivity();
	        }
	    });
		
		staffManage.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            staffManagement();
	        }
	    });
		
		income.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            showIncome();
	        }
	    });
		
		modPrice.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	modifyPrice();
	        }
	    });
		
		assis.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	assistance();
	        }
	    });
		
		JButton back=new JButton("Back");
		back.setBounds(300, 630 , 200, 50);
		back.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		back.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				initStaff();
			}
		});
		
		// add components to staff panel
		pstaff.add(newAct);
		pstaff.add(staffManage);
		pstaff.add(income);
		pstaff.add(modPrice);
		pstaff.add(assis);
		pstaff.add(back);
		
		// update panel
    	pstaff.repaint();
    	pstaff.revalidate();
    }

    
    // page to add activity
    private void newActivity() {
    	//clear the panel
		pstaff.removeAll();
		
		JLabel addAct=new JLabel("Add Activity");
		addAct.setFont(new Font("TimesRoman", Font.BOLD, 48));
		addAct.setBounds(250,10,350,60);
		
		JLabel nameMsg=new JLabel("Activity Name: ");
		nameMsg.setFont(new Font("TimesRoman", Font.BOLD, 24));
		nameMsg.setBounds(50,100,250,40);
		JTextField name= new JTextField();
		name.setBounds(300, 100, 400, 40);
		name.setFont(new Font("TimesRoman", Font.BOLD, 24));
		
		JLabel priceMsg=new JLabel("Price: ");
		priceMsg.setFont(new Font("TimesRoman", Font.BOLD, 24));
		priceMsg.setBounds(50,150,150,40);
		JTextField price = new JTextField();
		price.setBounds(300, 150, 130, 40);
		price.setFont(new Font("TimesRoman", Font.BOLD, 24));
		
		JLabel placeMsg=new JLabel("Takes place in: ");
		placeMsg.setFont(new Font("TimesRoman", Font.BOLD, 24));
		placeMsg.setBounds(50,200,350,40);
		
		String[] places= {"Collecting the World",
				"Living and Dying",
				"Religion and Ritual",
				"Trade and Discovery",
				"The Natural World",
				"Art and Civilization",
				"Enlightenment",
				"Great Battles",
				"Great Adventures along the Silk Road",
				"Myths and Legends",
				"Great Voyages"};
   
		JComboBox placeBox=new JComboBox(places);
		placeBox.setFont(new Font(placeBox.getFont().getFontName(), Font.PLAIN, 24));
		placeBox.setBounds(300, 200 , 400, 40);
		placeBox.setName("Place");
		
		JLabel msg=new JLabel("Please select a date: ");
		msg.setFont(new Font("TimesRoman", Font.BOLD, 24));
		msg.setBounds(50,250,350,40);
		
		
		// Year
		
		String[] year=new String[Calendar.getInstance().get(Calendar.YEAR)-2009];
		for(int i=2010, k=0; i<=Calendar.getInstance().get(Calendar.YEAR); i++, k++){
			year[k]=i+"";
		}
   
		JComboBox yearBox=new JComboBox(year);
		JLabel yearField=new JLabel("Year");
		yearField.setFont(new Font("TimesRoman", Font.BOLD, 24));
		yearField.setBounds(200,300,100,40);
		yearBox.setFont(new Font(yearBox.getFont().getFontName(), Font.PLAIN, 24));
		yearBox.setBounds(300, 300 , 200, 40);
		yearBox.setName("Year");

		
		//Month 
		
		String[] month=new String[12];
		for(int i=0; i<12;){
			month[i]=++i+"";
		}

		JComboBox monthBox=new JComboBox(month);
		JLabel monthField=new JLabel("Month");
		monthField.setFont(new Font("TimesRoman", Font.BOLD, 24));
		monthField.setBounds(200,360,100,40);
		monthBox.setFont(new Font(monthBox.getFont().getFontName(), Font.PLAIN, 24));
		monthBox.setBounds(300, 360 , 200, 40);
		
		
		//Day
		
		String[] day=new String[31];
		for(int i=0; i<31;){
			day[i]=++i+"";
		}

		JComboBox dayBox=new JComboBox(day);
		JLabel dayField=new JLabel("Day");
		dayField.setFont(new Font("TimesRoman", Font.BOLD, 24));
		dayField.setBounds(200,420,100,40);
		dayBox.setFont(new Font(dayBox.getFont().getFontName(), Font.PLAIN, 24));
		dayBox.setBounds(300, 420 , 200, 40);
		
		
		//search
		
		JButton add=new JButton("Add Activity");
		add.setBounds(250, 500 , 300, 70);
		add.setFont(new Font("TimesRoman", Font.BOLD, 36));
		add.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(name.getInputContext().toString());
				if(name.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter A Valid Activity Name!", "Invaled Activity Name!", JOptionPane.ERROR_MESSAGE);
				}else if(price.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter A Valid Price!", "Invaled Price!", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						String time = "" + yearBox.getSelectedItem() + "-" + monthBox.getSelectedItem() + "-" + dayBox.getSelectedItem();
						db.insertActivity(name.getText(), time, price.getText(), ""+placeBox.getSelectedItem());
						JOptionPane.showMessageDialog(null, "Activity Added Successfully", "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
					}catch(java.lang.NumberFormatException e){
						JOptionPane.showMessageDialog(null, "A Valid Price Should Be A Number!", "Invaled Price!", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		
		JButton back=new JButton("Back");
		back.setBounds(300, 600 , 200, 50);
		back.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		back.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				staffMenu();
			}
		});

		pstaff.add(yearBox);
		pstaff.add(monthBox);
		pstaff.add(dayBox);
		pstaff.add(msg);	
		pstaff.add(dayField);
		pstaff.add(monthField);
		pstaff.add(yearField);
		pstaff.add(add);
		pstaff.add(addAct);
		pstaff.add(nameMsg);
		pstaff.add(name);
		pstaff.add(price);
		pstaff.add(priceMsg);
		pstaff.add(placeMsg);
		pstaff.add(placeBox);
		pstaff.add(back);
		
		//tells Swing this area is dirty
		repaint();
		//recompute the layout
		revalidate();
    }

    // page to manage staff member
    private void staffManagement() {
    	
		pstaff.removeAll();
		
		// add new staff button
		JButton add = new JButton("Add Staff");
		add.setFont(new Font("TimesRoman", Font.BOLD, 36));
		add.setBounds(200,200,360,80);
			
		// delete staff button
		JButton delete = new JButton("Delete Staff");
		delete.setFont(new Font("TimesRoman", Font.BOLD, 36));
		delete.setBounds(200,320,360,80);
	
		// 
		add.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            addStaffMember();
	        }
	    });
		
		delete.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            deleteStaffMember();
	        }
	    });
		
		JButton back=new JButton("Back");
		back.setBounds(300, 600 , 200, 50);
		back.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		back.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				staffMenu();
			}
		});
		
		pstaff.add(add);
		pstaff.add(delete);
		pstaff.add(back);
		
		//tells Swing this area is dirty
		repaint();
		//recompute the layout
		revalidate();
	}

	// page to add staff member
	private void addStaffMember() {
		// clear the panel
		pstaff.removeAll();
		
		JLabel newStaff=new JLabel("Add Staff");
		newStaff.setFont(new Font("TimesRoman", Font.BOLD, 48));
		newStaff.setBounds(250,10,350,60);
		
		JLabel nameMsg=new JLabel("Staff Name: ");
		nameMsg.setFont(new Font("TimesRoman", Font.BOLD, 24));
		nameMsg.setBounds(50,100,250,40);
		JTextField name= new JTextField();
		name.setBounds(300, 100, 400, 40);
		name.setFont(new Font("TimesRoman", Font.BOLD, 24));
		
		JButton add=new JButton("Add Staff");
		add.setBounds(250, 500 , 300, 70);
		add.setFont(new Font("TimesRoman", Font.BOLD, 36));
		add.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(name.getInputContext().toString());
				//String time = "" + yearBox.getSelectedItem() + "-" + monthBox.getSelectedItem() + "-" + dayBox.getSelectedItem();
				//db.insertActivity(name.getText(), time, price.getText(), ""+placeBox.getSelectedItem());
				//JOptionPane.showMessageDialog(null, "Activity Added Successfully", "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
				String sName = "" + name.getText();
				if(sName.equals("")) {
					JOptionPane.showMessageDialog(null, "Staff Name is Empty!", "Invaled Name!", JOptionPane.ERROR_MESSAGE);
				}else {
					db.addStaff(sName);
					JOptionPane.showMessageDialog(null, "Staff Added Successfully", "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		}); 
		
		JButton back=new JButton("Back");
		back.setBounds(300, 600 , 200, 50);
		back.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		back.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				staffManagement();
			}
		});
		
		pstaff.add(newStaff);
		pstaff.add(nameMsg);
		pstaff.add(name);
		pstaff.add(add);
		pstaff.add(back);
		
		//tells Swing this area is dirty
		repaint();
		//recompute the layout
		revalidate();
	}


	// page to delete staff
	private void deleteStaffMember() {

 		// clear the panel
 		pstaff.removeAll();

 		JLabel oldStaff=new JLabel("Delete Staff");
 		oldStaff.setFont(new Font("TimesRoman", Font.BOLD, 48));
 		oldStaff.setBounds(250,10,350,60);
 		
 		// 
 		JLabel oldnameMsg=new JLabel("[Old]Staff Name: ");
 		oldnameMsg.setFont(new Font("TimesRoman", Font.BOLD, 24));
 		oldnameMsg.setBounds(50,100,350,40);
 		JTextField oldname= new JTextField();
 		oldname.setBounds(400, 100, 200, 40);
 		oldname.setFont(new Font("TimesRoman", Font.BOLD, 24));
 		
 		JLabel oldIDMsg=new JLabel("[Old]Staff ID: ");
 		oldIDMsg.setFont(new Font("TimesRoman", Font.BOLD, 24));
 		oldIDMsg.setBounds(50,150,250,40);
 		JTextField oldID= new JTextField();
 		oldID.setBounds(400, 150, 200, 40);
 		oldID.setFont(new Font("TimesRoman", Font.BOLD, 24));
 		
 		JLabel newIDMsg=new JLabel("[New]Staff ID: ");
 		newIDMsg.setFont(new Font("TimesRoman", Font.BOLD, 24));
 		newIDMsg.setBounds(50,200,250,40);
 		JTextField newID= new JTextField();
 		newID.setBounds(400, 200, 200, 40);
 		newID.setFont(new Font("TimesRoman", Font.BOLD, 24));
 		
 		
 		JButton delete=new JButton("Delete Staff");
 		delete.setBounds(250, 500 , 300, 70);
 		delete.setFont(new Font("TimesRoman", Font.BOLD, 36));
 		delete.addActionListener(new ActionListener (){
 			@Override
 			public void actionPerformed(ActionEvent arg0) {
 				if(oldID.getText().equals("") || oldname.getText().equals("") || newID.getText().equals("")) {
 					JOptionPane.showMessageDialog(null, "Please complete the information!", "Incomplete Information!", JOptionPane.ERROR_MESSAGE);
 				}else {
 					String feedback = db.deleteStaff(oldID.getText(), oldname.getText(), newID.getText());
 	 				JOptionPane.showMessageDialog(null, feedback, "Feedback", JOptionPane.INFORMATION_MESSAGE);
 				}
 			}
 		}); 
 		
 		JButton back=new JButton("Back");
		back.setBounds(300, 600 , 200, 50);
		back.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		back.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				staffManagement();
			}
		});
		
		pstaff.add(delete);
		pstaff.add(oldID);
		pstaff.add(newID);
		pstaff.add(newIDMsg);
		pstaff.add(oldIDMsg);
		pstaff.add(oldname);
		pstaff.add(oldStaff);
		pstaff.add(oldnameMsg);
		pstaff.add(back);
		
		//tells Swing this area is dirty
		repaint();
		//recompute the layout
		revalidate();
	 }
    
    // page to display income
    private void showIncome() {
    	//clear the panel
		pstaff.removeAll();
		
		JLabel income=new JLabel("Total Income");
		income.setFont(new Font("TimesRoman", Font.BOLD, 48));
		income.setBounds(220,10,350,60);
		
		JLabel from=new JLabel("Date From: ");
		from.setFont(new Font("TimesRoman", Font.BOLD, 24));
		from.setBounds(150,100,300,40);
		
		JLabel end=new JLabel("Date End: ");
		end.setFont(new Font("TimesRoman", Font.BOLD, 24));
		end.setBounds(450,100,300,40);
		
		
		// Year
		
		String[] year=new String[Calendar.getInstance().get(Calendar.YEAR)-2009];
		for(int i=2010, k=0; i<=Calendar.getInstance().get(Calendar.YEAR); i++, k++){
			year[k]=i+"";
		}
   
		JComboBox yearBox=new JComboBox(year);
		JLabel yearField=new JLabel("Year");
		yearField.setFont(new Font("TimesRoman", Font.BOLD, 24));
		yearField.setBounds(50,150,100,40);
		yearBox.setFont(new Font(yearBox.getFont().getFontName(), Font.PLAIN, 24));
		yearBox.setBounds(150, 150 , 150, 40);
		yearBox.setName("Year");

		
		//Month 
		
		String[] month=new String[12];
		for(int i=0; i<12;){
			month[i]=++i+"";
		}

		JComboBox monthBox=new JComboBox(month);
		JLabel monthField=new JLabel("Month");
		monthField.setFont(new Font("TimesRoman", Font.BOLD, 24));
		monthField.setBounds(50,250,100,40);
		monthBox.setFont(new Font(monthBox.getFont().getFontName(), Font.PLAIN, 24));
		monthBox.setBounds(150, 250 , 150, 40);
		
		
		//Day
		
		String[] day=new String[31];
		for(int i=0; i<31;){
			day[i]=++i+"";
		}

		JComboBox dayBox=new JComboBox(day);
		JLabel dayField=new JLabel("Day");
		dayField.setFont(new Font("TimesRoman", Font.BOLD, 24));
		dayField.setBounds(50,350,100,40);
		dayBox.setFont(new Font(dayBox.getFont().getFontName(), Font.PLAIN, 24));
		dayBox.setBounds(150, 350 , 150, 40);
		
		
		// Year
		
		String[] year2=new String[Calendar.getInstance().get(Calendar.YEAR)-2009];
		for(int i=2010, k=0; i<=Calendar.getInstance().get(Calendar.YEAR); i++, k++){
			year2[k]=i+"";
		}
   
		JComboBox yearBox2=new JComboBox(year2);
		yearBox2.setFont(new Font(yearBox.getFont().getFontName(), Font.PLAIN, 24));
		yearBox2.setBounds(450, 150 , 150, 40);
		yearBox2.setName("Year");

		
		//Month 
		
		String[] month2=new String[12];
		for(int i=0; i<12;){
			month2[i]=++i+"";
		}

		JComboBox monthBox2=new JComboBox(month);
		monthBox2.setFont(new Font(monthBox.getFont().getFontName(), Font.PLAIN, 24));
		monthBox2.setBounds(450, 250 , 150, 40);
		
		
		//Day
		
		String[] day2=new String[31];
		for(int i=0; i<31;){
			day[i]=++i+"";
		}

		JComboBox dayBox2=new JComboBox(day);
		dayBox2.setFont(new Font(dayBox.getFont().getFontName(), Font.PLAIN, 24));
		dayBox2.setBounds(450, 350 , 150, 40);
		
		JLabel totalIncome = new JLabel();
		totalIncome.setBounds(200, 500, 400, 100);
		totalIncome.setFont(new Font(dayBox.getFont().getFontName(), Font.PLAIN, 48));
		
		//search
		
		JButton display=new JButton("Display Total Income");
		display.setBounds(140, 450 , 500, 70);
		display.setFont(new Font("TimesRoman", Font.BOLD, 36));
		display.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(name.getInputContext().toString());
				String timeFrom = "" + yearBox.getSelectedItem() + "-" + monthBox.getSelectedItem() + "-" + dayBox.getSelectedItem();
				String timeEnd = "" + yearBox2.getSelectedItem() + "-" + monthBox2.getSelectedItem() + "-" + dayBox2.getSelectedItem();
				String income = db.selectIncome(timeFrom, timeEnd);
				if(income == null) {
					totalIncome.setText("	No income!");
				}else {
					totalIncome.setText(income);
				}
				
			}
		});
		
		JButton back=new JButton("Back");
		back.setBounds(300, 600 , 200, 50);
		back.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		back.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				staffMenu();
			}
		});
		
		
		pstaff.add(from);
		pstaff.add(end);
		pstaff.add(yearBox);
		pstaff.add(monthBox);
		pstaff.add(dayBox);
		pstaff.add(dayField);
		pstaff.add(monthField);
		pstaff.add(yearField);
		pstaff.add(yearBox2);
		pstaff.add(income);
		pstaff.add(monthBox2);
		pstaff.add(dayBox2);
		pstaff.add(monthField);
		pstaff.add(display);
		pstaff.add(totalIncome);
		pstaff.add(back);
		
		//tells Swing this area is dirty
		repaint();
		//recompute the layout
		revalidate();
    }
    
    // page to modify price
    private void modifyPrice() {
    	//clear the panel
    	pstaff.removeAll();
    	
		JLabel modP=new JLabel("Modify a Price");
		modP.setFont(new Font("TimesRoman", Font.BOLD, 48));
		modP.setBounds(250,10,350,60);
		
		JLabel nameMsg=new JLabel("Activity Name: ");
		nameMsg.setFont(new Font("TimesRoman", Font.BOLD, 24));
		nameMsg.setBounds(50,100,250,40);
		JTextField name= new JTextField();
		name.setBounds(300, 100, 400, 40);
		name.setFont(new Font("TimesRoman", Font.BOLD, 24));
		
		JLabel timeMsg=new JLabel("Activity Time: ");
		timeMsg.setFont(new Font("TimesRoman", Font.BOLD, 24));
		timeMsg.setBounds(50,150,250,40);
		JTextField time= new JTextField();
		time.setBounds(300, 150, 400, 40);
		time.setFont(new Font("TimesRoman", Font.BOLD, 24));
		
		
		JLabel priceMsg=new JLabel("New Price: ");
		priceMsg.setFont(new Font("TimesRoman", Font.BOLD, 24));
		priceMsg.setBounds(50,200,150,40);
		JTextField price = new JTextField();
		price.setBounds(300, 200, 130, 40);
		price.setFont(new Font("TimesRoman", Font.BOLD, 24));
		
		//MODIFY A PRICE BUTTON
		JButton mod=new JButton("Modify!");
		mod.setBounds(250, 500 , 300, 70);
		mod.setFont(new Font("TimesRoman", Font.BOLD, 36));
		mod.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(name.getInputContext().toString());
				//String time = "" + yearBox.getSelectedItem() + "-" + monthBox.getSelectedItem() + "-" + dayBox.getSelectedItem();
				//db.insertActivity(name.getSelectedText(), time, price.getText(), ""+placeBox.getSelectedItem());
				//JOptionPane.showMessageDialog(null, "Activity Added Successfully", "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
				
				String responseMsg = db.modifyPrice(name.getText(),time.getText(),price.getText());
				JOptionPane.showMessageDialog(null, responseMsg,"Notice",JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		JButton back=new JButton("Back");
		back.setBounds(300, 600 , 200, 50);
		back.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		back.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				staffMenu();
			}
		});
		
		
		pstaff.add(mod);
		pstaff.add(modP);
		pstaff.add(nameMsg);
		pstaff.add(name);
		pstaff.add(price);
		pstaff.add(priceMsg);
		pstaff.add(time);
		pstaff.add(timeMsg);
		pstaff.add(back);
		//tells Swing this area is dirty
		repaint();
		//recompute the layout
		revalidate();
    }
    
    // assistance information
    private void assistance() {
    	//clear the panel
    	pstaff.removeAll();
    	
    	JLabel assis=new JLabel("Help Needed On");
    	assis.setFont(new Font("TimesRoman", Font.BOLD, 48));
    	assis.setBounds(30,10,370,60);
		
		// Year
		
		String[] year=new String[Calendar.getInstance().get(Calendar.YEAR)-2009];
		for(int i=2010, k=0; i<=Calendar.getInstance().get(Calendar.YEAR); i++, k++){
			year[k]=i+"";
		}
   
		JComboBox yearBox=new JComboBox(year);
		yearBox.setFont(new Font(yearBox.getFont().getFontName(), Font.PLAIN, 48));
		yearBox.setBounds(420, 10, 150, 60);
		yearBox.setName("Year");

		
		//Month 
		
		String[] month=new String[12];
		for(int i=0; i<12;){
			month[i]=++i+"";
		}

		JComboBox monthBox=new JComboBox(month);
		monthBox.setFont(new Font(monthBox.getFont().getFontName(), Font.PLAIN, 48));
		monthBox.setBounds(570, 10 , 80, 60);
		
		
		//Day
		
		String[] day=new String[31];
		for(int i=0; i<31;){
			day[i]=++i+"";
		}

		JComboBox dayBox=new JComboBox(day);
		dayBox.setFont(new Font(dayBox.getFont().getFontName(), Font.PLAIN, 48));
		dayBox.setBounds(650, 10 , 80, 60);

		JLabel assisList = new JLabel();
		assisList.setBounds(10, 150, 700, 200);
		assisList.setFont(new Font(dayBox.getFont().getFontName(), Font.PLAIN, 16));
		
        //search
        
  		JButton display=new JButton("Display Assistance");
  		display.setBounds(180, 100 , 400, 70);
  		display.setFont(new Font("TimesRoman", Font.BOLD, 36));
  		display.addActionListener(new ActionListener (){
  			@Override
  			public void actionPerformed(ActionEvent arg0) {

  				String time = "" + yearBox.getSelectedItem() + "-" + monthBox.getSelectedItem() + "-" + dayBox.getSelectedItem();  				
  				String assistance = db.storedProcedure(time);
  				if(assistance.length() > 0) {
  					assisList.setText(assistance);
  				}  				
  			}
  		});
  		
  		JButton back=new JButton("Back");
		back.setBounds(300, 600 , 200, 50);
		back.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		back.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				staffMenu();
			}
		});
		
		pstaff.add(assis);
		pstaff.add(yearBox);
		pstaff.add(monthBox);
		pstaff.add(dayBox);
		pstaff.add(display);
		pstaff.add(assisList);
		pstaff.add(back);
		
		
		//tells Swing this area is dirty
		repaint();
		//recompute the layout
		revalidate();
	}

	//void 
	void searchActivity(){
		
		//clear the panel
		pvisitor.removeAll();
		
		JLabel msg=new JLabel("Please select a date");
		msg.setFont(new Font("TimesRoman", Font.BOLD, 28));
		msg.setBounds(200,80,350,40);
		
		
		// Year
		
		String[] year=new String[Calendar.getInstance().get(Calendar.YEAR)-2010+1];
		for(int i=2010, k=0; i<=Calendar.getInstance().get(Calendar.YEAR); i++, k++){
			year[k]=i+"";
		}
   
		JComboBox yearBox=new JComboBox(year);
		JLabel yearField=fieldName("Year", 200, 170);
		yearBox.setFont(new Font(yearBox.getFont().getFontName(), Font.PLAIN, 16));
		yearBox.setBounds(300, 170 , 200, 30);

		
		//Month 
		
		String[] month=new String[12];
		for(int i=0; i<12;){
			month[i]=++i+"";
		}

		JComboBox monthBox=new JComboBox(month);
		JLabel monthField=fieldName("Month", 200, 240);
		monthBox.setFont(new Font(monthBox.getFont().getFontName(), Font.PLAIN, 16));
		monthBox.setBounds(300, 240 , 200, 30);
		
		
		//Day
		
		String[] day=new String[31];
		for(int i=0; i<31;){
			day[i]=++i+"";
		}

		JComboBox dayBox=new JComboBox(day);
		JLabel dayField=fieldName("Day", 200, 310);
		dayBox.setFont(new Font(dayBox.getFont().getFontName(), Font.PLAIN, 16));
		dayBox.setBounds(300, 310 , 200, 30);
		
		
		//search
		
		JButton search=new JButton("Search");
		search.setBounds(300, 400 , 200, 50);
		search.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		search.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent arg0) {
	
				displayActivities(yearBox.getSelectedItem().toString()+
						'-'+monthBox.getSelectedItem().toString()
						+'-'+dayBox.getSelectedItem().toString());
			}
		});
		
		
		pvisitor.add(yearBox);
		pvisitor.add(monthBox);
		pvisitor.add(dayBox);
		pvisitor.add(msg);	
		pvisitor.add(dayField);
		pvisitor.add(monthField);
		pvisitor.add(yearField);
		pvisitor.add(search);
		
		//tells Swing this area is dirty
		repaint();
		//recompute the layout
		revalidate();
		
	}
	
	
	private JLabel fieldName(String name, int x, int y){
		JLabel label=new JLabel(name);
		label.setFont(new Font("TimesRoman", Font.BOLD, 16));
		label.setBounds(x,y,100,40);
		return label;
	}
	
	

	void book(String name, String time, String price){

		pvisitor.removeAll();		
		
		JLabel msg1=new JLabel(name+" - "+time+" - \n"+"Price: "+price);
		msg1.setFont(new Font("TimesRoman", Font.BOLD, 28));
		msg1.setBounds(50,50,650,40);
		
		JLabel msg2=new JLabel("Price: "+price);
		msg2.setFont(new Font("TimesRoman", Font.BOLD, 28));
		msg2.setBounds(50,90,650,40);
		
		JLabel phoneMsg=new JLabel("Please enter your phone");
		phoneMsg.setFont(new Font("TimesRoman", Font.PLAIN, 24));
		phoneMsg.setBounds(50,200,250,40);
		JTextField phone= new JTextField();
		phone.setBounds(300, 200, 400, 40);
		phone.setFont(new Font("TimesRoman", Font.PLAIN, 24));
		
		JLabel nameMsg=new JLabel("Please enter your name");
		nameMsg.setFont(new Font("TimesRoman", Font.PLAIN, 24));
		nameMsg.setBounds(50,250,250,40);
		JTextField pname= new JTextField();
		pname.setBounds(300, 250, 400, 40);
		pname.setFont(new Font("TimesRoman", Font.PLAIN, 24));
		
		JLabel numMsg=new JLabel("Number of people");
		numMsg.setFont(new Font("TimesRoman", Font.PLAIN, 24));
		numMsg.setBounds(50,300,250,40);
		JTextField num= new JTextField();
		num.setBounds(300, 300, 400, 40);
		num.setFont(new Font("TimesRoman", Font.PLAIN, 24));
		
		JLabel noteMsg=new JLabel("Note (Optional)");
		noteMsg.setFont(new Font("TimesRoman", Font.PLAIN, 24));
		noteMsg.setBounds(50,350,250,40);
		JTextField note= new JTextField();
		note.setBounds(300, 350, 400, 40);
		note.setFont(new Font("TimesRoman", Font.PLAIN, 24));
		
		JButton confirm=new JButton("Confirm");
		confirm.setBounds(300, 450 , 200, 50);
		confirm.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		confirm.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//get current time
				String now=LocalTime.now().toString();
				String date=LocalDate.now().toString();
				String str=date+" "+now.substring(0, now.length()-4);
				System.out.println(str);
				
				db.insertVisitor(phone.getText(), pname.getText(), note.getText());
				String msg=db.insertReservation(str, phone.getText(), 
						num.getText(), price, name, time);
				JOptionPane.showMessageDialog(new JFrame(), msg);
				
			}
		});
		
		JButton search=new JButton("Back");
		search.setBounds(300, 600 , 200, 50);
		search.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		search.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				initVisitor();
			}
		});
		
		
		pvisitor.add(msg1);
		pvisitor.add(msg2);
		pvisitor.add(phoneMsg);
		pvisitor.add(phone);
		pvisitor.add(numMsg);
		pvisitor.add(num);
		pvisitor.add(nameMsg);
		pvisitor.add(pname);
		pvisitor.add(noteMsg);
		pvisitor.add(note);
		pvisitor.add(confirm);
		pvisitor.add(search);
		
		//tells Swing this area is dirty
		repaint();
		//recompute the layout
		revalidate();
		
	}
	
	
	
	
	void displayActivities(String date){
		
		pvisitor.removeAll();
		
			
		//send the query information to database
		String[][] activityList=db.selectActivities(date);
		
		if(activityList==null){
			
			JLabel msg=new JLabel("No activity has been found");
			msg.setFont(new Font("TimesRoman", Font.BOLD, 28));
			msg.setBounds(200,200,400,60);
			pvisitor.add(msg);
			
			
		}else{
			
			listTable(activityList, 10, 10);
			
		}
		
		JButton search=new JButton("Back");
		search.setBounds(300, 600 , 200, 50);
		search.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		search.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchActivity();
			}
		});
		
		pvisitor.add(search);
			
		//tells Swing this area is dirty
		repaint();
		//recompute the layout
		revalidate();
		
		
	}
	
	
	
	
	void listTable(String[][] list, int x, int y){
		
		//scrolll pane layout
		
		String[] title={"Activity", "Time", "Price"};
		
		JTable table=new JTable(list, title);
		table.setRowSelectionAllowed(true);
		table.setRowHeight(22);
		table.setRowMargin(2);
		table.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		table.addColumnSelectionInterval(0, list[0].length-1);
		
		ListSelectionModel select=table.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		select.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int row=table.getSelectedRow();
				book(table.getValueAt(row, 0).toString(), 
						table.getValueAt(row, 1).toString(), 
						table.getValueAt(row, 2).toString());
			}
			
		});
		
		JScrollPane scrollArea = new JScrollPane(table);  
		scrollArea.setBounds(x, y, FRAME_WIDTH-100, FRAME_LENGTH-250 );
  
		pvisitor.add(scrollArea);
	
		
	}
	

	
}