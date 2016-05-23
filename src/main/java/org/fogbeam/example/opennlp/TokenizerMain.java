package org.fogbeam.example.opennlp;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * Tokenizer demo.
 */
public class TokenizerMain
{
  /**
   * Reads a set of input files from as arguments and tokenizes its contents.
   * Each token is written to standard output.
   * @param args The set of file names.
   * @throws Exception If some files are not found.
   */
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
			  catch (FileNotFoundException e)
			  {
			    throw e;
			  }
			  finally
			  {
			    System.out.println("===== FILE: \"" + fileName + "\" =====");
			    for (String token: fileTokens)
            System.out.println(token);
			  }
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
		}
		System.out.println( "\n-----\ndone" );
	}
}
