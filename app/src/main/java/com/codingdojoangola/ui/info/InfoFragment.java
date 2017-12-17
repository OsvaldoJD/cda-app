package com.codingdojoangola.ui.info;

//:::::::::::::::: Android imports

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codingdojoangola.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.joooonho.SelectableRoundedImageView.TAG;

//:::::::::::::::: Import from third parties (com, junit, net, org)
//:::::::::::::::: Java and javax
//:::::::::::::::: Same project import

public class InfoFragment {

    //:::::::::::: Constants


    //::::::::::::: Fields




    private DatabaseReference mDatabase;
    private static final String TAG = "QuickNotesMainActivity";

    Button btnObjectivo;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
// ...


    //*********************************** CONSTRUCTORS *********************************************

    public InfoFragment(View fragment_info){

        Log.e(TAG,"Sem Conexão???????????????????????????????");
        progressBar = (ProgressBar) fragment_info.findViewById(R.id.progressBar);
        TextView text = (TextView)fragment_info.findViewById(R.id.text_descricao);

        text.setVisibility(View.GONE);


        mDatabase=FirebaseDatabase.getInstance().getReference().child("infos");

        mDatabase.child("about").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String descricao = dataSnapshot.getValue(String.class);

                Log.e(TAG,"Sem Conexão"+descricao);
                if(dataSnapshot.exists()){


                    progressBar.setVisibility(View.GONE);
                    text.setVisibility(View.VISIBLE);

                    Log.println(Log.ERROR, TAG,"Conectado"+dataSnapshot);
                    text.setText(descricao);

                }
                else {
                    text.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    Log.println(Log.ERROR, TAG,"Sem Conexão"+dataSnapshot);

                    text.setText(descricao);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    //*********************************** ON CREATE *********************************************

    //*********************** Override Methods and Callbacks (public and Private) ******************


    //*************************************** PUBLIC METHODS ***************************************



    //*************************************** PRIVATE METHODS **************************************



    //***************************** INNER CLASSES OT INTERFACES ************************************



    //**********************************************************************************************
}
