package es.easyevents.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import es.easyevents.R;
import es.easyevents.Models.TiposEventoFoto;

public class Adaptador extends BaseAdapter {

    private Context context;
    private ArrayList<TiposEventoFoto> listaTposEventoFoto;

    public Adaptador(Context context, ArrayList<TiposEventoFoto> listaTposEventoFoto) {
        this.context = context;
        this.listaTposEventoFoto = listaTposEventoFoto;
    }

    @Override
    public int getCount() {
        return listaTposEventoFoto.size();
    }

    @Override
    public Object getItem(int i) {
        return listaTposEventoFoto.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);

        }
        ImageView imagenEvento = (ImageView) view.findViewById(R.id.fistainfantil);
        TextView textoEvento = (TextView) view.findViewById(R.id.textoinfantil);

        imagenEvento.setImageResource(listaTposEventoFoto.get(i).getFotoEvento());
        textoEvento.setText(listaTposEventoFoto.get(i).getTipoEvento());


        return view;
    }
}
