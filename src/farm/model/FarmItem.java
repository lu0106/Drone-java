package farm.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import farm.Visitor;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class FarmItem implements Component {
	private StringProperty name;
	
	// NOTE Do any of these these need to be doubles?
	private int locationX, locationY, width, height, length;
	// This is the purchase price.
	private double price;
	
	// This is the Market Value
	private double marketValue;
	

	// Tried to make this a javafx.scene.image.Image, but this didn't work with JUnit Testing,
	// Which gave an exception when using an image "Internal graphics not initialized yet".
	// So I've resorted to keeping the imageUrl as a String.
	private String imageUrl;

	private int id;
	
	/**
	 * Default constructor.
	 */
	public FarmItem() {
		this(null, -1, -1, -1, -1, -1, 0.0, "img/question-mark.png");
	}
	
	/**
	 *  Constructor with single parameter
	 */
	public FarmItem(String name) {
		this(name, -1, -1, -1, -1, -1, 0.0, "img/question-mark.png");
	}

	/**
	 * 	Constructor with multiple parameters
	 */
	public FarmItem(String name, int locationX, int locationY, int width, 
			int height, int length, double price, String imageUrl) {
		this.name = new SimpleStringProperty(name);
		this.setLocationX(locationX);
		this.setLocationY(locationY);
		this.setWidth(width);
		this.setHeight(height);
		this.setLength(length);
		this.setPrice(price);
		// Initially set the Market Price to the Purchase Price
		this.setMarketValue(price);
		this.setImageURL(imageUrl);
		
		id = UUID.randomUUID().hashCode();
	}
	
	
	
	public String getName() {
		return name.get();
	}
	
	public String toString() {
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

	@Deprecated
	public double getPrice() {
		System.out.println("This is a deprecated method");
		return price;
	}

	/*
	 * This gets the Purchase Price
	 */
	public double getPriceOnly() {
		return price;
	}
	
	/*
	 * This sets the Purchase Price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void addComponent(Component component) {
		throw new UnsupportedOperationException();		
	}

	public boolean removeComponent(Component component) {
		throw new UnsupportedOperationException();
	}

	public String getImageURL() {
		return this.imageUrl;
	}

	public void setImageURL(String imageUrl) {
		this.imageUrl = imageUrl;
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
	

	
	public void printTree(int depth) {
		String depth_tab = "";

		for(int i=0; i < depth; ++i) {
			 depth_tab += '\t';
		}
		
//		System.out.println(depth_tab + name);
		System.out.println(depth_tab + this.getName());
	}

	
	public void accept(Visitor vis) {
		vis.visit(this);
	}


	public ArrayList<Component> getChildren() {
		throw new UnsupportedOperationException();		
	}

	public Iterator<Component> iterator() {
		throw new UnsupportedOperationException();		
	}
	
	public int getId() {
		return id;
	}

	public Component findComponent(int id) {
		throw new UnsupportedOperationException();		
	}

	public double getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}
	
	public void printVariableDebug() {
		System.out.print("name: " + this.getName() + " - X: " + this.getLocationX() + "- Y: " + this.getLocationY() 
			+ " - Width: " + this.getWidth() + " - Height: " + this.getHeight() + " - Price:" + this.getPriceOnly() 
			+ " - Image URL: " + this.getImageURL() + "\n");
	}
}
