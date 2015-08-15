package net.domain;

public class Alarm {

	private int id;
	private String srcIP;
	private String dstIP;
	private int sport;
	private int dport;
	private String alarm_msg;
	private String alarm_class;
	private int severity;
	private int proto;
	private String timestamp;
	
	public Alarm() {
		super();
	}
	public Alarm(int id, String srcIP, String dstIP, int sport, int dport,
			String alarm_msg, String alarm_class, int severity, int proto,
			String timestamp) {
		super();
		this.id = id;
		this.srcIP = srcIP;
		this.dstIP = dstIP;
		this.sport = sport;
		this.dport = dport;
		this.alarm_msg = alarm_msg;
		this.alarm_class = alarm_class;
		this.severity = severity;
		this.proto = proto;
		this.timestamp = timestamp;
	}
	public int getSeverity() {
		return severity;
	}
	public void setSeverity(int severity) {
		this.severity = severity;
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
	public int getSport() {
		return sport;
	}
	public void setSport(int sport) {
		this.sport = sport;
	}
	public int getDport() {
		return dport;
	}
	public void setDport(int dport) {
		this.dport = dport;
	}
	public String getAlarm_msg() {
		return alarm_msg;
	}
	public void setAlarm_msg(String alarm_msg) {
		this.alarm_msg = alarm_msg;
	}
	public String getAlarm_class() {
		return alarm_class;
	}
	public void setAlarm_class(String alarm_class) {
		this.alarm_class = alarm_class;
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
