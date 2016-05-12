package reflect;

/**
 * Created by zzt on 5/11/16.
 * <p>
 * Usage:
 * <p>
 * <pre>
 * I am learning some knowledge about GC from [this oracle post][1].
 * When object is first allocated, it is in an area called Eden area.
 * After some time(or allocation failure, ...), the survivor object
 * is moved to survivor area0, and continue in this way.
 * So, we get **first conclusion**, GC will move the object
 * (According to [this question][2], it also update reference. But any reference?)
 *
 * Now we come to the doc of hashCode()
 *
 * >This is typically implemented by converting the internal address of the object into an integer, but this
 * implementation technique is not required by the Java™ programming language.
 *
 * So, we get second conclusion: `hashCode` is kind of address for `Object`.
 *
 * But the `hashCode` is
 * > must consistently return the same integer, provided no information used in equals comparisons on the object is
 * modified.
 *
 * And the following is a simple program to illustrate it:
 *
 * public class HashCode {
 * public static void main(String[] args) {
 * Object address = new Object();
 * System.out.println(address.hashCode());
 * System.out.println(address);
 *
 * int loop = 1000;
 * for (int i = 0; i < loop; i++) {
 * long[] longs = new long[1024 * 1024];
 * }
 * System.gc();
 * System.out.println(address.hashCode());
 * System.out.println(address);
 * }
 * }
 *
 * sample output:
 *
 * 2101973421
 * java.lang.Object@7d4991ad
 * [GC (Allocation Failure)  43786K->440K(180736K), 0.0008855 secs]
 * [GC (Allocation Failure)  42782K->360K(180736K), 0.0010020 secs]
 * [GC (Allocation Failure)  42172K->400K(180736K), 0.0013510 secs]
 * [GC (Allocation Failure)  42237K->368K(227840K), 0.0016770 secs]
 * [GC (Allocation Failure)  92265K->384K(227840K), 0.0016730 secs]
 * [GC (Allocation Failure)  92303K->400K(314880K), 0.0019814 secs]
 * [GC (Allocation Failure)  184266K->352K(314880K), 0.0011852 secs]
 * [GC (Allocation Failure)  184236K->352K(502784K), 0.0007333 secs]
 * [GC (Allocation Failure)  376326K->352K(503296K), 0.0009109 secs]
 * [GC (Allocation Failure)  376342K->352K(729088K), 0.0007483 secs]
 * [GC (Allocation Failure)  601953K->352K(729088K), 0.0010026 secs]
 * [GC (Allocation Failure)  601964K->352K(1090560K), 0.0006776 secs]
 * [GC (Allocation Failure)  961399K->352K(1090560K), 0.0007589 secs]
 * [GC (Allocation Failure)  961431K->352K(1124864K), 0.0007228 secs]
 * [GC (Allocation Failure)  994955K->352K(1124864K), 0.0007169 secs]
 * [GC (Allocation Failure)  995003K->352K(1076736K), 0.0007191 secs]
 * [GC (Allocation Failure)  944935K->352K(1124352K), 0.0043770 secs]
 * [GC (System.gc())  716520K->352K(1124864K), 0.0010501 secs]
 * [Full GC (System.gc())  352K->330K(1124864K), 0.0078729 secs]
 * 2101973421
 * java.lang.Object@7d4991ad
 *
 *
 * So, my question is how jvm implement it:
 * how jvm/gc move the object, change the address and keep the **same mapping** from address to `hashCode`? Or it
 * actually not change the address by using approach like virtual address and physical address?
 *
 * [1]: http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/index.html
 * [2]: http://stackoverflow.com/questions/15218438/after-gc-the-address-of-the-object-in-memory-be-changed-and-why-the-object-ref
 * </pre>
 * <a href="http://blogs.tedneward.com/post/objecthashcode-implementation/">
 *     Answer: hashCode in object is stored in header</a>
 */
public class HashCode {
    public static void main(String[] args) {
        Object address = new Object();
        System.out.println(address.hashCode());
        System.out.println(address);

        int loop = 1000;
        for (int i = 0; i < loop; i++) {
            long[] longs = new long[1024 * 1024];
        }
        System.gc();
        System.out.println(address.hashCode());
        System.out.println(address);
    }
}
