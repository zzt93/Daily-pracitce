package annotation;

import java.lang.annotation.Documented;

/**
 * Created by zzt on 9/2/15.
 * <p>
 * Description: the annotation template about file information
 */

@Documented
@interface ClassPreamble {
    String author() default "zzt";
    String date();
    int currentRevision() default 1;
    String lastModified() default "N/A";
    String lastModifiedBy() default "N/A";
    // Note use of array
    String[] reviewers();
}

@ClassPreamble(
        date = "9/2/15",
        lastModified = "9/2/15",
        lastModifiedBy = "zzt",
        reviewers = {"zzt"}
)
class Test {
    public static void main(String[] args) {
        Test.class.getAnnotations()
    }
}