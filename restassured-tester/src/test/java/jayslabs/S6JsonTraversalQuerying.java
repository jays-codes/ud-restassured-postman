package jayslabs;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class S6JsonTraversalQuerying {

	@Test
	public void testJson() {
		JsonPath jspath = new JsonPath(Payload.getMockBody1());
		
		int courseCnt = jspath.getInt("courses.size()");
		
		System.out.println("Course count: " + courseCnt);
		
		System.out.println("purchaseAmnt: " + jspath.get("dashboard.purchaseAmount"));

		System.out.println("1st course: " + jspath.get("courses[0].title"));
		
		for (int i=0;i<courseCnt;i++) {
			System.out.println(jspath.get("courses["+ i +"].title") + ":" +
					jspath.get("courses[" + i + "].price"));
		}
	}
	
	@Test
	public void testSumOfCourses() {
		JsonPath jspath = new JsonPath(Payload.getMockBody1());
		
		int courseCnt = jspath.getInt("courses.size()");
		
		int exptotal = jspath.getInt("dashboard.purchaseAmount");
		int actual = 0;
		
		for (int i=0;i<courseCnt;i++) {
			actual = actual + (jspath.getInt("courses[" + i + "].price") * 
					jspath.getInt("courses[" + i + "].copies"));
		}

		System.out.println("expected: " + exptotal);
		System.out.println("actual: " + actual);

		Assert.assertEquals(actual, exptotal);
	}
}
