package delin_OneZero.frame.socket.connection.thread;

import java.net.Socket;

import delin.socket.comm.packet.Packet;
import delin.socket.server.handler.Handler;
import delin.util.SerializeUtil;


public class HandlerThread extends Handler {
	public HandlerThread(Socket socket) {
		super(socket);
	}
	
	private Packet resolvePacket(byte[] bytes) {
		Packet packet=(Packet)SerializeUtil.resolve(bytes);
		return packet;
	}

	@Override
	public void service(byte[] bytes) {
		Packet packet=resolvePacket(bytes);	
	}

	@Override
	public byte[] resolveRequest(Socket socket) {
		// TODO Auto-generated method stub
		return null;
	}
}
