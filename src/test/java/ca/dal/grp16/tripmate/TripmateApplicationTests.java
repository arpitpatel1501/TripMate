package ca.dal.grp16.tripmate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
class TripmateApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testForPipeline(){
		assertTrue("This will succeed.", true);
	}

}
