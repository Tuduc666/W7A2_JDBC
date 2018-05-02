package w7a2_jdbc.JD.mainentry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import w7a2_jdbc.JD.DAO.UserDAO;
import w7a2_jdbc.JD.model.User;

public class MainPoint {

	public static void main(String[] args) throws IOException, SQLException {
		UserDAO o = new UserDAO();
		Scanner reader = new Scanner(System.in);
		User user = null;
		Integer isRegister = null;
		Integer choice = 0;
		while(choice != 4) {
			printMenu();
			choice = reader.nextInt(); reader.nextLine();
			switch(choice) {
				case 1:
					user = new User();
					System.out.println("What is your email?");
					user.setUser_email(reader.nextLine());
					System.out.println("What is your name?");
					user.setUser_name(reader.nextLine());
					System.out.println("What is your password?");
					user.setUser_password(reader.nextLine());
					isRegister = o.register(user);
					if(isRegister != null) {
						System.out.println("Successfully Registered");
					}
					else System.out.println("Failed to Register");
					break;
				case 2:
					System.out.println("What is your email?");
					String email = reader.nextLine();
					System.out.println("What is your password?");
					String password = reader.nextLine();
					user = o.login(email, password);
					if(user != null) System.out.println("You have logged in");
					else System.out.println("Login Failed");
					break;
				case 3:					
					if(user != null) {
						System.out.println("What is your name?");
						user.setUser_name(reader.nextLine());
						System.out.println("What is your password?");
						user.setUser_password(reader.nextLine());
						o.update(user);
						System.out.println("Updated");
					}
					else System.out.println("You must Login or Register");
					break;
				case 4:
					break;
				default:
					System.out.println("Try again");
				
			} 
			reader.close();
			
		}
		

			

	}
	public static void printMenu() {
		System.out.println("\nMenu");
		System.out.println("1. Register");
		System.out.println("2. Login");
		System.out.println("3. Update info");
		System.out.println("4. Quit \n");
	}

}
