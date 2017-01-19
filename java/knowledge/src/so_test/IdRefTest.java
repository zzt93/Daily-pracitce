package so_test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.annotation.*;
import javax.xml.transform.Result;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by zzt on 1/19/17.
 * <p>
 * <h3></h3>
 */
public class IdRefTest {

    @XmlRootElement
    public static class Outer {
        private List<Class1> list;

        @XmlElement(name = "c1")
        public List<Class1> getList() {
            return list;
        }

        public void setList(List<Class1> list) {
            this.list = list;
        }
    }

    public static class Class1 {

        @XmlID
        @XmlAttribute
        private String id;

        private Class2 class2;

        public Class1() {
            this.id = UUID.randomUUID().toString();
        }

        public Class1(Class2 refClass) {
            this();
            class2 = refClass;
        }

        public Class2 getClass2() {
            return class2;
        }

        public void setClass2(Class2 class2) {
            this.class2 = class2;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }

    }

    public static class Class2 {

        private Class1 class1;

        public Class2() {
        }

        public Class1 getClass1() {
            return class1;
        }

        @XmlIDREF
        @XmlAttribute(name = "ref")
        public void setClass1(Class1 class1) {
            this.class1 = class1;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }
    }

    public List<Class1> cyclicTest() {
        //I create an instance of each class, having them a cyclic reference to the other instance
        Class2 class2 = new Class2();
        Class1 class1 = new Class1(class2);
        final Class1 class11 = new Class1();
        class2.setClass1(class11);
        return Arrays.asList(class11, class1);
    }

    public static void main(String[] args) throws JAXBException, IOException {

        JAXBContext ctx = JAXBContext.newInstance(Outer.class);
        SchemaOutputResolver sor = new SchemaOutputResolver() {
            @Override
            public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
                return null;
            }
        };
        ctx.generateSchema(sor);


        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        List<Class1> class1s = new IdRefTest().cyclicTest();
        final Outer outer = new Outer();
        outer.setList(class1s);
        m.marshal(outer, System.out);
    }

}
