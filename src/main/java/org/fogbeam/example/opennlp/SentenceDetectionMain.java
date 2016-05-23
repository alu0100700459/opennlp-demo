
package org.fogbeam.example.opennlp;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

/**
 * Sentence detection demo.
 */
public class SentenceDetectionMain
{
  /**
   * Splits the contents of the file "demo_data/en-sent1.demo" in
   * sentences and prints them in the standard output.
   * @param args Unused.
   * @throws Exception If a file is not found.
   */
	public static void main( String[] args ) throws Exception
	{
		InputStream modelIn = new FileInputStream( "models/en-sent.model" );
		InputStream demoDataIn = new FileInputStream( "demo_data/en-sent1.demo" );
		
		try
		{
			SentenceModel model = new SentenceModel( modelIn );
			SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
			
			String demoData = convertStreamToString( demoDataIn );
			
			String sentences[] = sentenceDetector.sentDetect( demoData );
			
			for( String sentence : sentences )
			{
				System.out.println( sentence + "\n" );
			}
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if( modelIn != null )
			{
				try
				{
					modelIn.close();
				}
				catch( IOException e )
				{
				}
			}

			if( demoDataIn != null )
			{
				try
				{
					demoDataIn.close();
				}
				catch( IOException e )
				{
				}
			}
			
		}

		System.out.println( "done" );
		
	}
	
	/**
	 * Reads the {@link InputStream} and stores its contents into a String.
	 * @param is Input stream.
	 * @return The contents of the stream as String.
	 */
	public static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
	
}
