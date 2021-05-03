package lockedme.prototype;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class contains the core functionality of the LockedMe Application Prototype
 * @author Trey Turley
 *
 */
public class LockedMeApp {
	
	private String lockedMeDirectoryName = "LockedMeDirectory";
	private File lockedMeDirectory;
	
	/**
	 * Default constructor for LockedMe
	 */
	public LockedMeApp() {
		lockedMeDirectory = new File(lockedMeDirectoryName);
		try{
			lockedMeDirectory.mkdir();
		} catch (SecurityException e) {
			System.err.println("Failed to create the directory. Program exiting...");
		}
	}
	
	/**
	 * Print a welcome message to the user with some basic information
	 */
	public void printWelcomeMessage() {
		System.out.println("LockedMe Prototype");
		System.out.println("Developer: Trey Turley");
		System.out.println();
		System.out.println();
	}
	
	/**
	 * Print out the home menu options.
	 */
	public void printHomeMenu() {
		System.out.println("Please select one of the available actions:");
		System.out.println();
		System.out.println("1) View files");
		System.out.println("2) File interaction (Search/Add/Delete)");
		System.out.println("3) Close the application");
		System.out.println();
		System.out.print("Selection: ");
	}
	
	/**
	 * Print out the file menu.
	 */
	public void printFileMenu() {
		System.out.println("Please select one of the available file actions:");
		System.out.println();
		System.out.println("1) Search for a file");
		System.out.println("2) Add a file");
		System.out.println("3) Delete a file");
		System.out.println("4) Return to the Home menu");
		
		System.out.println();
		System.out.print("Selection: ");
	}
	
	/**
	 * Output the list of files currently in the LockedMeApp's file directory in alphabetical order.
	 */
	public void viewFiles() {
		
		//get the files from the directory
		File[] files = lockedMeDirectory.listFiles();
		Set<File> fileSet = new TreeSet<>(Arrays.asList(files));
		
		//print out the names of the files only
		for (File file : fileSet) {
			if(file.isFile()) {
				System.out.println(file.getName());
			}
		}
	}
	
	/**
	 * Add an empty file with the given filename
	 * @param fileName the name of the new file
	 */
	public boolean addFile(String fileName) {
		//TODO: validate we are giving the correct messages to the user during add file
		if(!fileName.isEmpty()) {
			//new file name + path
			String newFilePath = lockedMeDirectory.getPath() + "\\" + fileName;
			
			//create the new file in the LockedMeApp's directory
			File newFile = new File(newFilePath);
			
			try {
				if(newFile.createNewFile()) {
					//it was created
					System.out.println("The file was added to the LockedMe directory.");
					return true;
				}
				else {
					//the file already exists
					System.out.println("The file was not added the LockedMe directory as it already exists.");
					return false;
				}
			} catch (IOException e) {
				System.out.println("There was an error while trying to create the file. Please try again.");
			} catch (SecurityException e) {
				System.out.println("Write access is denied. File was not created.");
			}
		}
		return false;
	}

	
	/**
	 * Delete the specified file from the LockedMeApp's directory
	 * @param fileName name of the file to delete.
	 * @return True if the file was found and deleted. False for all other cases.
	 */
	public boolean deleteFile(String fileName) {
		if(!fileName.isEmpty()) {
			File file = new File(lockedMeDirectory.getPath() + "\\" + fileName);
			
			File[] files = lockedMeDirectory.listFiles();
			Set<File> fileSet = new TreeSet<>(Arrays.asList(files));
			
			if(fileSet.contains(file)) {
				try {
					if(file.delete()) {
						System.out.println("The file was deleted from the LockedMe directory.");
					}
					else {
						System.out.println("The file was not deleted from the LockedMe directory.");
					}
				} catch(SecurityException e) {
					System.out.println("Access to file denied.");
				}
			}
			else {
				System.out.println("The file " + fileName + " was not found in the LockedMe directory.");
			}
		
		}
		return false;
	}
	
	/**
	 * Checks to see if the specified file exists.
	 * @param fileName The name of the file to look for.
	 * @return True is the file is found. False for all other cases.
	 */
	public boolean searchFile(String fileName) {
		if(!fileName.isEmpty()) {		
			File file = new File(lockedMeDirectory.getPath() + "\\" + fileName);
			
			File[] files = lockedMeDirectory.listFiles();
			Set<File> fileSet = new TreeSet<>(Arrays.asList(files));
			
			return fileSet.contains(file);
		}
		return false;
	}
	
