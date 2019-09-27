package farmpackage;

import java.util.Scanner;
import java.util.List;

public class Runner {
	private static FarmManager farmManager;
	private static int option;
	private static final int SENTINAL_99=99;

	//Main Method
	public static void main(String[] args) {
		// creates new FarmManager Object
		farmManager = new FarmManager();

		// loop the Menu until user enters 99 to quit
		while (option != SENTINAL_99) {
			// method call to menu that selects action
			option = chooseOption();

			// switch for user choice
			switchOption(option);

		}

	}

	public static int chooseOption() {

		int choice;
		Scanner input = new Scanner(System.in);

		System.out.println("Choose from these choices");
		System.out.println("------------------------------------------");
		System.out.println("1 - View Farm Information");
		System.out.println("2 - Manage Animals");
		System.out.println("3 - Manage Farm Account");
		System.out.println("99 - Quit and Save Changes");
		System.out.println("------------------------------------------\n");

		choice = input.nextInt();
		return choice;
	}

	public static void switchOption(int opt) {
		Scanner input = new Scanner(System.in);

		switch (opt) {
		case 1:
			System.out.println("------------------------------------------\n");
			System.out.println("Farm Information\n");
			System.out.println("------------------------------------------\n");
			System.out.println(farmManager.getFarmerName() + "'s farm has a balance of: " + farmManager.getBalance() + "\n");
			System.out.println("Farm has a total of " + farmManager.getAnimalList().size() + " Animals");
			displayAnimals();
			break;

		case 2:
			//add,remove and view animal objects
			System.out.println("Options for Managing Animals:");
			System.out.println("1 - Add Animal");
			System.out.println("2 - Remove Animal");
			System.out.println("3 - View Animals");
			System.out.println("99 - Return to Menu");

			int optionAddRemoveView;
			optionAddRemoveView = input.nextInt();

			// add new animal object to array
			if (optionAddRemoveView == 1) {
				String animalType;
				String animaldob;
				String animalBreed;
				String animalColour;
				boolean invalid;
				
				//enter animal type
				do{
				System.out.println("Enter type of Animal(Choose from Cow, Sheep, Pig or Horse) you want to add: ");
				animalType = input.next();
				if (animalType.equalsIgnoreCase("Cow")) {
					invalid=false;
				} else if (animalType.equalsIgnoreCase("Sheep")) {
					invalid=false;
				} else if (animalType.equalsIgnoreCase("Pig")) {
					invalid=false;
				} else if (animalType.equalsIgnoreCase("Horse")) {
					invalid=false;
				} else {
					System.out.println(animalType + " - is an invalid Type of Animal\n");
					invalid=true;
				}}while(invalid==true);
				
				// do while to ensure correct D.O.B size entered using error handling
				do{
					System.out.println("Enter Animal Date of Birth(dd/mm/yy):");
					animaldob = input.next();
					
					try {
						checkDate(animaldob);
					} catch (DateException e) {
						//Print error message
						System.err.println(e.getMessage());
					}
				}while(animaldob.length()!=8);
				
				System.out.println("Enter Animal Breed:");
				animalBreed = input.next();
				System.out.println("Enter Animal Colour:");
				animalColour = input.next();
				// Add input to array format for use
				String[] newAnimal = { animalType, animaldob, animalBreed, animalColour };
				
				//If correct Animal type create Animal object and display to user
				if (animalType.equalsIgnoreCase("Cow")) {
					Cow cow = new Cow(newAnimal);
					farmManager.getAnimalList().add(cow);
					System.out.println("Added: " + cow);
				} else if (animalType.equalsIgnoreCase("Sheep")) {
					Sheep sheep = new Sheep(newAnimal);
					farmManager.getAnimalList().add(sheep);
					System.out.println("Added: " + sheep);
				} else if (animalType.equalsIgnoreCase("Pig")) {
					Pig pig = new Pig(newAnimal);
					farmManager.getAnimalList().add(pig);
					System.out.println("Added: " + pig);

				} else if (animalType.equalsIgnoreCase("Horse")) {
					Horse horse = new Horse(newAnimal);
					farmManager.getAnimalList().add(horse);
					System.out.println("Added: " + horse);

				} else {
					System.out.println(animalType + " - is an invalid Type of Animal\n");
				}

			}
			// Remove animal from array
			else if (optionAddRemoveView == 2) {
				int optionRemove;
				// show animals so user can see the number they wish to remove
				displayAnimals();

				System.out.println("Enter animal number to remove:");
				optionRemove = input.nextInt();

				if (optionRemove > farmManager.getAnimalList().size() + 1) {
					System.out.println("Invalid Option");
				} 
				else {
					String optionYN;
					int arrayNumber = optionRemove - 1;

					do {
						System.out.println("Removing");
						System.out.println(farmManager.getAnimalList().get(arrayNumber));
						System.out.println("Are you sure? Y/N:");
						optionYN = input.next();
					} while (!optionYN.equalsIgnoreCase("Y") && !optionYN.equalsIgnoreCase("N"));

					if (optionYN.equalsIgnoreCase("Y")) {
						farmManager.getAnimalList().remove(arrayNumber);
						System.out.println("Removed");
					}
					if (optionYN.equalsIgnoreCase("N")) {
						System.out.println("Animal was not removed");
					}
				}
			}
			// View different types of animals e.g view all sheep,cows horse etc..
			else if (optionAddRemoveView == 3) {
				int displayOption;
				do {
					System.out.println("Which Animals would you like to display:");
					System.out.println("1 - All Animals");
					System.out.println("2 - Display All Cows");
					System.out.println("3 - Display All Sheep");
					System.out.println("4 - Display All Pigs");
					System.out.println("5 - Display All Horses");
					System.out.println("99 - Return to Menu");
					displayOption = input.nextInt();

					List<Animal> list = farmManager.getAnimalList();

					if (displayOption == 1) {
						displayAnimals();
					}
					//display all cow objects
					else if (displayOption == 2) {
						//loop through list and create objects
						for (int i = 0; i < list.size(); i++) {
							Animal animal = list.get(i);

							if (animal.getType().equalsIgnoreCase("Cow")) {
								Cow cow = (Cow) animal;
								System.out.println(cow);
							}
						}
						System.out.println();
					}
					//display all sheep objects
					else if (displayOption == 3) {
						for (int i = 0; i < list.size(); i++) {
							Animal animal = list.get(i);

							if (animal.getType().equalsIgnoreCase("Sheep")) {
								Sheep sheep = (Sheep) animal;
								System.out.println(sheep);
							}
						}										
						System.out.println();
					}
					//display all pig objects
					else if (displayOption == 4) {
						for (int i = 0; i < list.size(); i++) {
							Animal animal = list.get(i);

							if (animal.getType().equalsIgnoreCase("Pig")) {
								Pig pig = (Pig) animal;
								System.out.println(pig);
							}
						}
						System.out.println();
					}
					//display all horse objects
					else if (displayOption == 5) {
						for (int i = 0; i < list.size(); i++) {
							Animal animal = list.get(i);

							if (animal.getType().equalsIgnoreCase("Horse")) {
								Horse horse = (Horse) animal;
								System.out.println(horse);
							}
						}
						System.out.println();
					}
					// To stop invalid option println printing when 99 entered to exit
					else if (displayOption == SENTINAL_99) {
					}

					else {
						System.out.println("Invalid Option - Please Choose Valid Option\n");
					}
				} while (displayOption != SENTINAL_99);//exit do while
			}
			else if (optionAddRemoveView == SENTINAL_99) {
				break;
			}
			else {
				System.out.println("Invalid Option - Please Choose Valid Option");
			}
			break;

		case 3:
			//Change Farm account details
			int accountOption;
			do{
			System.out.println("Options for Managing Farm Account:");
			System.out.println("1 - View Farmer Details");
			System.out.println("2 - Change Farmer Name");
			System.out.println("3 - Change Farm Balance");
			System.out.println("99 - Return to Menu");
			accountOption = input.nextInt();
			
			if (accountOption == 1) {
				System.out.println(farmManager.getFarmerName() + "'s farm has a balance of: " + farmManager.getBalance());
				System.out.println("Farm has a total of " + farmManager.getAnimalList().size() + " Animals\n");
			}
			//Change Farmer Name
			else if (accountOption == 2) {
				String farmerName;
				String oldfarmerName;
				oldfarmerName=farmManager.getFarmerName();
				System.out.println("Enter New Farmer Name:");
				farmerName = input.next();
				farmManager.setFarmerName(farmerName);
				System.out.println("Farmer name Changed from "+ oldfarmerName +" to: " + farmManager.getFarmerName());

			}
			
			// Allow for addition and subtraction of farm balance
			else if (accountOption == 3) {
				int addOrSubtractOption;
				do{
				System.out.println("Add Or Subtract To Balance:");
				System.out.println("1 - Add to Balance");
				System.out.println("2 - Subtract from Balance");
				System.out.println("99 - Return to Farm Menu");
				addOrSubtractOption = input.nextInt();
				
				//Add to Balance
				if (addOrSubtractOption == 1) {
					double increaseAmt;
					do{
					System.out.println("Enter the Amount you wish to add:");
					increaseAmt = input.nextDouble();
					
					if(increaseAmt<0){
						System.out.println("Number cannot be added from Balance - enter number greater than or equal to 0.");
					}}while(increaseAmt<0);
					
					farmManager.increaseBalance(increaseAmt);
					System.out.println("Balance topped up by " + increaseAmt + " to: " + farmManager.getBalance());
					
					// Subtract from balance
				} else if (addOrSubtractOption == 2) {
					double decreaseAmt;
					do{
					System.out.println("Enter the Amount you wish to subtract:");
					decreaseAmt = input.nextDouble();
					
					if(decreaseAmt<0){
						System.out.println("Number cannot be decreased from Balance - enter number greater than or equal to 0.");
					}}while(decreaseAmt<0);
					
					farmManager.decreaseBalance(decreaseAmt);
					System.out.println("Balance decreased by " + decreaseAmt + " to: " + farmManager.getBalance());
						
					//Handle invalid input
				} 
				else if (addOrSubtractOption == SENTINAL_99){
					break;
				}
				else {
					System.out.println("Invalid Option - Please Choose Add or Subtract\n");
				}}while(addOrSubtractOption!=SENTINAL_99);
			}
			//prevent invalid dialogue showing
			else if (accountOption == SENTINAL_99) {
			}
			//Handle invalid input
			else {
				System.out.println("Invalid Option - Please Choose Valid Farm Account Option");
			}}while(accountOption != SENTINAL_99);
			
				break;
			
		// Exit Application and Save updated animals/farm details to files
		case SENTINAL_99:
			System.out.println("Have a Good Day!!!");
			farmManager.save();
			break;

		default:
			System.out.println("Invalid Option - Please Choose Valid Option");
			break;
		}
	}
	// Display all Animals in Array
	private static void displayAnimals() {
		System.out.println("---------------------------------------------------------");
		System.out.println("Animal List");
		System.out.println("---------------------------------------------------------");
		for (int i = 0; i < farmManager.getAnimalList().size(); i++) {
			Animal animal = farmManager.getAnimalList().get(i);
			System.out.println(i + 1 + ": " + animal);
		}
		System.out.println("---------------------------------------------------------");
	}
	// Error handling for Date of Birth input 
	private static void checkDate(String date)throws DateException {
		if(date.length()!=8){
			throw new DateException("Incorrect date format");
		}	
	}
}
