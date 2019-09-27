package farmpackage;

//File Input/Output adapted from:
//https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FarmManager {
	private AnimalManager manager;
	private String farmerName;
	private double balance;
	private List<Animal> animalList;
	// Name and location of file used to store Farm details
	private String fileName = "savedFiles/Farm.txt";

	// Constructor
	public FarmManager() {
		// load name and balance from file if available
		// else prompt for name and balance
		load();
	}

	// return FarmerName
	public String getFarmerName() {
		return farmerName;
	}

	// sets FarmerName
	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}

	// return balance
	public double getBalance() {
		return balance;
	}

	// increase balance
	public void increaseBalance(double balance) {
		this.setBalance(this.balance += balance);
	}

	// decrease balance
	public void decreaseBalance(double balance) {
		this.setBalance(this.balance -= balance);
	}

	// sets balance
	public void setBalance(double balance) {
		this.balance = balance;
	}

	// return Animal List
	public List<Animal> getAnimalList() {
		return this.animalList;
	}

	// set Animal list
	public void setAnimalList(List<Animal> animals) {
		this.animalList = animals;
	}

	// load input stream from Farm.txt and calls loadAnimals() method.
	public void load() {
		String line = null;

		try {
			// Reads in input from text file
			FileReader fileReader = new FileReader(fileName);

			// Efficient reading of characters, arrays, and lines(recommended by article referenced above)
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			line = bufferedReader.readLine();

			if (line == null) {
				String farmerDetails;
				Scanner input = new Scanner(System.in);

				// ask for input
				System.out.println("Add new farmer and balance here: ");
				farmerDetails = input.nextLine();
				// set input as line
				line = farmerDetails;
			}

			// splits up line containing Farm info(divided by spaces) into an array to be passed to class(Easier than creating multiple string variables)
			String[] splitLine = line.split(" ");

			// sets Name to first position in array
			this.setFarmerName(splitLine[0]);

			// sets Balance from second position in array(parses from a string
			// to a Double)
			this.setBalance(Double.parseDouble(splitLine[1]));

			// Close files.
			bufferedReader.close();
		}
		// error handling - if file cannot be found/opened
		catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		}
		// error handling - if file cannot be read etc...
		catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}

		loadAnimals();
	}

	// Creates new AnimalManager and sets this.animallist to objects in animal list from AnimalManager class
	public void loadAnimals() {
		// creates new AnimalManager
		manager = new AnimalManager();

		// gets animal list from AnimalManager class
		this.setAnimalList(manager.getAnimalList());
	}

	//Save Animals - passes farmManager (this) lists to Animal Manager Save Methods
	public void save() {
		manager.saveAnimals(this.getAnimalList());
		manager.saveFarmDetails(this.getFarmerName(), this.getBalance());
	}

}
