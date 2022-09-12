package fxml;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class IntroViewController {

	@FXML
    private Label myLabel;
	@FXML
	private Button continueButton;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	public void connectToSence(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MusicView.fxml"));	
		root = loader.load();
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
