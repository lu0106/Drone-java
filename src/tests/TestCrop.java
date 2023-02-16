package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import farm.model.Crop;


// Class is a JUnit 5 test class, which is testing the farm.model.Crop class.
class TestCrop {
	@Test
	void testCrop1() {
		Crop crop = new Crop();
		
		LocalDate sowedDate = LocalDate.parse("2021-03-14");
		crop.setDateSowed(sowedDate);
		assertEquals(crop.getDateSowed(), LocalDate.of(2021, 03, 14));
	}
}
