package farmpackage;

//Horse class extends the Animal class via inheritance
public final class Horse extends Animal {

	private String breed;
	private String colour;

	//Constructor which receives an array of strings {"Type","DoB","Breed","Colour"} 
	//Type and DOB passed to superclass(Animal), other variables assigned here.
	public Horse(String[] array) {
		super(array);
		this.breed = array[2];
		this.colour = array[3];
	}
	
	//This returns the breed of the Horse.
	public String getBreed() {
		return breed;
	}
	
	//This sets the breed of the Horse.
	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	//This returns the colour of the Horse.
	public String getColour() {
		return colour;
	}
	
	//This sets the colour of the Horse.
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	//This overrides the default toString() method to display the Breed and colour of Horse.
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
