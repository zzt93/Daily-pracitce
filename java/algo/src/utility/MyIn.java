package utility;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by zzt on 3/21/15.
 */
public class MyIn {
    private Scanner scanner;

    /**
     * read from stdin
     */
    MyIn() {
        scanner = new Scanner(new BufferedInputStream(System.in));
    }

    /**
     * read from inputStream
     * @param inputStream
     */
    MyIn(InputStream inputStream) {
        scanner = new Scanner(new BufferedInputStream(inputStream));
    }

    /**
     * read from file
     * @param s
     * @throws FileNotFoundException
     */
    MyIn(String s) throws FileNotFoundException {
        scanner = new Scanner(new BufferedInputStream(new FileInputStream(s)));
    }

    public boolean hasNext() {
        return scanner.hasNext();
    }

    /**
     * Sets this scanner's delimiting pattern to the specified pattern.
     *
     * @param pattern A delimiting pattern
     * @return this scanner
     */
    public Scanner useDelimiter(Pattern pattern) {
        return scanner.useDelimiter(pattern);
    }

    /**
     * Sets this scanner's delimiting pattern to a pattern constructed from
     * the specified <code>String</code>.
     *
     * @param pattern A string specifying a delimiting pattern
     * @return this scanner
     */
    public Scanner useDelimiter(String pattern) {
        return scanner.useDelimiter(pattern);
    }

    public String next() {
        return scanner.next();
    }

    public int nextInt() {
        return scanner.nextInt();
    }

    public double nextDouble() {
        return scanner.nextDouble();
    }

    public long nextLong() {
        return scanner.nextLong();
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    /**
     * Resets this scanner.
     *
     * <p> Resetting a scanner discards all of its explicit state
     * information which may have been changed by invocations of {@link
     * #useDelimiter}, {@link java.util.Scanner#useLocale}, or {@link java.util.Scanner#useRadix}.
     *
     * <p> An invocation of this method of the form
     * <tt>scanner.reset()</tt> behaves in exactly the same way as the
     * invocation
     *
     * <blockquote><pre>{@code
     *   scanner.useDelimiter("\\p{javaWhitespace}+")
     *          .useLocale(Locale.getDefault(Locale.Category.FORMAT))
     *          .useRadix(10);
     * }</pre></blockquote>
     *
     * @return this scanner
     */
    public Scanner reset() {
       return scanner.reset();
    }
}
