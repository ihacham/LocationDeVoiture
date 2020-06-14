package ma.ac.ensaagadir.utils;

public class ApplicationSessionSingleton {

	private static ApplicationSession applicationSession;

	private ApplicationSessionSingleton() {
		
	}
	
	public static ApplicationSession getInstance() {
		if(applicationSession != null) {
			System.out.println("oldOne");
			return applicationSession;
		} else {
			applicationSession = new ApplicationSession();
			System.out.println("NewOne");
			return applicationSession;
		}
	}
	
}
