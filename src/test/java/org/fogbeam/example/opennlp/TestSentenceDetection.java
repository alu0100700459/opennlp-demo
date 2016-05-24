package org.fogbeam.example.opennlp;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.imageio.stream.ImageOutputStreamImpl;

import org.fogbeam.example.opennlp.SentenceDetectionMain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSentenceDetection {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testStreamToString() {
    String content1 = null, content2 = null;
    try (InputStream in = createStream())
    {
      content1 = SentenceDetectionMain.convertStreamToString(in);
    } catch (Exception e) {
      fail("This should not throw an exception");
    }

    try (InputStream in = createStream())
    {
      StringBuilder sb = new StringBuilder();
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      String read;
      while((read = reader.readLine()) != null)
        sb.append(read);

      reader.close();
      content2 = sb.toString();
    } catch (Exception e) {
      fail("this should not throw an exception");
    }

    assertNotNull(content1);
    assertNotNull(content2);
    assertEquals(content1, content2);
  }

  private InputStream createStream() throws FileNotFoundException {
    return new FileInputStream("test/simpletext1.txt");
  }

}
