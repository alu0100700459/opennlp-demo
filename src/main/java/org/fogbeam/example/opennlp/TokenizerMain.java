
package org.fogbeam.example.opennlp;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitOption;
import java.util.ArrayList;
import java.util.Arrays;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


public class TokenizerMain
{
	public static void main( String[] args ) throws Exception
	{
	  if (args.length == 0)
	  {
	    System.out.println("You have to specify a set of file names containing plain text.");
	    return;
	  }

		// the model we trained
		InputStream modelIn = new FileInputStream( "models/en-token.model" );
		
		try
		{
			TokenizerModel model = new TokenizerModel(modelIn);
			Tokenizer tokenizer = new TokenizerME(model);
			
			for (String fileName: args)
			{
			  ArrayList<String> fileTokens = new ArrayList<>();
			  try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
			  {
  			  String line;
  			  while ((line = reader.readLine()) != null)
  			  {
  			    String[] tokens = tokenizer.tokenize(line);
  			    fileTokens.addAll(Arrays.asList(tokens));
  			  }
			  }
			  finally
			  {
			    System.out.println("===== FILE: \"" + fileName + "\" =====");
			    for (String token: fileTokens)
            System.out.println(token);
			  }
			}
			
			/* note what happens with the "three depending on which model you use
			String[] tokens = tokenizer.tokenize
					(  "A ranger journeying with Oglethorpe, founder of the Georgia Colony, " 
							+ " mentions \"three Mounts raised by the Indians over three of their Great Kings" 
							+ " who were killed in the Wars.\"" );
			
			for( String token : tokens )
			{
				System.out.println( token );
			}
			*/
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
		}
		System.out.println( "\n-----\ndone" );
	}
}
