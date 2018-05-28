package delin.socket.server.handler;

import java.net.Socket;

public abstract class Handler implements Runnable {
	protected Socket socket;

	public Handler(Socket socket) {
		this.socket=socket;
	}
	@Override
	public final void run() {
		service(resolveRequest(socket));
	}
	
	protected abstract void service(byte[] bytes);
	
	protected abstract byte[] resolveRequest(Socket socket);

}
