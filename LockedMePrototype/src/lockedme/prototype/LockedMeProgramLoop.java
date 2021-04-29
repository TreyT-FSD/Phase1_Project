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
		String userInput="";
		
		LockedMeApp lockedMe = new LockedMeApp();
		
		//initial output to user
		System.out.println("LockedMe Prototype");	
		System.out.println();
		System.out.println();
		
		try {
			//start program loop
			while(running) {
				
				//display the available actions to the user
				lockedMe.printMainOptions();
			
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
					//2) Search for a file
					System.out.println("Enter a filename to search for or \"return\" to go back to the main menu.");
					
					
					userInput = userInputReader.readLine();
					if(lockedMe.isValidINput(userInput)) {
						//String userFileName = userInputReader.readLine();
						if(lockedMe.searchFile(userInput)) {
							System.out.println("The file exists in the LockedMe directory.");
						}
						else {
							System.out.println("The file does not exist in the LockedMe directory");
						}
					} 
					else {
						System.out.println("Returning to main menu.");
						
					}
					
					System.out.println();
					System.out.println();
					
					userInput="";
					break;
					
				case 3:
					//3) Add file
					System.out.println("Enter a name for the new file or \"return\" to go back to the main menu.");
					
					userInput = userInputReader.readLine();
					
					//String userFileName = userInputReader.readLine();
					if(lockedMe.addFile(userInput)) {
						System.out.println("The file was added to the LockedMe directory.");
					}
					else {
						System.out.println("The file was not added the LockedMe directory.");
					}
					
					System.out.println();
					System.out.println();
					
					userInput="";
					break;
					
				case 4:
					//4) Delete files
					System.out.println("Enter the name of the file to be deleted or \"return\" to go back to the main menu.");
					
					userInput = userInputReader.readLine();
					
					if(lockedMe.deleteFile(userInput)) {
						System.out.println("The file was deleted from the LockedMe directory.");
					}
					else {
						System.out.println("The file was not deleted from the LockedMe directory");
					}
					
					System.out.println();
					System.out.println();
					
					userInput="";
					break;
					
				case 5:
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
					
					userInput="";
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
