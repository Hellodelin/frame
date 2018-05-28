package delin_OneZero.frame.socket.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import delin.socket.comm.packet.Packet;
import delin_OneZero.frame.socket.connection.proxy.Proxy;
import delin_OneZero.frame.socket.connection.proxy.proxyImpl.SerializeProxy;

/**
 * 单线程发送消息
 * @author 李德林
 */

public class ClientConnection {
	// private static Charset charset=Charset.forName("UTF-8");
	private static ExecutorService exec = Executors.newSingleThreadExecutor();
	private static int port;
	private static String host;
	static {
		host = "127.0.0.1";
		port = 8080;
	}

	@SuppressWarnings("finally")
	public static byte[] connect(String host, int port, byte[] data) {
		byte[] response = new byte[0];
		try (Socket socket = new Socket(host, port);) {
			socket.setSoTimeout(5000);

			OutputStream out = socket.getOutputStream();
			out.write(data);
			out.flush();
			socket.shutdownOutput();
			InputStream in = socket.getInputStream();
			response = in.readAllBytes();
			socket.shutdownInput();
		} catch (IOException e) {
			e.printStackTrace();
			response = new byte[0];
		} finally {
			return response;
		}
	}

	public static byte[] connect(Packet packet) {
		Proxy proxy = new SerializeProxy();
		return connect(packet, proxy);
	}

	public static byte[] connect(Packet packet, Proxy proxy) {
		byte[] data = proxy.toBytes(packet);
		return connect(host, port, data);
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		ClientConnection.port = port;
	}

	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		ClientConnection.host = host;
	}

	public static void setHost() {
		// Connection.host = new ;
	}
}
