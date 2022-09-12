package factory;

import fxml.IntroViewController;
import fxml.MusicViewController;

public class ControllerFactory {

	public IntroViewController intro;
	public MusicViewController loading;
	
	public ModelFactory modelFactory;
	
	public ControllerFactory()
	{
		this.intro = new IntroViewController();
		this.loading = new MusicViewController();
		this.modelFactory = new ModelFactory();
	}
}
