package lockedme.prototype;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class contains the core functionality of the LockedMe Application Prototype
 * @author Trey_Turley
 *
 */
public class LockedMeApp {
	
	//private FileSystem currentWorkingDirectory;	
	//private Path lockedMePath;
	
	private String lockedMeDirectoryName = "LockedMeDirectory";
	private File lockedMeDirectory;
	
	public LockedMeApp() {
		lockedMeDirectory = new File(lockedMeDirectoryName);
		try{
			lockedMeDirectory.mkdir();
		} catch (SecurityException e) {
			System.err.println("Failed to create the directory. Program exiting...");
		}
	}
	
	/**
	 * Print out the main menu options.
	 */
	public void printMainOptions() {
		System.out.println("Please select one of the available actions:");
		System.out.println();
		
		System.out.println("1) View files");
		System.out.println("2) Search for a file");
		System.out.println("3) Add a file");
		System.out.println("4) Delete a file");
		System.out.println("5) Exit the application");
		
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
		if(!fileName.isEmpty()) {
			//new file name + path
			String newFilePath = lockedMeDirectory.getPath() + "\\" + fileName;
			
			//create the new file in the LockedMeApp's directory
			File newFile = new File(newFilePath);
			
			try {
				return newFile.createNewFile();			
			} catch (IOException e) {
				System.out.println("There was an error while trying to create the file. Please try again.");
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
				return file.delete();
			}
		
		}
		return false;
	}
	
	/**
	 * Checks to see if the specified file exists.
	 * @param fileName The name of the file to look for
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
	 * @param input the string to validate
	 * @return True if it's valid input and false for all other cases.
	 */
	public boolean isValidINput(String input) {
		
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
}
