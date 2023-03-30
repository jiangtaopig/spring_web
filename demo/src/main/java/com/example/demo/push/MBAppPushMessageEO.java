package com.example.demo.push;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MBAppPushMessageEO {

    private String pushId;

    private String title;

    private String content;

    private String images;

    private String type;

    private String sendUserCode;

    private String sendUserName;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date sendTime;

    private String receiveUserCode;

    private String receiveUserName;

    private String state;

    private Long offlineTime;

    private String isRead;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date lastUpdateState;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date lastUpdateRead;

    private String platform;

    private String deviceID;

    private String businessNo;

    private String businessData;

    private String businessType;

    private String sendFailError;

    private String updateStateFailError;

    private String appCode;

    private String pushMethod;

    private String messageId;

    private String businessSource;

    private String businessSubType;

    private Long sendCount;

    private Long logId;


    private String pushDeviceSort;//多设备推送消息中心查询使用

    private String isFilter;

    private String filterReason;

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSendUserCode() {
        return sendUserCode;
    }

    public void setSendUserCode(String sendUserCode) {
        this.sendUserCode = sendUserCode;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getReceiveUserCode() {
        return receiveUserCode;
    }

    public void setReceiveUserCode(String receiveUserCode) {
        this.receiveUserCode = receiveUserCode;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Long offlineTime) {
        this.offlineTime = offlineTime;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public Date getLastUpdateState() {
        return lastUpdateState;
    }

    public void setLastUpdateState(Date lastUpdateState) {
        this.lastUpdateState = lastUpdateState;
    }

    public Date getLastUpdateRead() {
        return lastUpdateRead;
    }

    public void setLastUpdateRead(Date lastUpdateRead) {
        this.lastUpdateRead = lastUpdateRead;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getBusinessData() {
        return businessData;
    }

    public void setBusinessData(String businessData) {
        this.businessData = businessData;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getSendFailError() {
        return sendFailError;
    }

    public void setSendFailError(String sendFailError) {
        this.sendFailError = sendFailError;
    }

    public String getUpdateStateFailError() {
        return updateStateFailError;
    }

    public void setUpdateStateFailError(String updateStateFailError) {
        this.updateStateFailError = updateStateFailError;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getPushMethod() {
        return pushMethod;
    }

    public void setPushMethod(String pushMethod) {
        this.pushMethod = pushMethod;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getBusinessSource() {
        return businessSource;
    }

    public void setBusinessSource(String businessSource) {
        this.businessSource = businessSource;
    }

    public String getBusinessSubType() {
        return businessSubType;
    }

    public void setBusinessSubType(String businessSubType) {
        this.businessSubType = businessSubType;
    }

    public Long getSendCount() {
        return sendCount;
    }

    public void setSendCount(Long sendCount) {
        this.sendCount = sendCount;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getPushDeviceSort() {
        return pushDeviceSort;
    }

    public void setPushDeviceSort(String pushDeviceSort) {
        this.pushDeviceSort = pushDeviceSort;
    }

    public String getIsFilter() {
        return isFilter;
    }

    public void setIsFilter(String isFilter) {
        this.isFilter = isFilter;
    }

    public String getFilterReason() {
        return filterReason;
    }

    public void setFilterReason(String filterReason) {
        this.filterReason = filterReason;
    }
}
