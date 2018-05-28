package delin.net.connect;

import java.util.HashMap;

public class Header {
	//协议类型
	protected String protocol;
	//协议版本
	protected String version;
	//参数
	protected HashMap<String,String> attributes=new HashMap<>();

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public void addAttribute(String name,String value) {
		attributes.put(name, value);
	}
	
	public String getAttribute(String name) {
		return attributes.get(name);
	}

}
