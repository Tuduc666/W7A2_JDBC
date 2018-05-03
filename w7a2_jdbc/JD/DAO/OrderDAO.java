package w7a2_jdbc.JD.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import w7a2_jdbc.JD.utils.OracleQueries;

public class OrderDAO {
	public boolean createOrder(Map<Integer, Integer> m) throws SQLException, ClassNotFoundException, IOException  {
		Connection conn = null;
		PreparedStatement stmt =  null;
	    boolean l=true;
	    boolean n=true;
		conn = OracleConnection.getConnection();
		
		// insert a record into order_table foreach hashmap entry
		for(Map.Entry<Integer, Integer> x : m.entrySet()) {
		    stmt =  conn.prepareStatement(OracleQueries.ADDORDER);
		    stmt.setInt(1, x.getKey());   
		    stmt.setInt(2, x.getValue());  
	 	    n = stmt.executeUpdate() >0;      // checks if record inserted successfully. stmt.executeUpdate() is number of records updated
		    if(!n) l=false;
		}
		return l;    
	}
}
