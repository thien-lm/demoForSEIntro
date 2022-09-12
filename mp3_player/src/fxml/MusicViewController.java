package fxml;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.MusicModel;

public class MusicViewController implements Initializable{
	
	@FXML
	private Label label;
	@FXML
	private Button button1Play, button2Pause, button3Next, button4Replay, button5Prev;
	@FXML 
	private ProgressBar progressBar;
	@FXML
	private ComboBox<String> speedBox;
	@FXML
	private Slider volumeSlider;
	
	private Media media;
	public MediaPlayer mediaPlayer;
	
	private int songNumber;
	private int[] speeds = {25, 50, 75, 100, 125, 150, 175, 200, 250, 300, 1000};
	
	private Timer timer;
	public boolean isRunning = true;
	public TimerTask task ;
	
	private MusicModel musicModel;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		musicModel = new MusicModel();
		
		media = new Media(musicModel.getSongs().get(songNumber).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		label.setText(musicModel.getSongs().get(songNumber).toString());

		volumeSlider.setValue(0);
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
			}
		});
		
		
        for(int i = 0; i < speeds.length; i++) {
			speedBox.getItems().add(Integer.toString(speeds[i])+"%");
		}
		
        this.beginTimer();
             
}
	
	private void setListPlay()
	{
		
		 mediaPlayer.setOnEndOfMedia( () -> 
			{
				if(this.songNumber == musicModel.getSongs().size() - 1) this.songNumber = 0;
		        else this.songNumber++;
				
				media = new Media(musicModel.getSongs().get(songNumber).toURI().toString());
				mediaPlayer = new MediaPlayer(media);
				mediaPlayer.setVolume(volumeSlider.getValue());
				label.setText(musicModel.getSongs().get(songNumber).toString());
				playMedia();
				
			});
	}
	
	
	public void playMedia()
	{
		changeSpeed(null);
		mediaPlayer.setVolume(volumeSlider.getValue());
		System.out.println(volumeSlider.getValue());
		mediaPlayer.play();
		this.setListPlay();
		
	}
	
	public void pauseMedia()
	{
		mediaPlayer.pause();
	}
	
	public void resetMedia()
	{
		if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) 
		mediaPlayer.seek(Duration.seconds(0.0));
	}
	
	public void previousmedia()
	{
		
		mediaPlayer.stop();
		
		
		if(this.songNumber > 0 ) this.songNumber--;
		else this.songNumber = musicModel.getSongs().size()-1;
		
		media = new Media(musicModel.getSongs().get(songNumber).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(volumeSlider.getValue());
		label.setText(musicModel.getSongs().get(songNumber).toString());
		mediaPlayer.play();
		
		this.setListPlay();
		
	}
	
	public void nextMedia()
	{
		
		mediaPlayer.stop();
		
		if(this.songNumber < musicModel.getSongs().size() - 1 ) this.songNumber++;
		else this.songNumber = 0;
		
		media = new Media(musicModel.getSongs().get(songNumber).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		label.setText(musicModel.getSongs().get(songNumber).toString());
		mediaPlayer.setVolume(volumeSlider.getValue());
		mediaPlayer.play();
		this.setListPlay();
	
	}
	
	public void changeSpeed(ActionEvent event)
	{        
		
       if(speedBox.getValue() == null) {
			
			mediaPlayer.setRate(1);
		}
		else {
			mediaPlayer.setRate(Integer.parseInt(speedBox.getValue().substring(0, speedBox.getValue().length() - 1)) * 0.01);
		}
	}
	
	 
	public void beginTimer()
	 {
			timer = new Timer();

			 task = new TimerTask() {

				public void run() {

					isRunning = true;
					double current = mediaPlayer.getCurrentTime().toSeconds();
					double end = media.getDuration().toSeconds();
					progressBar.setProgress(current / end);

					if (current / end == 1) {

						cancelTimer();
					}
				}
				
			};
			

			timer.scheduleAtFixedRate(task, 0, 1000);
		}

	public void cancelTimer() {

			isRunning = false;
			timer.cancel();
		}
	
}



