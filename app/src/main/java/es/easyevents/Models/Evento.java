package es.easyevents.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Evento  implements Serializable {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("publicIdCode")
    @Expose
    private int publicIdCode;
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("dateCreated")
    @Expose
    private Date dateCreated;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("hostName")
    @Expose
    private String hostName;
    @SerializedName("guestsEmails")
    @Expose
    private String[] guestsEmails;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPublicIdCode() {
        return publicIdCode;
    }

    public void setPublicIdCode(int publicIdCode) {
        this.publicIdCode = publicIdCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String[] getGuestsEmails() {
        return guestsEmails;
    }

    public void setGuestsEmails(String[] guestsEmails) {
        this.guestsEmails = guestsEmails;
    }
}
