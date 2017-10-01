package com.adrjun.firebase.fcm;

public class FCMPayLoad {
	private String to;	
	private String condition;
	private String registrationId;
	private String collapseKey; //This parameter identifies a group of messages 
	private String priority;
	private Integer timeToLive; //How long the message should be kept
	private FCMNotificationModel notification;
	private FCMDataModel data;
	
	public FCMPayLoad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FCMPayLoad(String to, String condition, String registrationId, String collapseKey, String priority,
			Integer timeToLive, FCMNotificationModel notification, FCMDataModel data) {
		super();
		this.to = to;
		this.condition = condition;
		this.registrationId = registrationId;
		this.collapseKey = collapseKey;
		this.priority = priority;
		this.timeToLive = timeToLive;
		this.notification = notification;
		this.data = data;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getCollapseKey() {
		return collapseKey;
	}

	public void setCollapseKey(String collapseKey) {
		this.collapseKey = collapseKey;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Integer getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(Integer timeToLive) {
		this.timeToLive = timeToLive;
	}

	public FCMNotificationModel getNotification() {
		return notification;
	}

	public void setNotification(FCMNotificationModel notification) {
		this.notification = notification;
	}

	public FCMDataModel getData() {
		return data;
	}

	public void setData(FCMDataModel data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "FCMPayLoad [to=" + to + ", condition=" + condition + ", registrationId=" + registrationId
				+ ", collapseKey=" + collapseKey + ", priority=" + priority + ", timeToLive=" + timeToLive
				+ ", notification=" + notification + ", data=" + data + "]";
	}
}
	