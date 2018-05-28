package delin.net.connect.impl;

import delin.net.connect.Header;

public class ResponseHeader extends Header {
	//状态码
	private int statusCode;
	//状态描述
	private String statusDescripsion;	

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDescripsion() {
		return statusDescripsion;
	}

	public void setStatusDescripsion(String statusDescripsion) {
		this.statusDescripsion = statusDescripsion;
	}
	
	public String toString() {
		StringBuilder header=new StringBuilder();
		header.append(protocol);
		header.append("/");
		header.append(version);
		header.append(" ");
		header.append(statusCode);
		header.append(" ");
		header.append(statusDescripsion);
		header.append("\r\n");
		attributes.entrySet().stream().map(entry->String.format("%s:%s;\r\n", entry.getKey(),entry.getValue())).forEach(header::append);
		//添加CALF	
		header.append("\r\n");
		return header.toString();
	}
}
