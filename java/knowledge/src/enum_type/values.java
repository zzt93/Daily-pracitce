package enum_type;

import thread.old.OSExecute;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by zzt on 2/10/15.
 */
public class values {
    public static Set<String> analyze(Class<?> enumClass) {
        System.out.println("----- Analyzing " + enumClass + " -----");

        System.out.println("Interfaces:");
        for(Type t : enumClass.getGenericInterfaces())
            System.out.println(t);
        System.out.println("Base: " + enumClass.getSuperclass());
        System.out.println("Methods: ");
        Set<String> methods = new TreeSet<String>();
        for(Method m : enumClass.getMethods())
            methods.add(m.getName());
        System.out.println(methods);
        return methods;
    }

    public static void main(String[] args) {
        Set<String> languageMethods = analyze(Language.class);
        Set<String> enumMethods = analyze(Enum.class);
        System.out.println("Language.containsAll(Enum)? " +
                languageMethods.containsAll(enumMethods));
        System.out.println("Language.removeAll(Enum): ");
        languageMethods.removeAll(enumMethods);
        System.out.println(languageMethods);
// Decompile the code for the enum:
//        try {
//            Runtime.getRuntime().exec("javap Language.class > ll");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        OSExecute.command("javap Language.class");
    }
}
