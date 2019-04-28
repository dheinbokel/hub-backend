package com.hub.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//This is no longer used but can be used for the calandar if needed later

//@Entity
//@Table(name = "HUB_EVENT")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HubEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EVENT_ID")
    private Integer eventID;

    @Column(name = "DEPARTMENT_ID")
    private Integer dptID;

    @Column(name = "FRANCHISE_ID")
    private Integer frID;

    @Column(name = "EVENT_NAME")
    private String eventName;

    @Column(name = "EVENT_DETAILS")
    private String eventDetails;

    @Column(name = "START_DATE")
    private String startDate;

    @Column(name = "END_DATE")
    private String endDate;

    HubEvent(){

    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public Integer getDptID() {
        return dptID;
    }

    public void setDptID(Integer dptID) {
        this.dptID = dptID;
    }

    public Integer getFrID() {
        return frID;
    }

    public void setFrID(Integer frID) {
        this.frID = frID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
