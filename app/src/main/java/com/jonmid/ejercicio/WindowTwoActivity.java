package com.jonmid.ejercicio;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jonmid.ejercicio.Adapters.AdapterPosts;
import com.jonmid.ejercicio.Connection.HttpManager;
import com.jonmid.ejercicio.Models.PhotoModel;
import com.jonmid.ejercicio.Parser.PhotoJson;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class WindowTwoActivity extends AppCompatActivity {

    ProgressBar progressBarPhoto;
    RecyclerView recyclerViewPhoto;
    List<PhotoModel> photoModelList;
    AdapterPosts adapterPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_two);

        progressBarPhoto = (ProgressBar) findViewById(R.id.id_pb_item_photo);
        recyclerViewPhoto = (RecyclerView) findViewById(R.id.id_rv_item_photo);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewPhoto.setLayoutManager(linearLayoutManager);

        loadData(Integer.toString(getIntent().getExtras().getInt("userId")));

        //String valor = Integer.toString(getIntent().getExtras().getInt("albumId"));
        //Toast.makeText(this, valor, Toast.LENGTH_SHORT).show();
    }

    public Boolean isOnLine(){
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null){
            return true;
        }else {
            return false;
        }
    }

    public void loadData(String userId){
        if (isOnLine()){
            TaskPhoto taskPhoto = new TaskPhoto();
            taskPhoto.execute("https://jsonplaceholder.typicode.com/posts?userId="+userId);
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData(){
        adapterPosts = new AdapterPosts(photoModelList, getApplicationContext());
        recyclerViewPhoto.setAdapter(adapterPosts);
    }

    public class TaskPhoto extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarPhoto.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManager.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                photoModelList = PhotoJson.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBarPhoto.setVisibility(View.GONE);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pantalla_dos, menu);
        return true;
    }


}
