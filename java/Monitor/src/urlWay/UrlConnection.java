package urlWay;

import connection.WebConnection;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zzt on 12/26/15.
 * <p>
 * Usage: have to test using servlet and http server
 */
public class UrlConnection implements WebConnection {

    private final PrintWriter printWriter;
    private final BufferedReader bufferedReader;

    public UrlConnection(String url) throws IOException {
        URL local = new URL(url);
        URLConnection urlConnection = local.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        printWriter = new PrintWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "utf8"));
        bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf8"));
    }

    @Override
    public PrintWriter getOut() {
        return printWriter;
    }

    @Override
    public BufferedReader getIn() {
        return bufferedReader;
    }

    @Override
    public void close() throws IOException {
        getIn().close();
        getOut().close();
    }
}
