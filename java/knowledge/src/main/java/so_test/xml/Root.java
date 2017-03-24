package so_test.xml;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zzt on 1/25/17.
 * <p>
 * <h3></h3>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Root {

    @XmlElement(name = "Value")
    @XmlJavaTypeAdapter(DateAdapter.class)
    protected Object value;
    @XmlElement(name = "Metadata")
    protected String metadata;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String value) {
        this.metadata = value;
    }

    public static void main(String[] args) {
        final Root root = new Root();
        root.setValue("test");
        root.setMetadata("asdf");
        root.toXml();
    }

    public void toXml() {
        try {
            JAXBContext ctx = JAXBContext.newInstance(Root.class);
            Marshaller marshaller = ctx.createMarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File("t.xsd"));
            marshaller.setSchema(schema);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(this, System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class DateAdapter extends XmlAdapter<String, Object> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String marshal(Object v) throws Exception {
        if (v.getClass() == Date.class) {
            synchronized (dateFormat) {
                return dateFormat.format(v);
            }
        }
        return v.toString();
    }

    @Override
    public java.util.Date unmarshal(String v) throws Exception {
        synchronized (dateFormat) {
            return dateFormat.parse(v);
        }
    }

}
