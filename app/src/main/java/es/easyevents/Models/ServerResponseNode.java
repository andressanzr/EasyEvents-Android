package es.easyevents.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServerResponseNode implements Serializable {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("msg")
    @Expose
    private Evento evento;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
