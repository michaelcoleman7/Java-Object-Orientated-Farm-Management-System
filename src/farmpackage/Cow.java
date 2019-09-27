package farmpackage;

//Cow class extends the Animal class via inheritance
public final class Cow extends Animal {
	private String breed;
	private String colour;

	//Constructor which receives an array of strings {"Type","DoB","Breed","Colour"} 
	//Type and DOB passed to superclass(Animal), other variables assigned here.
	public Cow(String[] array) {
		super(array);
		this.breed = array[2];
		this.colour = array[3];
	}
	
	//This returns the breed of the Cow.
	public String getBreed() {
		return breed;
	}
	
	//This sets the breed of the Cow.
	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	//This returns the colour of the Cow.
	public String getColour() {
		return colour;
	}
	
	//This sets the colour of the Cow.
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	//This overrides the default toString() method to display the Breed and colour of Cow.
	@Override
	public String toString() {
		return getType() +  " [Breed: " + breed + ", Colour: " + colour + ", Date of Birth: " + getDob() +"]";
	}

	//Adds implemented method from Interface which is received through inheritance from Animal - Must be implemented
	@Override
	public String objectToAString() {
		String str = this.getType()+" "+this.getDob()+" "+this.getBreed()+" "+this.getColour();
		return str;
	}	
}
