package delin.socket.client.connect.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import delin.socket.client.connect.ClientConnection;

public class SimpleClientConnection implements ClientConnection {
	private int timeout=0;
	protected int port;
	protected String host;
	public SimpleClientConnection() {}
	
	public  SimpleClientConnection(String host,int port) {
		this.host=host;
		this.port=port;
	}
	
	@Override
	public byte[] connect(String host, int port, byte[] data) throws IOException {
		byte[] response = new byte[0];
		try (Socket socket = new Socket(host, port);) {
			socket.setSoTimeout(timeout);
			OutputStream out = socket.getOutputStream();
			out.write(data);
			out.flush();
			socket.shutdownOutput();
			InputStream in = socket.getInputStream();
			response = in.readAllBytes();
			socket.shutdownInput();
		} catch (IOException e) {
			throw e;
		}
		return response;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public void setHost(String host) {
		this.host=host;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public void setPort(int port) {
		this.port=port;
	}

	@Override
	public void setTimeout(int timeout) {
		this.timeout=timeout;		
	}

}
