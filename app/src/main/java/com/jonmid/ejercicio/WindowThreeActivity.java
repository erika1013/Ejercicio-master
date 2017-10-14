package com.jonmid.ejercicio;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jonmid.ejercicio.Adapters.AdapterComents;
import com.jonmid.ejercicio.Adapters.AdapterPosts;
import com.jonmid.ejercicio.Connection.HttpManager;
import com.jonmid.ejercicio.Models.ComentsModel;
import com.jonmid.ejercicio.Models.PhotoModel;
import com.jonmid.ejercicio.Parser.ComentsJson;
import com.jonmid.ejercicio.Parser.PhotoJson;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class WindowThreeActivity extends AppCompatActivity {

    ProgressBar progressBarPhoto;
    RecyclerView recyclerViewPhoto;
    List<ComentsModel> comentsModelList;
    AdapterComents adapterComents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);

        progressBarPhoto = (ProgressBar) findViewById(R.id.id_pb_item);
        recyclerViewPhoto = (RecyclerView) findViewById(R.id.id_rv_item);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewPhoto.setLayoutManager(linearLayoutManager);

        loadData(Integer.toString(getIntent().getExtras().getInt("postId")));

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

    public void loadData(String postId){
        if (isOnLine()){
            TaskPhoto taskPhoto = new TaskPhoto();
            taskPhoto.execute("https://jsonplaceholder.typicode.com/comments?postId="+postId);
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData(){
        adapterComents = new AdapterComents(comentsModelList, getApplicationContext());
        recyclerViewPhoto.setAdapter(adapterComents);
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
                comentsModelList = ComentsJson.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBarPhoto.setVisibility(View.GONE);
        }
    }
}
