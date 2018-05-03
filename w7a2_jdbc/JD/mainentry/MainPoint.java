package w7a2_jdbc.JD.mainentry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import w7a2_jdbc.JD.DAO.ItemDAO;
import w7a2_jdbc.JD.DAO.OrderDAO;
import w7a2_jdbc.JD.model.Item;



public class MainPoint {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException  {
		ItemDAO o = new ItemDAO();
		Scanner sc = new Scanner(System.in);
		HashMap<Integer,Integer> avail = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> cart = new HashMap<Integer,Integer>();
		int choice=0;
		while(choice != 4) {
			System.out.println("\n=======================  Menu Items  ===================================");
			System.out.println("Item#      Item Name                                Price        Avail Qty");
			System.out.println("-----      ---------                                -----        ---------");
			for(Item i : o.getItemsInStock()) {
		    	System.out.printf("%-10d %-40s %-7.2f      %-8d \r\n",
				i.getId(),i.getName(),i.getPrice(),i.getQty()); 
		    	avail.put(i.getId(), i.getQty());
			}
			printMenu();
			choice = sc.nextInt(); sc.nextLine();
			switch(choice) {
				case 1:
					int availQty=0, cartQty=0, itemSelected=0;
					System.out.println("Enter the item # of the item you would like to add to cart");
					itemSelected=sc.nextInt(); sc.nextLine();
					if(avail.containsKey(itemSelected)) availQty=avail.get(itemSelected);
					if(cart.containsKey(itemSelected)) {
						cartQty=cart.get(itemSelected);
						if(cartQty >= availQty) {      // out of stock
							System.out.println("Item is out of stock!");
						}
						else {
							cart.put(itemSelected, cartQty+1);   // add 1 to cart quantity
							System.out.println("Item added to cart");
						}
					}
					else {  // add new item to cart
						cart.put(itemSelected, 1);
						System.out.println("Item added to cart");
					}
					break;
				case 2:
					System.out.println("\n== Cart Items ===");
					System.out.println("Item#      Cart Qty");
					System.out.println("-----      --------");
					for(Map.Entry<Integer, Integer> m : cart.entrySet()) {
						System.out.printf("%-10d %-8d \r\n",
								m.getKey(),m.getValue()); 
					}
					break;
				case 3:		
					OrderDAO n = new OrderDAO();
					if(n.createOrder(cart)) {
						System.out.println("Checkout Completed");
						// update item in stock quantity
						for(Map.Entry<Integer, Integer> m : cart.entrySet()) {
							availQty=avail.get(m.getKey());
							availQty -= m.getValue();
							o.updateQuantityInStock(m.getKey(), availQty);   // update in stock quantity 
						}
					}
					else System.out.println("Checkout Failed");
					break;
				case 4:
					break;
				default:
					System.out.println("Try again");	
			} 
		}
		sc.close();
	}
	public static void printMenu()  {

		System.out.println("\nSelect a menu option:");
		System.out.println("1. Add item to cart");
		System.out.println("2. Display items in cart");
		System.out.println("3. Checkout");
		System.out.println("4. Quit \n");
	}

}
