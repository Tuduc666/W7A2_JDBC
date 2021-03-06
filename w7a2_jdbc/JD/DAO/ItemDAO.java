package w7a2_jdbc.JD.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import w7a2_jdbc.JD.model.Item;
import w7a2_jdbc.JD.utils.OracleQueries;

public class ItemDAO {
	public List<Item> getItemsInStock() throws ClassNotFoundException, SQLException, IOException {
		Connection conn = null;
		Statement stmt =  null;
		ResultSet result = null;
	    
		conn = OracleConnection.getConnection();
	    stmt =  conn.createStatement();
	    result = stmt.executeQuery(OracleQueries.GETITEMSINSTOCK);
	    
	    List<Item> l = new ArrayList<Item>();
	    Item s = null;
	    while(result.next()) {            
//	    	System.out.printf("%-30d   %-30s   %-30d   %-30f \r\n",
//	    			result.getInt(1),result.getString(2),result.getInt(3),result.getFloat(4)); 
	    	s = new Item(result.getInt(1),result.getString(2),result.getInt(3),result.getFloat(4));
	    	l.add(s);
	    }
		return l;    
	}
	public boolean updateQuantityInStock(int id, int qty) throws SQLException, ClassNotFoundException, IOException  {
		Connection conn = null;
		PreparedStatement stmt =  null;
	    
		conn = OracleConnection.getConnection();
	    stmt =  conn.prepareStatement(OracleQueries.UPDATEITEMQUANTITY);
	    stmt.setInt(1, qty);
	    stmt.setInt(2, id);
	    return stmt.execute();
	}
//	public void getAllItems() throws ClassNotFoundException, SQLException, IOException {
//		Connection conn = null;
//		Statement stmt =  null;
//		ResultSet result = null;
//	    
//		conn = OracleConnection.getConnection();
//	    stmt =  conn.createStatement();
//	    result = stmt.executeQuery(OracleQueries.GETALLITEMS);
//		    
//	    while(result.next()) {         // get first/next record from the result, false if there is no more record    
//	    	System.out.printf("%-30d   %-30s   %-30d   %-30f \r\n",
//	    			result.getInt(1),result.getString(2),result.getInt(3),result.getFloat(4));     
//	    }     
//	}
}
