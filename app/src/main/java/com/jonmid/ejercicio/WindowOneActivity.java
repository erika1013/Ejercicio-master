package com.jonmid.ejercicio;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jonmid.ejercicio.Adapters.AdapterUsers;
import com.jonmid.ejercicio.Connection.HttpManager;
import com.jonmid.ejercicio.Models.AlbumModel;
import com.jonmid.ejercicio.Parser.JsonUsers;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class WindowOneActivity extends AppCompatActivity {

    ProgressBar progressBarAlbum;
    RecyclerView recyclerViewAlbum;
    List<AlbumModel> albumModelList;
    AdapterUsers adapterUsers;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_one);
        toolbar=(Toolbar)findViewById(R.id.id_tb_toolbar);
        showToolbar(getResources().getString(R.string.str_tb_title_p2));
        progressBarAlbum = (ProgressBar) findViewById(R.id.id_pb_item_album);
        recyclerViewAlbum = (RecyclerView) findViewById(R.id.id_rv_item_album);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewAlbum.setLayoutManager(linearLayoutManager);

        loadData();

    }
    public void showToolbar(String title){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
    }

    public void showPantalla2(){
        Intent intent = new Intent(this, WindowTwoActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pantalla_uno, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showPantalla2();
        return super.onOptionsItemSelected(item);
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

    public void loadData(){
        if (isOnLine()){
            TaskAlbum taskAlbum = new TaskAlbum();
            taskAlbum.execute("https://jsonplaceholder.typicode.com/users");
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData(){
        adapterUsers = new AdapterUsers(albumModelList, getApplicationContext());
        recyclerViewAlbum.setAdapter(adapterUsers);
    }

    public class TaskAlbum extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarAlbum.setVisibility(View.VISIBLE);
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
                albumModelList = JsonUsers.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBarAlbum.setVisibility(View.GONE);
        }
    }


}
