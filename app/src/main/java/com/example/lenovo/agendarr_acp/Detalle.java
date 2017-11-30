package com.example.lenovo.agendarr_acp;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Detalle extends AppCompatActivity {

    EditText tel,nom;
    String n="",t="";
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle datos = this.getIntent().getExtras();


        if(datos!=null){
            id = datos.getInt("id");
            n = datos.getString("nom");
            t = datos.getString("tel");
        }

        nom=(EditText)findViewById(R.id.nom1);
        tel=(EditText)findViewById(R.id.tel1);
        nom.setText(n);
        tel.setText(t);
        final Intent in= new Intent(this,MainActivity.class);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    updateContact(id+"",tel.getText().toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (OperationApplicationException e) {
                    e.printStackTrace();
                }
                startActivity(in);
            }
        });
    }


    public void updateContact(String id,String nombre) throws RemoteException, OperationApplicationException {
        ArrayList<ContentProviderOperation> ops =
                new ArrayList<ContentProviderOperation>();

        ops.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                .withSelection(ContactsContract.Data._ID + "= ?", new String[]{id})
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, nombre)
                .build()
        );
        getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
    }


}
