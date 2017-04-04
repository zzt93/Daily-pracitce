/**
 * Created by zzt on 4/4/17.
 * <p>
 * <h3>Implement the serialization</h3>
 * <p>
 * <li>Serialize class and object separately: serialize class first</li>
 * <li>Serialization is to travel the object graph, class graph</li>
 * <li>The class info in the serialization output is redundant for Java code,
 * i.e. the de-serialization part have to own that class</li>
 * <li>The json format can't fully describe cyclic dependency relationship,
 * because json not have reference</li>
 *
 */
package reflect.serializeImpl;