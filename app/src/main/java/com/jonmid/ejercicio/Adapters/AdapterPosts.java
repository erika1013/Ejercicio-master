package com.jonmid.ejercicio.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jonmid.ejercicio.Models.PhotoModel;
import com.jonmid.ejercicio.R;
import com.jonmid.ejercicio.WindowThreeActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by software on 12/10/2017.
 */

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.ViewHolder> {

    List<PhotoModel> photoModelList = new ArrayList<>();
    Context context;

    // Constructor de la clase
    public AdapterPosts(List<PhotoModel> photoModelList, Context context) {
        this.photoModelList = photoModelList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Configuracion del ViewAdapter

        // Obtener la vista (item.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_posts, parent, false);

        // Pasar la vista (item.xml) al ViewHolder
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Encargado de trabajar con el item.xml y sus componentes
        holder.textViewPhotoId.setText(Integer.toString(photoModelList.get(position).getId()));
        holder.textViewPhotoTitle.setText(photoModelList.get(position).getTitle());
        holder.textViewPhotoBody.setText(photoModelList.get(position).getBody());


        String [] images ;
        images  = new String[ 6];
        images [0]="http://elvasomediolleno.guru/wp-content/uploads/2015/12/331.jpg";
        images [1]="https://i.pinimg.com/originals/3f/cd/54/3fcd540ffb5e7a475773e53c180891ac.jpg";
        images [2]="http://bucket2.glanacion.com/anexos/fotos/97/1661297.jpg";
        images [3]="http://www.esperanzadiaxdia.com.ar/wp-content/uploads/2013/05/Accion-Poetica-Esperanza-Murales-4-al-6-11.jpg";
        images [4]=" http://k35.kn3.net/taringa/2/2/9/2/2/5/eldolape22/0BE.jpg?409";
        images [5]="http://2.bp.blogspot.com/-H0EvH8XAHIc/VDl6H7VjubI/AAAAAAAAAGw/kOhe84u29lc/s1600/hey...1.jpg";

        int number = (int ) (Math.random()*5);
        Picasso.with(context).load(images[number]).into(holder.imageView);




    }

    @Override
    public int getItemCount() {
        return photoModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewPhotoId;
        TextView textViewPhotoTitle;
        TextView textViewPhotoBody;
        ImageView imageView;

        public ViewHolder(View item) {
            super(item);

            item.setOnClickListener(this);

            textViewPhotoId = (TextView) item.findViewById(R.id.id_tv_item_idpost);
            textViewPhotoTitle = (TextView) item.findViewById(R.id.id_tv_item_title);
            textViewPhotoBody = (TextView) item.findViewById(R.id.id_tv_item_body);
            imageView= (ImageView) item.findViewById(R.id.id_img_item_cardview);



        }

        @Override
        public void onClick(View view) {
            Context contextItem = view.getContext();
            Intent intent = new Intent(context, WindowThreeActivity.class);
            contextItem.startActivity(intent);
        }
    }

}
