package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import farm.MarketPriceVisitor;
import farm.PurchasePriceVisitor;
import farm.model.FarmItem;
import farm.model.StoragePlace;



class TestVisitorPattern {
	
	// This is test data that models the "Implement Visitor Pattern" on the assignment
	StoragePlace Building1 = new StoragePlace("Building-1");
	StoragePlace Room1 = new StoragePlace("Room-1");
	FarmItem Cattle1 = new FarmItem("Cattle-1");
	FarmItem Equipment1 = new FarmItem("Equipment-1");
	
	// This is a constructor the is ran before the testing begins.
	TestVisitorPattern() {
		Building1.setPrice(5000);
		Room1.setPrice(3000);
		Building1.addComponent(Room1);
		Cattle1.setPrice(500);
		Cattle1.setMarketValue(500);
		Room1.addComponent(Cattle1);
		Equipment1.setPrice(500);		// These differ
		Equipment1.setMarketValue(400); // These differ
		Building1.addComponent(Equipment1);
	}
	
	
	@Test
	void testPurchasePriceVisitor() {
		PurchasePriceVisitor purchaseVisitor = new PurchasePriceVisitor();
		
		// Select Cattle-1 expect Purchase Price: 500
		Cattle1.accept(purchaseVisitor);
		assertEquals(500.0, purchaseVisitor.visit(Cattle1));
		
		// Select Building-1 expect PurchasePrice: 9000
		Building1.accept(purchaseVisitor);
		assertEquals(9000.0, purchaseVisitor.visit(Building1));	
	}
	
	@Test
	void testMarketPriceVisitor() {
		MarketPriceVisitor marketVisitor = new MarketPriceVisitor();

		// Select Cattle-1 expect Current Market Value: 500
		Cattle1.accept(marketVisitor);
		assertEquals(500.0, marketVisitor.visit(Cattle1));

		// Select Room-1 expect Current Market Value: 500
		Room1.accept(marketVisitor);
		assertEquals(500.0, marketVisitor.visit(Room1));

		
		// Select Building-1 expect Current Market Value: 900
		Building1.accept(marketVisitor);
		assertEquals(900.0, marketVisitor.visit(Building1));

	}

}
