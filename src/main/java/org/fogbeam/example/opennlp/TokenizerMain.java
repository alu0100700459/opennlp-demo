package org.fogbeam.example.opennlp;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;

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
	public static void main( String[] args ) throws Exception {
	  if (args.length == 0) {
	    System.out.println("You have to specify a set of file names containing plain text.");
	    return;
	  }
	  
	  TokenizerMain tokenizer = new TokenizerMain();
	  for (String fileName: args) {
	    System.out.println("===== FILE: \"" + fileName + "\" =====");
	    List<String> tokens = tokenizer.tokenizeFile(fileName);
	    for (String token: tokens) {
	      System.out.println(token);
	    }
	  }
	  
		System.out.println( "\n-----\ndone" );
	}
	
  private Tokenizer tokenizer;
  
  /**
   * Creates a new instance of the tokenizer.
   * @throws InvalidFormatException If the model file used for training has an
   * invalid format.
   * @throws IOException If there's a problem reading the model file.
   */
  public TokenizerMain() throws InvalidFormatException, IOException {
    InputStream modelIn = null;
    
    try {
      modelIn = new FileInputStream("models/en-token.model");
      TokenizerModel model = new TokenizerModel(modelIn);
      tokenizer = new TokenizerME(model);
    }
    finally {
      if (modelIn != null)
        modelIn.close();
    }
  }
  
  /**
   * Split a string by tokens.
   * @param str The string to be split.
   * @return An array of strings that represent the list of tokens.
   */
  public String[] tokenizeString(String str) {
    return tokenizer.tokenize(str);
  }
  
  /**
   * Tokenizes the whole contents of a file.
   * @param fileName The name of the file.
   * @return The list of tokens in the file.
   * @throws FileNotFoundException If the file specified doesn't exist.
   * @throws IOException If there was a problem reading the file.
   */
  public List<String> tokenizeFile(String fileName) throws FileNotFoundException, IOException {
    ArrayList<String> fileTokens = new ArrayList<>();
    
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null)
        fileTokens.addAll(Arrays.asList(tokenizeString(line)));
    }
    
    return fileTokens;
  }
}
