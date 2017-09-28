package com.adrjun.firebase.fcm;

public class FCMPayLoad {
	private String to;
	private String notification;
	private String data;
	private String condition;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getNotification() {
		return notification;
	}
	public void setNotification(String notification) {
		this.notification = notification;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	@Override
	public String toString() {
		return "FCMPayLoad [to=" + to + ", notification=" + notification + ", data=" + data + ", condition=" + condition
				+ "]";
	}
}
