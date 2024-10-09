package other.pratice.programs;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crem.basetest.BaseClass;

@Listeners (com.comcast.crm.listenergeneric.ListeImpClass.class)
public class Sample extends BaseClass{
	
	@Test(retryAnalyzer = com.comcast.crm.listenergeneric.RetryAnalyzerImpClass.class)
	public void test() {
		System.out.println("test1");
		
	}
	@Test
	public void test2() {
		System.out.println("test2");
		Assert.fail();
	}
}

