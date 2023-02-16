package tests;

import static org.junit.jupiter.api.Assertions.*;

import farm.model.FarmItem;


import org.junit.jupiter.api.Test;

class TestFarmItem {
	private final FarmItem hammer = new FarmItem("Hammer",100, 150, 1, 2, 3, 5.25, "img/question-mark.png");
	// These should really be a livestock class, but I am improvising for the moment.
	private final FarmItem llama = new FarmItem("Llama",500, 300, 1, 2, 3, 100.10, "img/llama.png");
	private final FarmItem sheep = new FarmItem("Sheep",400, 220, 1, 2, 3, 50.50, "img/sheep.png");
	
	@Test
	void testGetLocationX() {
		assertEquals(100, hammer.getLocationX());
	}

	@Test
	void testGetLocationY() {
		assertEquals(150, hammer.getLocationY());
	}

	/* Note: getPrice() method was deprecated */
	@Test
	void testGetPriceOnly() {
		assertEquals(5.25, hammer.getPriceOnly());
	}

	@Test
	void testGetWidth() {
		assertEquals(1, hammer.getWidth());
	}
	
	@Test
	void testGetHeight() {
		assertEquals(2, hammer.getHeight());
	}

	@Test
	void testGetLength() {
		assertEquals(3, hammer.getLength());
	}

	@Test
	void testName() {
		assertEquals("Hammer", hammer.getName());
	}

	@Test
	void testImageURLEquals() {
		assertEquals(llama.getImageURL(), "img/llama.png");
	}

	@Test
	void testImageURLNotEqual() {
		assertNotEquals(sheep.getImageURL(), llama.getImageURL());
	}

	
	
}
