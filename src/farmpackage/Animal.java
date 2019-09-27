package farmpackage;

//This class is abstract, Other classes such as Cow and Sheep will be extended from this class 

public abstract class Animal implements Printable {
	private String type;
	private String dob;
	
	//Constructor
	public Animal(String[] array) {
		this.type = array[0];
		this.dob = array[1];
	}
	
	//This returns the type of animal eg.Cow or Sheep
	public String getType() {
		return type;
	}
	
	//This sets the type of animal
	public void setType(String type) {
		this.type = type;
	}
	
	//This returns the date of birth for the animal
	public String getDob() {
		return dob;
	}
	
	//This sets the date of birth for the animal
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	//This overrides the default toString() method to display the type and DOB of Animal.
	@Override
	public String toString() {
		return "Animal [Type: " + type + ", D.O.B: " + dob + "]";
	}
	
}
