// usage:  1. compile: javac -cp /usr/lib/oracle/18.3/client64/lib/ojdbc8.jar jdbcdemo2.java
//         2. execute: java -cp /usr/lib/oracle/18.3/client64/lib/ojdbc8.jar jdbcdemo2.java

//Illustrate call stored procedure
import java.sql.*;
import oracle.jdbc.*;
import java.math.*;
import java.io.*;
import java.awt.*;
import oracle.jdbc.pool.OracleDataSource;
import java.util.*;
import java.sql.Types;

public class jdbcdemo2 {

   public static void main (String args []) throws SQLException {
    try
    {

        //Connection to Oracle server.
        OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("pjagani1", "9212367502");

	//created switch statement for implementing different cases
	Scanner sc=new Scanner(System.in);
	boolean b=true;
	while(b){
	System.out.println("enter 1 for displaying employees");
	System.out.println("enter 2 for displaying customers");
	System.out.println("enter 3 for displaying products");
	System.out.println("enter 4 for displaying purchases");
	System.out.println("enter 5 for displaying logs");
	System.out.println("enter 6 for displaying name of the customer as well as every purchase the customer has made for given cid");
	System.out.println("enter 7 for displaying the number of customers who have purchased the product identified by the pid");
	System.out.println("enter 8 for inserting in the customer table");
	System.out.println("enter 9 for adding tuple to the Purchases table");
	System.out.println("enter 10 to exit");
	int n=sc.nextInt();
	switch(n){
		case 1:
		display_employees(conn );
		break;
		case 2:
		display_customers(conn);
		break;
		case 3:
		display_products(conn);
		break;
		case 4:
		display_purchases(conn);
		break;
		case 5:
		display_logs(conn);
		break;
		case 6:
		purchases_made(conn);
		break;
		case 7:
		number_customers(conn);
		break;
		case 8:
		add_customer(conn);
		break;
		case 9:
		add_purchase(conn);
		break;	
		case 10:
		b=false;		
		break;
		default:
		System.out.println("please enter right input");
	}
	}
       conn.close();
   }
   catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }

 static void display_employees(Connection conn) throws SQLException{
	try
    {
	//calling the show_employees procedure 
	CallableStatement cs = conn.prepareCall("call show_employees(?)");

        //set the in parameter (the first parameter)
       // cs.setString(1, sid);

        //register the out parameter (the second parameter)
        cs.registerOutParameter(1, OracleTypes.CURSOR);

        //execute the stored procedure
        cs.execute();
	
      ResultSet rs = null;
      rs = (ResultSet)cs.getObject(1);   
		    if(rs != null){
		    	
	    		  	System.out.println("\n\neid" + "\t\t" + "name" + "\t" + "\t" + "telephone#" + "\t\t"  + "\t\t" + "EMAIL" + "\t\t\t");
	    		  	System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
				
			}
		     while (rs.next()) {
            	System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) +rs.getString(4));
        	}
	
        //close the result set, statement, and the connection
        cs.close();
        
 }
catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }


static void display_customers(Connection conn) throws SQLException{
 try
    {

	CallableStatement cs = conn.prepareCall("call show_customers(?)");

        //set the in parameter (the first parameter)
       // cs.setString(1, sid);

        //register the out parameter (the second parameter)
        cs.registerOutParameter(1, OracleTypes.CURSOR);

        //execute the stored procedure
        cs.execute();

      ResultSet rs = null;
      rs = (ResultSet)cs.getObject(1);   
		    if(rs != null){
		    	
	    		  	System.out.println("\n\ncid" + "\t\t" + "name" + "\t" + "telephone#" + "\t" + "visit_made" + "\t\t" + "last_visit_date");
	    		  	System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
				
			}
		     while (rs.next()) {
            	System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) +"\t"+rs.getString(4)+"\t"+rs.getString(5));
        	}
	
        //close the result set, statement, and the connection
        cs.close();
        
 }
 catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }

