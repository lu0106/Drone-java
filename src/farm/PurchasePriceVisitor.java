package farm;

import farm.model.Component;
import farm.model.StoragePlace;

public class PurchasePriceVisitor implements Visitor {
	public PurchasePriceVisitor (){
		
	}

	public double visit(Component comp) {
		double sum = comp.getPriceOnly();
		
		if(comp instanceof StoragePlace) {
			// compute the price of all the children
			for (Component element : comp.getChildren()) {
				if(element instanceof StoragePlace) {
					// recursively call for this element
					sum += visit(element);
				} else {
					sum += element.getPriceOnly();
				}
			}
		}
		return sum;
	}
}
