package delin.frame.socket.client.connect.impl;

import java.io.IOException;

import delin.frame.socket.comm.packet.Packet;
import delin.frame.socket.comm.proxy.Proxy;

public class PacketClientConnection extends SimpleClientConnection {
	public byte[] connect(Packet packet, Proxy proxy) throws IOException {
		byte[] data = proxy.toBytes(packet);
		return connect(host, port, data);
	}
}