static void display_products(Connection conn) throws SQLException{
 try
    {

	CallableStatement cs = conn.prepareCall("call show_products(?)");

        //set the in parameter (the first parameter)
       // cs.setString(1, sid);

        //register the out parameter (the second parameter)
        cs.registerOutParameter(1, OracleTypes.CURSOR);

        //execute the stored procedure
        cs.execute();

      ResultSet rs = null;
      rs = (ResultSet)cs.getObject(1);   
		    if(rs != null){
		    	
	    		  	System.out.println("\n\npid" + "\t\t" + "name" + "\t" + "qoh" + "\t" + "qoh_threshold" + "\t\t" + "reg_price_rate"+"\t"+"last_visit_date");
	    		  	System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
				
			}
		     while (rs.next()) {
            	System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) +"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6));
        	}
	
        //close the result set, statement, and the connection
        cs.close();
        
 }
 catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }

static void display_purchases(Connection conn) throws SQLException{
 try
    {

	CallableStatement cs = conn.prepareCall("call show_purchases(?)");

        //set the in parameter (the first parameter)
       // cs.setString(1, sid);

        //register the out parameter (the second parameter)
        cs.registerOutParameter(1, OracleTypes.CURSOR);

        //execute the stored procedure
        cs.execute();

      ResultSet rs = null;
      rs = (ResultSet)cs.getObject(1);   
		    if(rs != null){
		    	
	    		  	System.out.println("\n\npur#" + "\t\t" + "eid" + "\t" + "pid" + "\t" + "cid" + "\t\t" + "pur_date"+"\t"+"qty"+"\t"+"unit_price"+"\t"+"total"+"\t"+"saving");
	    		  	System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
				
			}
		     while (rs.next()) {
            	System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) +"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getString(7)+"\t"+rs.getString(8)+"\t"+rs.getString(9));
        	}
	
        //close the result set, statement, and the connection
        cs.close();
        
 }
 catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }

static void display_logs(Connection conn) throws SQLException{
 try
    {

	CallableStatement cs = conn.prepareCall("call show_logs(?)");

        //set the in parameter (the first parameter)
       // cs.setString(1, sid);

        //register the out parameter (the second parameter)
        cs.registerOutParameter(1, OracleTypes.CURSOR);

        //execute the stored procedure
        cs.execute();

      ResultSet rs = null;
      rs = (ResultSet)cs.getObject(1);   
		    if(rs != null){
		    	
	    		  	System.out.println("\n\nlog#" + "\t\t" + "user_name" + "\t" + "operation" + "\t" + "op_time" + "\t\t" + "table_name"+"\t"+"tuple_pkey");
	    		  	System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
				
			}
		     while (rs.next()) {
            	System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) +"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6));
        	}
	
	
        //close the result set, statement, and the connection
        cs.close();
        
 }
 catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }

static void purchases_made(Connection conn) throws SQLException{
 try
    {

	CallableStatement cs = conn.prepareCall("call purchases_made(?,?)");
	System.out.println("enter the cid of customers");
	Scanner sc=new Scanner(System.in);
	String cid=sc.next();
        //set the in parameter (the first parameter)
         cs.setString(2,cid);

        //register the out parameter (the second parameter)
        cs.registerOutParameter(1, OracleTypes.CURSOR);

        //execute the stored procedure
        cs.execute();

      ResultSet rs = null;
      rs = (ResultSet)cs.getObject(1);   
		    if(rs != null){
		    	
	    		  	System.out.println("\n\n name" + "\t\t" + "pid" + "\t" + "pur_date" + "\t" + "Qty" + "\t\t" + "unit_price"+"\t"+"total");
	    		  	System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
				
			}
		while (rs.next()) {
            	System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) +"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6));
        	}
	
        //close the result set, statement, and the connection
        cs.close();
        
 }
 catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }

