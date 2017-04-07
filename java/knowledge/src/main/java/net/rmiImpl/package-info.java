/**
 * Created by zzt on 4/3/17.
 * <h3>My simple trial to devise a rmi implementation</h3>
 * <p>
 * <li>Understand that why there exists two socket class: {@link java.net.ServerSocket}
 * & {@link java.net.Socket} -- the fundamental client-server network paradigm, one have to
 * listen (server side), one have to know other side address (client side)</li>
 * <li>Because the first point, the rmi implementation must be asymmetric, i.e. server side
 * and client side</li>
 * <li>Understand why rmi method argument is all serializable</li>
 * <li>Understand that rmi actually has two directions:
 * <ul>
 * <li>send argument to server, invoke on server, can differ the implementations while
 * client have a single interface</li>
 * <li>send class to client, invoke on client, meaningless -- client have to have the interface and
 * implementation</li>
 * </ul>
 * </li>
 */
package net.rmiImpl;

