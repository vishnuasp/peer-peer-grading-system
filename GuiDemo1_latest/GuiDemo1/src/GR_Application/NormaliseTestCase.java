package GR_Application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
/**
 * This class tests all the scenarios of the Normalization where the user selects the scores for all students 
 * in each category.
 */
class NormaliseTestCase {

	/**
	 * This test is for normalization of scores entered in the Grading window by the user.
	 * 
	 * This tests the Lower boundary(2 users) ,Upper boundary(7 users) and the middle scenario(5 users) for the valid Range of Users between 2 to 7.
	 * and also checks the condition when number of users equal to 3 and 6 so that all scenarios are covered.
	 * 
	 */
	@Test
	void testBoundaryAndPositiveCases() {
		// When the number of students entered are 4 and Each student has a valid score of 0 to 5 for each scoring category
    	//Test if all normalised values of each student are calculated correctly as expected
		
		String normInput[][] = new String[][] { { "4", "4", "4" }, { "2", "2", "2" }, { "2", "2", "2" },
				{ "2", "2", "2" } };
		float normExpected[] = { 0.4f, 0.2f, 0.2f, 0.2f };
		float normActual[] = new float[3];

		try {
			normActual = Normalise.calculate_normalised_scores(normInput);
		} catch (AllZerosException e1) {
		} catch (NotInRangeException e) {
		}

		assertArrayEquals(normExpected, normActual);

		// When the number of students entered are 3 and Each student has a valid score in range 0 to 5 for each scoring category
    	//Test if all normalised values of each student are calculated correctly as expected
		
		String normInput1[][] = new String[][] { { "4", "3", "1" }, { "2", "3", "2" }, { "4", "1", "2" } };
		float normExpected1[] = { 0.36f, 0.32f, 0.32f };
		float normActual1[] = new float[3];

		try {
			normActual1 = Normalise.calculate_normalised_scores(normInput1);
		} catch (AllZerosException e1) {
		} catch (NotInRangeException e) {
		}

		assertArrayEquals(normExpected1, normActual1);

		// When the number of students entered are 2 and Each student has a valid score in range 0 to 5 for each scoring category
    	//Test if all normalised values of each student are calculated correctly as expected
		
		String normInput2[][] = new String[][] { { "2", "1", "3" }, { "0", "1", "0" } };
		float normExpected2[] = { 0.86f, 0.14f };
		float normActual2[] = new float[3];

		try {
			normActual2 = Normalise.calculate_normalised_scores(normInput2);
		} catch (AllZerosException e1) {
		} catch (NotInRangeException e) {
		}

		assertArrayEquals(normExpected2, normActual2);

		// When the number of students entered are 5 and Each student has a valid score in range 0 to 5 for each scoring category
    	//Test if all normalised values of each student are calculated correctly as expected
		
		String normInput3[][] = new String[][] { { "2", "0", "0" }, { "5", "2", "1" }, { "4", "5", "5" },
				{ "5", "1", "4" }, { "4", "3", "3" } };
		float normExpected3[] = { 0.05f, 0.18f, 0.32f, 0.23f, 0.23f };
		float normActual3[] = new float[3];

		try {
			normActual3 = Normalise.calculate_normalised_scores(normInput3);
		} catch (AllZerosException e1) {
		} catch (NotInRangeException e) {
		}
		assertArrayEquals(normExpected3, normActual3);

		// When the number of students entered are 6 and Each student has a valid score in range 0 to 5 for each scoring category
    	//Test if all normalised values of each student are calculated correctly as expected
		
		String normInput4[][] = new String[][] { { "2", "5", "3" }, { "3", "4", "2" }, { "4", "1", "5" },
				{ "4", "0", "2" }, { "3", "3", "3" }, { "5", "5", "5" } };
		float normExpected4[] = { 0.17f, 0.15f, 0.17f, 0.1f, 0.15f, 0.25f };
		float normActual4[] = new float[3];

		try {
			normActual4 = Normalise.calculate_normalised_scores(normInput4);
		} catch (AllZerosException e1) {
		} catch (NotInRangeException e) {
		}
		assertArrayEquals(normExpected4, normActual4);

		// When the number of students entered are 7 and Each student has a valid score of 5 for each scoring category
    	//Test if all normalised values of each student are calculated correctly as expected
		
		String normInput5[][] = new String[][] { { "5", "5", "5" },  { "5", "5", "5" },  { "5", "5", "5" },
			{ "5", "5", "5" },  { "5", "5", "5" },  { "5", "5", "5" }, { "5", "5", "5" } };
		float normExpected5[] = { 0.14f, 0.14f, 0.14f, 0.14f, 0.14f, 0.14f,
				0.14f };
		float normActual5[] = new float[3];

		try {
			normActual5 = Normalise.calculate_normalised_scores(normInput5);
		} catch (AllZerosException e1) {
		} catch (NotInRangeException e) {
		}
		assertArrayEquals(normExpected5, normActual5);
	}
	
	/**
	 * This test is for normalization of scores entered on the Grading window by the user.
	 * 
	 *  This tests the scenario where all the students are scored zero(Extreme case) for all scoring categories.
	 *  This test should be able to catch AllZerosException throw by the calculation_normalised_scores method.
	 */
	@Test
	void testAllZerosException() {
		float normActual[] = new float[3];
		boolean value = false;
		String normInput1[][] = new String[][] { { "0", "0", "0" }, { "0", "0", "0" }, { "0", "0", "0" } };
		try {
			normActual = Normalise.calculate_normalised_scores(normInput1);
		} catch (AllZerosException e) {
			value = true;
		} catch (NotInRangeException e) {
		}
		Assert.assertEquals(true, value);
	}

	/**
	 * This test is for normalization of scores entered on the Grading window by the user.
	 * 
	 * This test method checks for the case when all the values  selected by the user are empty  (null).
	 * This case should be able to catch the NullPointerException thrown by the calculation_normalised_scores method.
	 */
	@Test
	void testNullEntriesException() {
		float normActual[] = new float[3];
		String normInput1[][] = new String[3][3];
		boolean value = false;
		int ns = 3;
		try {
			normActual = Normalise.calculate_normalised_scores(normInput1);
		} catch (NullPointerException e) {
			value = true;
		} catch (AllZerosException e) {
			value = false;
		} catch (NotInRangeException e) {
		}
		Assert.assertEquals(value, true);
	}
	/**
	 * This test is for normalization of scores entered on the Grading window by the user.
	 * 
	 *  This tests the scenario where only one student is scored and other students are scored zero for all scoring categories
	 *  Test if all normalised values of each students are calculated correctly as expected.
	 */
	@Test
	void testGreedyUser() {
		String normInput[][] = new String[][] { { "0", "0", "0" }, { "0", "0", "0" }, { "0", "0", "0" },{ "5", "5", "5" } };
		float normExpected[] = { 0, 0, 0, 1 };
		float normActual[] = new float[3];

		try {
			normActual = Normalise.calculate_normalised_scores(normInput);
		} catch (AllZerosException e1) {
		} catch (NotInRangeException e) {
		}
		assertArrayEquals(normExpected, normActual);

	}

}