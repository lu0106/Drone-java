package farm;

import farm.model.Component;
import farm.model.FarmItem;
import farm.model.StoragePlace;

public class MarketPriceVisitor implements Visitor {
	public MarketPriceVisitor() {
		
	}
	
	/* Added Market Value variable in Component class, button to set Market Value,
	   and swapped to using Market Value for per the request of Mario made during Sprint #1.  */
	public double visit(Component comp){
		double sum = 0.0;

		// this is a for each loop
		if(comp instanceof StoragePlace) {
			for (Component element : comp.getChildren()) {
				if(element instanceof FarmItem) {
					sum += element.getMarketValue();
				} else if (element instanceof StoragePlace) {
					sum += visit(element);
				}
			}
		} else {
			sum += comp.getMarketValue();
		}
		return sum;
	}
}
