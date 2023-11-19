import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
//Class for customer details
class Customerdetail{
	
	String customerName; 
	long consumerNumber;
	//constructor of class customer with Arguments 
	Customerdetail(String customerName,long consumerNumber){
		this.customerName=customerName;
		this.consumerNumber=consumerNumber;
		}
	
}
//Class for EB bill calculation
public class DomesticEBbillcalculation extends Customerdetail  {
	
DomesticEBbillcalculation(String customerName, long consumerNumber) {
		super(customerName, consumerNumber);
		}

//Main function for implementation
public static void main(String[] args) {
	//try block handling Exception
		try {
			//Object created for Scanner class in heap memory
			Scanner sc= new Scanner(System.in);
			
			 System.out.println("Please enter your name");
			
			String customerName=sc.nextLine();
			 
			 System.out.println("Please enter Consumer number");
			 
			long consumerNumber=sc.nextLong();
			 
			 System.out.println("Enter the number of units");
              int units=sc.nextInt();
             //Variables declared for Taxrate and bill 
              double taxRate=0.05;
              double yoursBill=0.0;
              
           //Checking whether units is less than or equal to 100
              if(units<=100) {
            	  yoursBill=units*0;
          
            	  
            	  
              }
              //Checking whether units is between 0 and 200
              else if(units>0 && units<=200){
            	  yoursBill=100*0+(units-100)*1.5;
            	  
              }
              //Checking whether units is between 0 and 500
              else if(units>0 && units<=500) {
            	  yoursBill=100*0+(units-100)*2+(units-200)*3;
              }
              //Checking the units are greater than 500
              else if(units>500){
            	  yoursBill=100*0+(units-100)*3.5+(units-200)*4.6+(units-500)*6.6;
            	 
              }
              //if unit is greater than 100 then the tax rate will be added
              if(units>100)
              yoursBill=yoursBill+calculateTax(units,taxRate); 
              System.out.println("Your electricity bill for units"+" "+units+" "+"is:"+" "+yoursBill+" "+"rupees");
			
		}
		
		//Catch block for handling the exception
		catch(Exception e) {
			System.out.println("invalid value entered"+" "+e);
		
		}
		
}

//Function for Calculating Tax for each unit
public static double calculateTax(int units,double taxRate) {
	return units*taxRate;	
  }

 }

//class for to view bill from database
class Viewbill{
 public static void main(String args[]){
try {
	 
	//Database name,username,password changes accordingly to respected database
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Billhistory","root","riyas"); 
    Statement st=con.createStatement();
	String s1="select * from EBbilldata";
    
	ResultSet rs=st.executeQuery(s1);
	
	while(rs.next()) {
		System.out.println("yours name :"+" "+rs.getString(1));
		System.out.println("yours Consumer number :"+" "+rs.getString(2));
    	System.out.println("units runned :"+" "+rs.getString(3));
    	System.out.println("yours bill amount:"+" "+rs.getString(4));
    	System.out.println("Date"+" "+rs.getString(5));
    	System.out.println("----------------------------------------");
	}	  
}catch(Exception e) {
	System.out.println("Error occured"+" "+e);
 }
	
}
}

//class for save bill in database
class Savebill{
	public static void main(String args[]){
	
		//Database name,username,password changes accordingly to respected database
	try {
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Billhistory","root","riyas"); 
	    Statement st=con.createStatement();
	    String s1="insert into EBbilldata values('Riyas','323456781','175','125.25','19.11.2023')";
	    String s2="insert into EBbilldata values('Kamal','829456781','340','917','19.11.2023')";
	    st.execute(s1);
	    st.execute(s2);
	    
	    System.out.println("Bill saved");
	    
	    con.close();
	    
	   
	}catch(Exception e) {
		System.out.println("Error occured in Saving bill"+" "+e);
	}
	
}

}

//class for to delete bill from database
class Deletebill{
	public static void main(String args[]){
	
		//Database name,username,password changes accordingly to respected database
	try {
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Billhistory","root","riyas");  
	    Statement st=con.createStatement();
	    String s2="delete from EBbilldata where custname='Riyas'";
	    
	    st.execute(s2);
	    
	    System.out.println("Bill deleted");
	    
	    con.close();
	    
	   
	}catch(Exception e) {
		System.out.println("Error occured in Saving bill"+" "+e);
	}
	
}

}



