package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import farm.model.Component;
import farm.model.FarmItem;
import farm.model.StoragePlace;

class TestComponent {
	@Test
	void testComponentInArray() {
		ArrayList<Component> comps = new ArrayList<Component>();
		FarmItem hammer = new FarmItem("hammer");
		StoragePlace barn = new StoragePlace("barn");
		comps.add(barn);
		comps.add(hammer);
	}

}
