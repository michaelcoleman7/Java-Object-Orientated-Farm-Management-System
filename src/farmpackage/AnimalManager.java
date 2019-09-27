package farmpackage;

//File Input/Output adapted from:
//https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html

//List adapted from:
//https://beginnersbook.com/2013/12/java-arraylist/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Class handles file input/output from text file
//creates a list to pass on animal objects for other classes to use
public class AnimalManager {

	// An Array list of Animal Objects(adds objects such as Cow, Sheep to a list to manage all the animal objects).
	private List<Animal> animalList = new ArrayList<Animal>();

	// Name and location of file used to store Animal details
	private String fileName = "savedFiles/Animals.txt";

	// Constructor for Animal Manager class.
	public AnimalManager() {
		loadAnimals();
	}

	// load information about Animals from text file
	private void loadAnimals() {
		String line = null;

		try {
			// Reads in input from text file
			FileReader fileReader = new FileReader(fileName);

			// Efficient reading of characters, arrays, and lines(recommended by article referenced above)
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// while - line is not null(empty), keep looping(reading and creating new classes)
			while ((line = bufferedReader.readLine()) != null) {
				
				// splits up line containing animal info(divided by spaces) into an array to be passed to class(Easier than creating multiple string variables)
				String[] splitLine = line.split(" ");
				
				//set type = to first member of array(array[0] e.g. Cow)
				String type = splitLine[0];

				// if type is a Cow then add to list(animalList)
				if (type.equalsIgnoreCase("Cow")) {
					//Create a Cow class
					Cow temp = new Cow(splitLine);
					
					//Temp object added to list
					animalList.add(temp);
				}
				
				// if type is a Sheep then add to list(animalList)
				if (type.equalsIgnoreCase("Sheep")) {
					//Create a Sheep class
					Sheep temp = new Sheep(splitLine);
					
					//Temp object added to list
					animalList.add(temp);
				}
				
				// if type is a Pig then add to list(animalList)
				if (type.equalsIgnoreCase("Pig")) {
					//Create a Pig class
					Pig temp = new Pig(splitLine);
					
					//Temp object added to list
					animalList.add(temp);
				}
				
				// if type is a Horse then add to list(animalList)
				if (type.equalsIgnoreCase("Horse")) {
					//Create a Horse class
					Horse temp = new Horse(splitLine);
					
					//Temp object added to list
					animalList.add(temp);
				}

			} // end while

			//Close files.
			bufferedReader.close();
		}
		//error handling - if file cannot be found/opened
		catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} 
		//error handling - if file cannot be read etc...
		catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}

	// Returns a list of animal objects
	public List<Animal> getAnimalList() {
		return animalList;
	}
	
	// save to textFile
	public void saveAnimals(List<Animal> lst) {
		try {
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("savedFiles/Animals.txt"));
				
		        for (int i = 0; i < lst.size(); i++) {
			 		Animal animal = lst.get(i);
			 		bufferedWriter.write(animal.objectToAString()+" \n");
			 	}
		        bufferedWriter.close();
        }
		//runs if unable to save the file
         catch (Exception e) {
			System.out.println("Unable to Save to File");
		}
	}
	// Save farm details - name and balance
	public void saveFarmDetails(String name, double bal) {
		try {
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("savedFiles/Farm.txt"));
	
			 	bufferedWriter.write(name+" "+bal);
			 	
		        bufferedWriter.close();
        }
		//runs if unable to save the file
         catch (Exception e) {
			System.out.println("Unable to Save to File");
		}
	}

}
