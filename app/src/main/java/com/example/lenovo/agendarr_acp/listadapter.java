package com.example.lenovo.agendarr_acp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by lenovo on 29/11/2017.
 */

public class listadapter extends BaseAdapter {
    Context context;
    String nom[];
    String  tel[];
    String ini[];
    LayoutInflater inflater;
    String p,t;
    public listadapter(Context context,String nom[],String tel[]){
        this.context=context;
        this.nom=nom;
        this.tel=tel;
        ini=getIniciales();
        p=getEmojiByUnicode(0x1F468);
        t=getEmojiByUnicode(0x1F4DE);
    }

    public String[] getIniciales(){
        String temp[]=new String[nom.length];
        for (int i=0;i<nom.length;i++){
            temp[i]=nom[i].substring(0,1);
        }
        return temp;
    }

    @Override
    public int getCount() {
        return nom.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Declare Variables
        TextView txtNombre;
        TextView txtTelefono;
        TextView ini;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_item, viewGroup, false);

        // Locate the TextViews in listview_item.xml
        txtNombre = (TextView) itemView.findViewById(R.id.nom);
        txtTelefono = (TextView) itemView.findViewById(R.id.tel);
        ini = (TextView) itemView.findViewById(R.id.ini);

        // Capture position and set to the TextViews
        txtNombre.setText(p+" "+nom[i]);
        txtTelefono.setText(t+" "+tel[i]);
        ini.setText(this.ini[i]);

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        ini.setTextColor(color);

        return itemView;
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}
