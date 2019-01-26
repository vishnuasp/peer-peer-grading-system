package GR_Application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
/**
 * This class tests all the scenarios of the HomeScreen where the user enters the number of Team Members.
 */
class HomeScreenTest {

	/**
	 * This test is for number of team mates entered on the home screen by the user.
	 * Tests for normalization if done in "NormaliseTestCase.java".
	 * 
	 * Checks if the number of team mates entered in the textbox has appropriate
	 * number format and falls within appropriate range.
	 */
	@Test
	void test_number_of_users_input() {
		// When the number of students entered as 4. It is a valid case, as it is an
		// integer and lies between 2 and 7.
		String s = "4";

		boolean isvalid = false;
		try {
			isvalid = HomeScreen.ValidateInput(s);
		} catch (NumberFormatException | NotInRangeException e) {

		}

		Assert.assertTrue(isvalid);

		// When the number of students entered as 10. This value is not in between 2 and
		// 7.
		// So invalid case.
		String s1 = "10";

		try {
			isvalid = HomeScreen.ValidateInput(s1);
		} catch (NumberFormatException | NotInRangeException e) {
			Assert.assertEquals(e.getMessage(), "Number of Team Mates must be between 2 and 7");
		}

		// When the number of students entered as s.
		// This input is a character which is invalid.
		String s2 = "s";

		try {
			isvalid = HomeScreen.ValidateInput(s2);
		} catch (NumberFormatException | NotInRangeException e) {
			Assert.assertEquals(e.getMessage(), "For input string: \"s\"");

		}

		// When the number of students entered as -1.
		// The number of users cannot be -1,so this is an invalid case.

		String s3 = "-1";

		try {
			isvalid = HomeScreen.ValidateInput(s3);
		} catch (NumberFormatException | NotInRangeException e) {
			Assert.assertEquals(e.getMessage(), "Number of Team Mates must be between 2 and 7");

		}

	}

}
