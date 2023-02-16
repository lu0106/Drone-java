package farm.view;

import farm.Visitor;
import farm.model.Component;

/*
 * This is calls
 */
public class PrintingVisitor implements Visitor {
	public PrintingVisitor() {
		
	}
	
	public double visit(Component comp){
		comp.printTree(0);
		return 0.0; // not used
	} 
}
