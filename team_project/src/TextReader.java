package team_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TextReader {
	
	private static Scanner scan;
	private static TranscriptHandler tScriptList = new TranscriptHandler();

	protected static void openFile(File file) throws FileNotFoundException, IOException 
	{
		scan = new Scanner(file);
	}

	protected static void readTextFile() {
		
		Transcript t = new Transcript();
		String line, trimLine;
		String[] fields;
		
		while(scan.hasNextLine())
		{
			try
			{
				line=scan.nextLine();
				trimLine = line.trim();
				fields = trimLine.split("\\s{2,20}");
				Course c = new Course(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);
				t.add(c);
			}
			catch(NullPointerException e)
			{
				break;
			}
					
		}
		tScriptList.add(t);
	}
	
	//Dont think this is needed anymore
	protected static void closeFile() {
		/*
		try{
			
			fis.close();
			
		}catch(Exception e){
			
			AlertBox.displayAlert("Error", "Could not close transcript file.");
		}*/
	}

	private static void processTranscripts()
	{
		int i = 1;
		boolean done = false;
		
		while(!done)
		{
			try
			{
				/*
				 *	The actual file name (2015EE_ ... .txt) will be set by the user prior to this method being executed.
				 */
				openFile(new File(ConfigGUI.getTranscriptFolderPath() + "2015EE_" + i + ".txt"));
				
				readTextFile();
				
				closeFile();
			}
			catch (FileNotFoundException fnf)
			{
				AlertBox.displayAlert("Done", "All transcripts in the folder have been processed.");
				done = true;
				
			}
			catch (IOException e) 
			{
				AlertBox.displayAlert("Error", "Could not open file.");
			}
		}	
		
	}
}