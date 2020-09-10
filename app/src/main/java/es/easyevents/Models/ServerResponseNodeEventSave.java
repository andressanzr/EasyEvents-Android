package es.easyevents.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServerResponseNodeEventSave implements Serializable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("msg")
    @Expose
    private int publicIdCode;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPublicIdCode() {
        return publicIdCode;
    }

    public void setPublicIdCode(int publicIdCode) {
        this.publicIdCode = publicIdCode;
    }
}
