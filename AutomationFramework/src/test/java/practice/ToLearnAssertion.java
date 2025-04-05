package practice;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ToLearnAssertion {

	@Test
	public void sample() {
		
		System.out.println("--Step 1--");
		System.out.println("--Step 2--");
		
		//Validation using Hard Assert
		//Assert.assertEquals(false, true);
		
		//Validation with soft Assert
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(false, true); // failed
		
		System.out.println("--Step 3--");
		System.out.println("--Step 4--");
		
		sa.assertAll();
	}
}