	/**
	 * Validates the input string to see if it is valid input for one of the three options that requires user input for file name. 
	 * @param input the string to validate.
	 * @return True if it's valid input and false for all other cases.
	 */
	public boolean isValidInput(String input) {
		
		if(input == null || input.isEmpty()) {
			System.out.println("Invalid User Input.");
			return false;
		}
		else if (input.toLowerCase().compareTo("return") == 0) {
			return false;
		}
		
		//for all other cases return true
		return true;
	}
	
	/**
	 * Kicks off the home menu which has the main loop for accepting user input.
	 */
	public void run() {
		//initial output to user
		printWelcomeMessage();
		
		//launch the Home menu
		lockedMeHomeMenu();
	}
	
	/**
	 * This is the Home menu which provides several options for user interaction. It is the main user input loop. 
	 * From here the user can  select options to View the files in LockedMe, move to the file menu, or exit the application.
	 */
	public void lockedMeHomeMenu() {
		
		boolean running = true;
		BufferedReader userInputReader = null;
		userInputReader = new BufferedReader(new InputStreamReader(System.in));
		int selectedAction = 0;
		
		try {
			//start program loop
			while(running) {
				
				//display the available actions to the user
				printHomeMenu();
			
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
					
					viewFiles();					
					
					System.out.println();
					System.out.println();
					break;
					
				case 2:
					//2) move to the file menu
					LockedMeFileMenu(userInputReader);
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
			//catch-all for exceptions
			System.err.println("a general error occured");
			//e.printStackTrace();
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
	
	/**
	 * This is the file menu which presents options to the user for searching, adding, and deleting files from LockedMe.
	 * @param userInputReader a BufferedReader that can be used to get user input.
	 * @throws IOException if there is a problem trying to read the user input from userInputReader.
	 */
	public void LockedMeFileMenu(BufferedReader userInputReader) throws IOException {
		String userInput="";
		int selectedAction;
		boolean returnToHomeMenu = false;
		
		while(!returnToHomeMenu) {
			
			//present the file menu options to the user
			printFileMenu();			
		
			//get the user input
			try {
				selectedAction = Integer.parseInt(userInputReader.readLine());
			} catch (NumberFormatException e) {
				//the user entered an invalid input
				selectedAction = 0;
			}
			
			switch (selectedAction) {
					
			case 1:
				//1) Search for a file
				System.out.println("Enter a filename to search for or \"return\" to go back to the file menu.");
				
				userInput = userInputReader.readLine();
				
				if(this.isValidInput(userInput)) {
					if(searchFile(userInput)) {
						System.out.println("The file exists in the LockedMe directory.");
					}
					else {
						System.out.println("The file does not exist in the LockedMe directory");
					}
				} 
				else {
					System.out.println("Returning to the file menu.");
					
				}
				
				System.out.println();
				System.out.println();
				
				userInput="";
				break;
				
			case 2:
				//2) Add file
				System.out.println("Enter a name for the new file or \"return\" to go back to the file menu.");
				
				userInput = userInputReader.readLine();
				if(this.isValidInput(userInput)) {
					addFile(userInput);
				}
				else {
					System.out.println("Returning to the file menu.");
				}
				
				System.out.println();
				System.out.println();
				
				userInput="";
				break;
				
			case 3:
				//3) Delete file
				System.out.println("Enter the name of the file to be deleted or \"return\" to go back to the file menu.");
				
				userInput = userInputReader.readLine();
				
				if(this.isValidInput(userInput)) {
					deleteFile(userInput);
				}
				else {
					System.out.println("Returning to the file menu.");
				}					
				
				System.out.println();
				System.out.println();
				
				userInput="";
				break;
				
			case 4:
				//time to go back to the home menu
				returnToHomeMenu=true;
				break;
			
			default:
				//if we made it to the default cause, the user did not enter a valid option
				System.out.println();
				System.out.println("Please select an action from the list by entering a single digit number.");
				System.out.println("Example: enter \"1\" to search for a file or \"4\" to exit.");
				System.out.println();
				System.out.println();
				
				userInput="";
				break;
			}
			
		}
		 
	}
}
