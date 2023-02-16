package farm.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import farm.Visitor;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StoragePlace implements Component {

	private ArrayList<Component> componentArray = new ArrayList<Component>();

	private StringProperty name;
	// NOTE Do any of these these need to be doubles?
	private int locationX, locationY, width, height, length;
	private double price;
	private String imageUrl;
	
	private int id;
	
	/**
	 * Default constructor.
	 */
	public StoragePlace() {
		this(null, -1, -1, -1, -1, -1, 0.0, "/img/question-mark.png");
	}
	
	/**
	 *  Constructor with single parameter
	 */
	public StoragePlace(String name) {
		this(name, -1, -1, -1, -1, -1, 0.0, "/img/question-mark.png");
	}

	/**
	 * 	Constructor with multiple parameters
	 */
	public StoragePlace(String name, int locationX, int locationY, int width, 
			int height, int length, double price, String imageUrl) {

		this.name = new SimpleStringProperty(name);
		this.setLocationX(locationX);
		this.setLocationY(locationY);
		this.setWidth(width);
		this.setHeight(height);
		this.setLength(length);
		this.setPrice(price);
		this.setImageURL(imageUrl);
		// assigns a random id to the object
		id = UUID.randomUUID().hashCode();
	}

	/* ============== Getters and Setters ============== */
	public String getName() {
		return name.get();
	}
	
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public int getLocationX() {
		return locationX;
	}

	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}

	public int getLocationY() {
		return locationY;
	}

	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	/*
	 *  This function is deprecated, Visitors can do this functionality.
	 */
	@Deprecated
	public double getPrice() {
		System.out.println("This is a deprecated method");
		double priceSum = 0;
		
		Iterator<Component> iter = componentArray.iterator();
		while( iter.hasNext() ) {
			Component childComponent = (Component) iter.next();
			priceSum += childComponent.getPrice();
		}
		return this.price + priceSum;
	}

	
	public double getPriceOnly() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getImageURL() {
		return this.imageUrl;
	}

	public void setImageURL(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	/* ============== Composite Design Methods ============== */
	public void addComponent(Component component) {
		componentArray.add(component);
	}
	
	/**
	 * this removes the component.
	 * returns boolean true/false if component was removed from the list.
	 */
	public boolean removeComponent(Component component) {
		return componentArray.remove(component);
	}

	
	/*
	 * setRandomXY - This gives the X, Y coordinates a random value. 
	 */
	public void setRandomXY() {
		// Assume that the maximum value for X is 800
		this.locationX = ThreadLocalRandom.current().nextInt(0, 800 + 1);
		// Assume that the maximum value for Y is 600
		this.locationY = ThreadLocalRandom.current().nextInt(0, 600 + 1);
	}

	public String toString() {
		String out = this.name.get();
		return out;
	}
	
	/* 
	 * Prints tree linearly. 
	 */
	public void printTree(int depth) {
		String depth_tab = "";

		for(int i=0; i < depth; ++i) {
			 depth_tab += '\t';
		}
		
//		System.out.println(depth_tab + name);
		System.out.println(depth_tab + this.getName());
		Iterator<Component> iter = componentArray.iterator();
		while( iter.hasNext ()) {
			Component comp = iter.next();
			comp.printTree(depth+1);
		}
	}
	
	public void accept(Visitor vis) {
		vis.visit(this);
	}
	
    public ArrayList<Component> getChildren() {
        return componentArray;
    }

    public Iterator<Component> iterator() {
		return componentArray.iterator();
    }
    
	public int getId() {
		return id;
	}
	
	
	// find the Component object
	public Component findComponent(int id) {
/*		Iterator<Component> compIter = componentArray.iterator(); */
		
		// this is a for each loop
		for (Component element : componentArray) {
			if(element.getId() == id) {
				return element;
			}
			
			if(element instanceof StoragePlace) {
				// recursive call for this element
				Component foundComp = element.findComponent(id);
				if(foundComp != null) {
					return foundComp;
				}
			}
		}
		return null;
	}
	
	public double getMarketValue() {
		throw new UnsupportedOperationException();
	}

	public void setMarketValue(double marketValue) {
		throw new UnsupportedOperationException();
	}

	public void printVariableDebug() {
		System.out.print("name: " + this.getName() + " - X: " + this.getLocationX() + "- Y: " + this.getLocationY() 
			+ " - Width: " + this.getWidth() + " - Height: " + this.getHeight() + " - Price:" + this.getPriceOnly() 
			+ " - Image URL: " + this.getImageURL() + "\n");
	}
}
