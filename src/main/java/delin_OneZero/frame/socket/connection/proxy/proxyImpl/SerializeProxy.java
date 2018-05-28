package delin_OneZero.frame.socket.connection.proxy.proxyImpl;

import delin_OneZero.frame.socket.connection.proxy.Proxy;
import delin_OneZero.frame.util.SerializeUtil;

public class SerializeProxy implements Proxy{

	@Override
	public byte[] toBytes(Object o) {
		return SerializeUtil.serialize(o);
	}

}
