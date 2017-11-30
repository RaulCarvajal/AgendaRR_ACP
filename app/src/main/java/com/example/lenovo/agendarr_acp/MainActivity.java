package com.example.lenovo.agendarr_acp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView lista;
    listadapter la;

    String nom[],noms="";
    String tel[],tels="";
    String id[],ids="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista=(ListView)findViewById(R.id.lista);

        Async a=new Async();
        a.execute();

        lista.setOnItemClickListener(this);
    }

    public Cursor getAllContacts() {
        return getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,//URI
 /*Proyecion*/  new String[]{ContactsContract.Data._ID,ContactsContract.Data.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.TYPE},
 /*Where*/      ContactsContract.Data.MIMETYPE+"= '"+ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE +"' AND "+ContactsContract.CommonDataKinds.Phone.NUMBER+" IS NOT NULL",
                null,
 /*Order*/      ContactsContract.Data.DISPLAY_NAME+" ASC"
        );
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent in=new Intent(this,Detalle.class);
        in.putExtra("id",Integer.parseInt(id[i]));
        in.putExtra("nom",nom[i]);
        in.putExtra("tel",tel[i]);
        startActivity(in);
    }

    public class Async extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] objects) {
            Cursor c=getAllContacts();

            while (c.moveToNext()){
                ids+=c.getString(0)+"\t";
                noms+=c.getString(1)+"\t";
                tels+=c.getString(2)+"\t";
            }
            nom=noms.split("\t");
            tel=tels.split("\t");
            id=ids.split("\t");

            la=new listadapter(getApplicationContext(),nom,tel);
            lista.setAdapter(la);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            lista.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(),"...Cargado",Toast.LENGTH_SHORT).show();
        }
    }

}