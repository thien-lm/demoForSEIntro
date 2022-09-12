package application;
	

import factory.ControllerFactory;
import fxml.MusicViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;



public class Main extends Application {
	
	public boolean isExit;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader= new FXMLLoader(getClass().getResource("/fxml/MusicView.fxml"));
			Parent root = loader.load();
			ControllerFactory controller = new ControllerFactory();
			controller.loading = loader.getController();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(event -> {
				event.consume();
				logout(primaryStage);
				if(isExit)
				{
					controller.loading.task.cancel();
					primaryStage.close();
				}
			});
			

	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void logout(Stage stage)
	{
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout!");
		alert.setContentText("Do you want to save before exiting?");
		
		if (alert.showAndWait().get() == ButtonType.OK){
			System.out.println("You successfully logged out");
			isExit = true;
		} 
	}
}
