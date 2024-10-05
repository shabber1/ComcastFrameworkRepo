package practicetestng;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
public class InvoiceTest {
	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplimentationClass.class)
	public void activatesim() {
		System.out.println("execute createInvoiceTest");
		Assert.assertEquals("", "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}

}
