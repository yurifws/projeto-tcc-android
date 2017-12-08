package com.example.yurifws.teste.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yurifws.teste.R;
import com.example.yurifws.teste.dao.FirebaseConfiguracao;
import com.example.yurifws.teste.model.Espetaculo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvEventos;


    private DatabaseReference firebaseReferenciaEspetaculos;
    private ArrayList<String> arlEventos;
    private ArrayList<String> arlKeysEventos;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();

        firebaseReferenciaEspetaculos = FirebaseConfiguracao.getFirebase().child("espetaculos");

        arlEventos = new ArrayList<>();
        arlKeysEventos = new ArrayList<>();
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arlEventos);

        try {
            lvEventos.setAdapter(adapter);
            firebaseReferenciaEspetaculos.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String keyEspetaculo = dataSnapshot.getKey();
                    String strEspetaculo = dataSnapshot.child("nome").getValue(String.class);
                    arlKeysEventos.add(keyEspetaculo);
                    arlEventos.add(strEspetaculo);
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    String keyEspetaculo = dataSnapshot.getKey();
                    String strEspetaculo = dataSnapshot.child("nome").getValue(String.class);
                    int intIndex = arlKeysEventos.indexOf(keyEspetaculo);
                    arlEventos.set(intIndex, strEspetaculo);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    String keyEspetaculo = dataSnapshot.getKey();

                    String strEspetaculo = dataSnapshot.child("nome").getValue(String.class);
                    arlKeysEventos.remove(keyEspetaculo);
                    arlEventos.remove(strEspetaculo);
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }catch(Exception ex){}

}

    private void init(){
        try{
            lvEventos = (ListView) findViewById(R.id.lvEventos);

            lvEventos.setOnItemClickListener(this);

        }catch (Exception ex){}

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        try{
            Toast.makeText(MainActivity.this, "key: "+arlKeysEventos.get(i) +"nome: "+ arlEventos.get(i), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this,DetalhesEspetaculoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("idEspetaculoClicado", arlKeysEventos.get(i));
            intent.putExtras(bundle);
            startActivity(intent);

        }catch (Exception ex){}
    }

}
