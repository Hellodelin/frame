package delin_OneZero.frame.socket.connection.proxy.proxyImpl;

import delin.util.SerializeUtil;
import delin_OneZero.frame.socket.connection.proxy.Proxy;

public class SerializeProxy implements Proxy{

	@Override
	public byte[] toBytes(Object o) {
		return SerializeUtil.serialize(o);
	}

}