static void  number_customers(Connection conn) throws SQLException{
 try
    {

	CallableStatement cs = conn.prepareCall("call number_customers(?,?)");
	System.out.println("enter the pid of customers");
	Scanner sc=new Scanner(System.in);
	String pid=sc.next();
        //set the in parameter (the first parameter)
         cs.setString(2,pid);
	//register ouut parameter
	cs.registerOutParameter(1, OracleTypes.CURSOR);
        //execute the stored procedure
        cs.execute();

      ResultSet rs = null;
      rs = (ResultSet)cs.getObject(1);   
		    if(rs != null){
		    	
	    		  	System.out.println("\n\npurchaseno");
	    		  	System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
				
			}
		while (rs.next()) {
            	System.out.println(rs.getString(1));
        	}
	
        //close the result set, statement, and the connection
        cs.close();
        
 }
 catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }

static void  add_customer(Connection conn) throws SQLException{
 try
    {

	CallableStatement cs = conn.prepareCall("call add_customer(?,?,?)");
	System.out.println("enter the cid of customers");
	Scanner sc=new Scanner(System.in);
	String pid=sc.next();
        //set the in parameter (the first parameter)
        cs.setString(1,pid);
	System.out.println("enter the customername of customers");
	String name=sc.next();
	cs.setString(2,name);
	System.out.println("enter the customername of customers");
	String telephoneno=sc.next();
	cs.setString(3,telephoneno);
	
        //execute the stored procedure
        cs.executeUpdate();

    //  ResultSet rs = null;
     // rs = (ResultSet)cs.getObject(1);   
	//	    if(rs != null){
		    	
	//    		  	System.out.println("\n\npurchaseno");
	  //  		  	System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
		//		
		//	}
	//	while (rs.next()) {
          //  	System.out.println(rs.getString(1));
        	//}
	
        //close the result set, statement, and the connection
        cs.close();
        
 }
 catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }

static void  add_purchase(Connection conn) throws SQLException{
 try
    {

	CallableStatement cs = conn.prepareCall("call add_purchase(?,?,?,?,?)");
	System.out.println("enter the eid:");
	Scanner sc=new Scanner(System.in);
	String e_id=sc.next();
        //set the in parameter (the first parameter)
        cs.setString(1,e_id);
	System.out.println("enter the pid:");
	String p_id=sc.next();
	cs.setString(2,p_id);
	System.out.println("enter the cid:");
	String c_id=sc.next();
	cs.setString(3,c_id);
	System.out.println("enter the pur_qty:");
	int pur_qty=sc.nextInt();
	cs.setInt(4,pur_qty);
	System.out.println("enter the unit_price:");
	float unit_price=sc.nextFloat();
	cs.setFloat(5,unit_price );
	
        //execute the stored procedure
        cs.executeUpdate();
	String query="SELECT qoh,pid,qoh_threshold FROM products";
	//PreparedStatement ps = conn.prepareStatement(query);
	//ps.setString(1,p_id);
	Statement stmt = conn.createStatement ();
        ResultSet rset  = null;
        rset = stmt.executeQuery (query);
	int my_qoh=0,my_qoh_threshold=0;
        //rs = (ResultSet)cs.getObject(1);   
	//	    if(rs != null){
		    	
	//    		  	System.out.println("\n\npurchaseno");
	  //  		  	System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
		//		
		//	}
	//getting out the whole data from products table then comparing with p_id.
	while (rset.next()) {
	String s3=rset.getString(2);
	if(p_id.equals(s3)){
        my_qoh=Integer.parseInt(rset.getString(1));
        my_qoh_threshold=Integer.parseInt(rset.getString(3));
	}
	}
	//comparing threshold with qantity required and quantityin hand.
	if((my_qoh-pur_qty)<my_qoh_threshold){	
	System.out.println("The current qoh of the product is below the required threshold and new supply is required");
	System.out.println("The new qoh of product is"+(my_qoh_threshold+10));
	}
        //close the result set, statement, and the connection
	rset.close();
        stmt.close();
        cs.close();
        
 }
 catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }

}
