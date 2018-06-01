package delin.frame.net.connect.impl;

import delin.frame.net.connect.Header;

public class RequestHeader extends Header {
	private String uri;
	private String method;
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
}
