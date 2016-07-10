package reflect.spring;

/**
 * Created by zzt on 7/8/16.
 * <p>
 * <h3></h3>
 */
public class SomeBean {


    private AnotherBean bean;

    public void invoke() {
        System.out.println(bean.toString());
    }
}
