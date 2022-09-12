package model;

import java.io.File;
import java.util.ArrayList;

public class MusicModel {

	private File directory;
	private File[] files;

	private ArrayList<File> songs;
	private int songNumber;
	
	public MusicModel ()
	{
		songs = new ArrayList<File>();
		directory = new File("Songs");
		files = directory.listFiles();
		
		if (files != null) {

			for (File file : files) {

				songs.add(file);
			}
		}
	}

	public File getDirectory() {
		return directory;
	}

	public void setDirectory(File directory) {
		this.directory = directory;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public ArrayList<File> getSongs() {
		return songs;
	}

	public void setSongs(ArrayList<File> songs) {
		this.songs = songs;
	}

	public int getSongNumber() {
		return songNumber;
	}

	public void setSongNumber(int songNumber) {
		this.songNumber = songNumber;
	}
	
	
}
