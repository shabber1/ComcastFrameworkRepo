package practicedatadriven;

import org.testng.annotations.Test;

public class readruntimeparametertest {
@Test
public void runtimeMavenParametertest() {
	String url=System.getProperty("url");
	String browser=System.getProperty("browser");
	String username=System.getProperty("username");
	String password=System.getProperty("password");
	System.out.println("Env Data==>URL====>"+url);
	System.out.println("Env Data==>URL====>"+browser);
	System.out.println("Env Data==>URL====>"+username);
	System.out.println("Env Data==>URL====>"+password);
}
}
