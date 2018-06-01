package delin.frame.socket.client.connect;

import java.io.IOException;

public interface ClientConnection {
	public byte[] connect(String host,int port,byte[] data) throws IOException;
	public String getHost();
	public void setHost(String host);
	public int getPort();
	public void setPort(int port);
	public void setTimeout(int timeout);
}
