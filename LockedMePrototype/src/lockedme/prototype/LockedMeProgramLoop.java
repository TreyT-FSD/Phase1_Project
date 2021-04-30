package lockedme.prototype;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LockedMeProgramLoop {
	public static void main(String[] args) {
		boolean running = true;
		BufferedReader userInputReader = null;
		userInputReader = new BufferedReader(new InputStreamReader(System.in));
		int selectedAction = 0;
		
		LockedMeApp lockedMe = new LockedMeApp();
		
		//initial output to user
		lockedMe.printWelcomeMessage();
		
		//TODO: cleanup unused code
		//TODO: review functional requirements and manually test

		
		try {
			//start program loop
			while(running) {
				
				//display the available actions to the user
				lockedMe.printHomeMenu();
			
				//get the user input
				try {
					selectedAction = Integer.parseInt(userInputReader.readLine());
				} catch (NumberFormatException e) {
					//the user entered an invalid input
					selectedAction = 0;
				}
				
				//switch statement to handle the action
				
				switch (selectedAction) {
				case 1:
					//1) View files
					System.out.println("View files was selected. Current Files:");
					
					lockedMe.viewFiles();					
					
					System.out.println();
					System.out.println();
					break;
					
				case 2:
					//2) move to the file menu
					lockedMe.LockedMeFileMenu(userInputReader);
					break;
					
				case 3:
					//5) Exit			
					System.out.println("Exiting...");
					running=false;
					break;
	
				default:
					//if we made it to the default cause, the user did not enter a valid option
					System.out.println();
					System.out.println("Please select an action from the list by entering a single digit number.");
					System.out.println("Example: enter \"1\" to view files or \"5\" to exit.");
					System.out.println();
					System.out.println();
					
					break;
				}
			}
			
			
		} catch (IOException e) {
			//some error occurred while trying to read the user input
			System.err.println("Error: Failed to read in the user input");
			
		}  catch (Exception e) {
			//catchall for exceptions
			System.err.println("a general error occured");
			e.printStackTrace();
		}
		finally {
			if (userInputReader != null) {
				try {
					userInputReader.close();
				} catch (IOException e) {
					System.err.println("Error: Failed to close the userInput stream");
					e.printStackTrace();
				}
			}
		}
	}
}
