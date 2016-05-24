package org.fogbeam.example.opennlp;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import org.fogbeam.example.opennlp.TokenizerMain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTokenizer {
  
  private TokenizerMain tokenizer;

  @Before
  public void setUp() throws Exception {
    tokenizer = new TokenizerMain();
  }

  @Test
  public void testExistingFiles() {
    try {
      TokenizerMain.main(new String[]{"test/simpletext1.txt", "test/simpletext2.txt"});
    } catch (Exception e) {
      fail("This should not throw an Exception");
    }
  }
  
  @Test
  public void testNonExistingFiles() {
    try {
      TokenizerMain.main(new String[]{"thisFileDoesNotExist"});
      fail("This should have thrown an Exception");
    } catch (Exception e) {}
  }
  
  @Test
  public void testZeroArgs() {
    try {
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      PrintStream stream = new PrintStream(outStream);
      System.setOut(stream);
      
      TokenizerMain.main(new String[0]);
      
      assertEquals("You have to specify a set of file names containing plain text.",
                   outStream.toString().trim());
    } catch (Exception e) {
      e.printStackTrace();
      fail("This should not throw an Exception");
    }
  }
  
  @Test
  public void testStringTokenize() {
    String str = "This is a simple string.";
    String[] tokens = new String[]{"This", "is", "a", "simple", "string", "."};
    String[] tokens2 = tokenizer.tokenizeString(str);
    assertArrayEquals(tokens, tokens2);
  }
  
  @Test
  public void testFileTokenize() {
    String[] tokens = new String[]{"This", "is", "the", "content", "of", "a", "simple", "test", "."};
    try {
      List<String> tokens2 = tokenizer.tokenizeFile("test/simpletext1.txt");
      String[] tokens2Str = tokens2.toArray(new String[tokens2.size()]);
      assertArrayEquals(tokens, tokens2Str);
    } catch (IOException e) {
      e.printStackTrace();
      fail("This should not throw an exception");
    }
  }

}
