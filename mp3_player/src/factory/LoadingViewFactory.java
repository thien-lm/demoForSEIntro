package factory;

public class LoadingViewFactory {
	
	String name;
	public static String load(String name)
	{
		return "/fxml/" + name+ ".fxml";
	}

}
