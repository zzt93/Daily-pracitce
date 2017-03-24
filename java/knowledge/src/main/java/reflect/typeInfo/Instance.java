package reflect.typeInfo;

/**
 * Created by zzt on 7/14/15.
 * <p>
 * Description:
 * learning instanceof
 */
public class Instance {
    //    public class A { Inner class can't have static definition
    public static void main(String args[]) {
        B obj = new B();
//        System.out.println(obj instanceof Instance);      //Gives compiler error
        System.out.println(obj instanceof C);      //Gives false as output
    }
    //    }

}

interface C {
}

class B {
}
