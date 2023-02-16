package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import farm.model.FarmItem;
import farm.model.StoragePlace;


class TestStoragePlace {
	private final FarmItem hammer = new FarmItem("Hammer",100, 150, 1, 2, 3, 5.25, "/img/question-mark.png");
	private final FarmItem fencePosts = new FarmItem("FencePosts",110, 160, 1, 2, 3, 50, "/img/question-mark.png");
	private StoragePlace bay1 = new StoragePlace("Bay 1", 100, 150, 10, 20, 3, 10000, "/img/question-mark.png");
	private StoragePlace bay2 = new StoragePlace("Bay 2", 110, 160, 10, 20, 3, 10000, "/img/question-mark.png");
	private StoragePlace barn = new StoragePlace("Barn", 100, 150, 30, 40, 3, 80000, "/img/barn.png");

/*
	@Test
	void testAddComponent() {
		bay1.addComponent(hammer);
		barn.addComponent(bay1);
	}*/
/*
 * getPrice method has been deprecated.	
	@Test
	void testRemoveComponent() {
		bay2.addComponent(fencePosts);
		barn.addComponent(bay2);
		// Calculate price of total with children components
		assertEquals(90050, barn.getPrice());

		barn.removeComponent(bay2);
		// The price should go down because the fencePost and bay2 are no longer part of the tree (removed).
		assertEquals(80000, barn.getPrice());

	}
*/
	/* getPrice method has been deprecated.
	@Test
	void testGetPrice() {
		FarmItem tractor = new FarmItem("Tractor",110, 160, 2, 3, 5, 34000, "/img/question-mark.png");
		FarmItem tractorPart = new FarmItem("Tractor Part",110, 160, 2, 3, 5, 9001, "/img/question-mark.png");
		StoragePlace bay3 = new StoragePlace("Bay 3", 10, 20, 10, 20, 3, 10000, "/img/question-mark.png");
		StoragePlace bay4 = new StoragePlace("Bay 4", 10, 20, 10, 20, 3, 10000, "/img/question-mark.png");
		StoragePlace barn2 = new StoragePlace("Barn 2", 110, 160, 30, 40, 3, 80000, "/img/barn.png");
		
		bay4.addComponent(tractorPart);
		bay4.addComponent(tractor);
		assertEquals(53001.00, bay4.getPrice());

		barn2.addComponent(bay3);
		barn2.addComponent(bay4);
		assertEquals(143001.00, barn2.getPrice());
	}*/

	@Test
	void testImageUrl() {
		StoragePlace emptyStoragePlace = new StoragePlace();

		assertEquals("/img/question-mark.png", emptyStoragePlace.getImageURL());
		assertEquals("/img/barn.png", barn.getImageURL());
	}
	
	@Test
	void testNotValidImageUrl() {
		
		StoragePlace notAValidUrlStoragePlace = new StoragePlace();
		assertEquals("/img/question-mark.png", notAValidUrlStoragePlace.getImageURL());
		notAValidUrlStoragePlace.setImageURL("InvalidURL");
		assertEquals("InvalidURL", notAValidUrlStoragePlace.getImageURL());

	}
}