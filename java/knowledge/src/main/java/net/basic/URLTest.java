package net.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author zzt
 */
public class URLTest {


  public static void main(String[] args) throws URISyntaxException, IOException {
    parseURL();
    readURL();
  }

  private static void parseURL() throws URISyntaxException, MalformedURLException {
    URI uri = new URI("http", "192.168.1.222", "/test space&?/path", "");
    // url is immutable
    URL url = uri.toURL();
    System.out.println(url);
  }

  private static void readURL() throws IOException {
    URL oracle = new URL("http://www.oracle.com/");
    BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

    String inputLine;
    while ((inputLine = in.readLine()) != null)
      System.out.println(inputLine);
    in.close();
  }
}
