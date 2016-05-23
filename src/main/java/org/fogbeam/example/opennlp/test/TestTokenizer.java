package org.fogbeam.example.opennlp.test;
import static org.junit.Assert.*;

import org.fogbeam.example.opennlp.TokenizerMain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTokenizer {

  @Before
  public void setUp() throws Exception {
    
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testExistingFiles() {
    try {
      TokenizerMain.main(new String[]{"test/simpletext1.txt", "test/simpletext2.txt"});
    } catch (Exception e) {
      fail("This should not throw an Exception");
    }
  }

}
