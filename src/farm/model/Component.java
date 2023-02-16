package farm.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import farm.Visitor;

// This acts as an abstract class that serves for FarmItem, Equipment (Leaf) 
// and other classes that are Composite classes (Room, Building)

// This is taking the Pro-Transparency Approach to the Composite Design Pattern.
public interface Component {
	String getName();
	String toString();
	void setName(String name);	
	int getLocationX();
	void setLocationX(int locationX);
	int getLocationY();
	void setLocationY(int locationY);
	int getWidth();
	void setWidth(int width);
	int getHeight();
	void setHeight(int height);
	int getLength();
	void setLength(int length);
	// gets/sets the purchase price
	@Deprecated
	double getPrice();
	void setPrice(double price);
	// this is a dumb getter function
	double getPriceOnly();
	String getImageURL();
	void setImageURL(String imageUrl);
	
	// Methods for Composite Design Pattern
	// Specific methods for the StoragePlace Class
	public void addComponent(Component component);
	public boolean removeComponent(Component component);
	
	// Both NOT TESTED
	public ArrayList<Component> getChildren();
	public Iterator<Component> iterator();
	 
	// Method for Visitor Design Pattern
    void accept(Visitor vis);
    
    
	void printTree(int depth);
	void setRandomXY();

	// a unique ID for the class
	int getId();
	
	// Does this class's children have this Id?
	// boolean hasId(UUID id);
	
	// for StoragePlace class, NOTE:  NOT TESTED and UNUSED
	Component findComponent(int id);
	
	// get/set Market Value
	double getMarketValue();
	void setMarketValue(double marketValue);
	
	// This is a convenience method for debugging, just print a message that 
	// displays the value of many of the stored variables.
	void printVariableDebug();

}
