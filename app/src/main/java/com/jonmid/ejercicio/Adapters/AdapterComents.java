package com.jonmid.ejercicio.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jonmid.ejercicio.Models.AlbumModel;
import com.jonmid.ejercicio.Models.ComentsModel;
import com.jonmid.ejercicio.R;
import com.jonmid.ejercicio.WindowOneActivity;
import com.jonmid.ejercicio.WindowTwoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by software on 12/10/2017.
 */

public class AdapterComents extends RecyclerView.Adapter<AdapterComents.ViewHolder> {

    List<ComentsModel> albumModelList = new ArrayList<>();
    Context context;

    // Constructor de la clase
    public AdapterComents(List<ComentsModel> albumModelList, Context context) {
        this.albumModelList = albumModelList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Configuracion del ViewAdapter

        // Obtener la vista (item.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coments, parent, false);

        // Pasar la vista (item.xml) al ViewHolder
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Encargado de trabajar con el item.xml y sus componentes
        holder.textViewId.setText(Integer.toString(albumModelList.get(position).getId()));
        holder.textViewEmail.setText(albumModelList.get(position).getEmail());
        holder.textViewBody.setText(albumModelList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return albumModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewId;
        TextView textViewEmail;
        TextView textViewBody;

        public ViewHolder(View item) {
            super(item);

            item.setOnClickListener(this);

            textViewId = (TextView) item.findViewById(R.id.id_tv_itemcom_idcom);
            textViewEmail = (TextView) item.findViewById(R.id.id_tv_itemcome_email);
            textViewBody=(TextView)item.findViewById(R.id.id_tv_itemcoment_body);
        }

        @Override
        public void onClick(View view) {
            Context contextItem = view.getContext();
            Intent intent = new Intent(context, WindowOneActivity.class);
            intent.putExtra("postId", albumModelList.get(getLayoutPosition()).getId());
            contextItem.startActivity(intent);


            //String valor = Integer.toString(albumModelList.get(getLayoutPosition()).getId());
            //Toast.makeText(contextItem, valor, Toast.LENGTH_SHORT).show();
        }
    }

}
