package net.domain;


public class NetFlow {

	private int id;
	private String srcIP;
	private String dstIP;
	private int version;
	private int hlen;
	private int tos;
	private int len;
	private int ttl;
	private int proto;
	private String timestamp;
	
	
	public NetFlow() {
		super();
	}
	
	public NetFlow(int id, String srcIP, String dstIP, int version, int hlen,
			int tos, int len, int ttl, int proto, String timestamp) {
		super();
		this.id = id;
		this.srcIP = srcIP;
		this.dstIP = dstIP;
		this.version = version;
		this.hlen = hlen;
		this.tos = tos;
		this.len = len;
		this.ttl = ttl;
		this.proto = proto;
		this.timestamp = timestamp;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getTos() {
		return tos;
	}
	public void setTos(int tos) {
		this.tos = tos;
	}
	public int getTtl() {
		return ttl;
	}
	public void setTtl(int ttl) {
		this.ttl = ttl;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSrcIP() {
		return srcIP;
	}
	public void setSrcIP(String srcIP) {
		this.srcIP = srcIP;
	}
	public String getDstIP() {
		return dstIP;
	}
	public void setDstIP(String dstIP) {
		this.dstIP = dstIP;
	}
	public int getHlen() {
		return hlen;
	}
	public void setHlen(int hlen) {
		this.hlen = hlen;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public int getProto() {
		return proto;
	}
	public void setProto(int proto) {
		this.proto = proto;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
	
