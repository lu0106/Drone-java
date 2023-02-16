package farm;

import farm.model.Component;

public interface Visitor {
	public abstract double visit(Component comp);

}
