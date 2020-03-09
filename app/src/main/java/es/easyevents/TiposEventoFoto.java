package es.easyevents;

public class TiposEventoFoto {
    private String tipoEvento;
    private int fotoEvento;

    public TiposEventoFoto(String tipoEvento, int fotoEvento) {
        this.tipoEvento = tipoEvento;
        this.fotoEvento = fotoEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public int getFotoEvento() {
        return fotoEvento;
    }

    public void setFotoEvento(int fotoEvento) {
        this.fotoEvento = fotoEvento;
    }
}
