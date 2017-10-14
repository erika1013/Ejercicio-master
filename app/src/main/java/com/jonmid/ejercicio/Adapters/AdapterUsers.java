package com.jonmid.ejercicio.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jonmid.ejercicio.Models.AlbumModel;
import com.jonmid.ejercicio.R;
import com.jonmid.ejercicio.WindowTwoActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by software on 12/10/2017.
 */

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.ViewHolder> {

    List<AlbumModel> albumModelList = new ArrayList<>();
    Context context;

    // Constructor de la clase
    public AdapterUsers(List<AlbumModel> albumModelList, Context context) {
        this.albumModelList = albumModelList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Configuracion del ViewAdapter

        // Obtener la vista (item.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_users, parent, false);

        // Pasar la vista (item.xml) al ViewHolder
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Encargado de trabajar con el item.xml y sus componentes
        holder.textViewId.setText(Integer.toString(albumModelList.get(position).getId()));
        holder.textViewName.setText(albumModelList.get(position).getName());
        holder.textViewUsername.setText(albumModelList.get(position).getUsername());

        String [] images ;
        images  = new String[ 4];
        images [0]="https://i.ytimg.com/vi/YQHsXMglC9A/maxresdefault.jpg";
        images [1]="https://cdn-images-1.medium.com/max/1400/1*R09fektet6KO3OwegL2DjA.jpeg";
        images [2]="https://imbo.vgc.no/users/vgno/images/3f475ef2bb60bba61525faa67053208a?t[0]=crop:width=5716,height=3816,x=0,y=0&t[1]=maxSize:width=990&t[2]=resize:width=990&accessToken=0e405d8bfb2ed5b18cec1ab8a36db6571d21b4533c0a4f511919a288f78ff249";
        images [3]="https://imbo.vgc.no/users/vgno/images/3f475ef2bb60bba61525faa67053208a?t[0]=crop:width=5716,height=3816,x=0,y=0&t[1]=maxSize:width=990&t[2]=resize:width=990&accessToken=0e405d8bfb2ed5b18cec1ab8a36db6571d21b4533c0a4f511919a288f78ff249";


        int number = (int ) (Math.random()*3);
        Picasso.with(context).load(images[number]).into(holder.imageView);




    }

    @Override
    public int getItemCount() {
        return albumModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewId;
        TextView textViewName;
        TextView textViewUsername;
        ImageView imageView;

        public ViewHolder(View item) {
            super(item);

            item.setOnClickListener(this);

            textViewId = (TextView) item.findViewById(R.id.id_tv_item_id);
            textViewName = (TextView) item.findViewById(R.id.id_tv_item_name);
            textViewUsername = (TextView) item.findViewById(R.id.id_tv_item_username);
            imageView= (ImageView) item.findViewById(R.id.id_img_item_cardview);
        }

        @Override
        public void onClick(View view) {
            Context contextItem = view.getContext();
            Intent intent = new Intent(context, WindowTwoActivity.class);
            intent.putExtra("userId", albumModelList.get(getLayoutPosition()).getId());
            contextItem.startActivity(intent);


            //String valor = Integer.toString(albumModelList.get(getLayoutPosition()).getId());
            //Toast.makeText(contextItem, valor, Toast.LENGTH_SHORT).show();
        }
    }

}
