package tests;

import org.junit.Test;

import farm.Visitor;
import farm.model.Component;
import farm.model.FarmItem;
import farm.model.StoragePlace;
import farm.view.PrintingVisitor;


public class TestVisitorPrint implements Visitor {
	public double visit(Component comp) {
		    System.out.println(comp);
		    return 0.0;
	} 


	@Test 
	public void testPrint() {
		
		System.out.println("===== TestVistorPrint.testPrint() =====");
		
		FarmItem saw = new FarmItem("Saw");
		FarmItem nailGun = new FarmItem("Nail Gun");
		StoragePlace shelf7 = new StoragePlace("Shelf 7");
		StoragePlace cabinet = new StoragePlace("Cabinet");
		StoragePlace shed = new StoragePlace("Shed");
		shelf7.addComponent(saw);
		shelf7.addComponent(nailGun);
		shed.addComponent(shelf7);
		shed.addComponent(cabinet);
		
		PrintingVisitor vis = new PrintingVisitor();
		shed.accept(vis);
		double x = this.visit(shed);	// This prints an extra Shed -  Why?

	}
	
	// THIS FUNCTIONS IS NOT A UNIT TEST, IT IS NOT A VISITOR
/*	@Test
	public void testPrint2() {
		
		System.out.println("===== TestVistorPrint.testPrint2() =====");
		FarmItem saw = new FarmItem("Saw");
		FarmItem nailGun = new FarmItem("Nail Gun");
		StoragePlace shelf7 = new StoragePlace("Shelf 7");
		StoragePlace shed = new StoragePlace("Shed");

		shelf7.addComponent(saw);
		shelf7.addComponent(nailGun);
		shed.addComponent(shelf7);
		shed.printTree(0);
	}
*/

}
