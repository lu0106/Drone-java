package tests;

import farm.model.FarmItem;
import farm.model.StoragePlace;

//This is a model for testing Visitor Pattern, not anything else.
//So that is why it has public members.
public class ModelForVisitor {

	public StoragePlace Building1 = new StoragePlace("Building-1");
	public StoragePlace Room1 = new StoragePlace("Room-1");
	public FarmItem Cattle1 = new FarmItem("Cattle-1");
	FarmItem Equipment1 = new FarmItem("Equipment-1");
	
	public void ModelforVisitor () {
		Building1.setPrice(5000);
		Room1.setPrice(3000);
		Building1.addComponent(Room1);
		Cattle1.setPrice(500);
		Room1.addComponent(Cattle1);
		Equipment1.setPrice(500);
		
		
	}

}
