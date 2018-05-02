package w7a2_jdbc.JD.utils;

public class OracleQueries {
	public final static String GETITEMSINSTOCK = "select * from item where item_quantity > 0";
	public final static String UPDATEITEMQUANTITY = "update item "
			+ "set item_quantity = ? "
			+ "where item_id = ?";
	public final static String GETALLITEMS = "select * from item";

	public final static String ADDORDER = "insert into order_table "
			+ "(order_itemid, order_quantity) "
			+ "values(?, ?)";
	
}
