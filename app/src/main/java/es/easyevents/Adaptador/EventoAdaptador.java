package es.easyevents.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import es.easyevents.Models.Evento;
import es.easyevents.R;

public class EventoAdaptador extends BaseAdapter{


    private Context context;
    private List<Evento> events;


    public EventoAdaptador(Context context,List<Evento> events) {
        this.context = context;
        this.events = events;
      //  this.layout = layout;
        //this.itemClickListener = itemListener;
      //  this.btnClickListener = btnListener;
    }

    public void EditEvent(List<Evento> nuevaLista){
        this.events = nuevaLista;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int i) {
        return events.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Evento evento = (Evento) getItem(i);


        viewHolder.titulo.setText(evento.getTitulo());
        viewHolder.fecha.setText(evento.getFecha());
        viewHolder.hora.setText(evento.getHora());
        viewHolder.anfitrion.setText(evento.getAnfitrion());
        viewHolder.direccion.setText(evento.getDireccion());
 //       viewHolder.stock.setText(String.valueOf(evento.getStock()));
    //    viewHolder.codigo.setText(String.valueOf(evento.getCodigo()));
        Glide.with(context).load(evento.getImagen()).into(viewHolder.imagen);

        return view;
    }
    public static class ViewHolder {
        @BindView(R.id.titulo)
        TextView titulo;
        @BindView(R.id.fecha)
        TextView fecha;
        @BindView(R.id.hora)
        TextView hora;
        @BindView(R.id.anfitrion)
        TextView anfitrion;
        @BindView(R.id.direccion)
        TextView direccion;
        @BindView(R.id.profile_image)
        CircleImageView imagen;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
