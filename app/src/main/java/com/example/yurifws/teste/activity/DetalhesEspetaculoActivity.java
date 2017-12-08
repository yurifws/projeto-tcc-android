package com.example.yurifws.teste.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.yurifws.teste.R;
import com.example.yurifws.teste.dao.FirebaseConfiguracao;
import com.example.yurifws.teste.model.Endereco;
import com.example.yurifws.teste.model.Espetaculo;
import com.example.yurifws.teste.model.Evento;
import com.example.yurifws.teste.model.Local;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DetalhesEspetaculoActivity extends AppCompatActivity {

    private TextView txtDetalhesNome;
    private TextView txtDetalhesSinopse;
    private TextView txtDetalhesGenero;
    private TextView txtDetalhesClassificacao;
    private TextView txtDetalhesDuracao;
    private TextView txtDetalhesEndereco;


    private String idEspetaculoClicado;

    private DatabaseReference firebaseReferenciaEventos;
    private DatabaseReference firebaseReferenciaEspetaculos;
    private DatabaseReference firebaseReferenciaLocais;
    private DatabaseReference firebaseReferenciaEnderecos;

    private Espetaculo espetaculo;
    private Evento evento;
    private ArrayList<Local> locais;
    private ArrayList<Endereco> enderecos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_espetaculo);

        init();

        Bundle bundle = getIntent().getExtras();
        idEspetaculoClicado = bundle.getString("idEspetaculoClicado");

        firebaseReferenciaEventos = FirebaseConfiguracao.getFirebase().child("eventos");
        firebaseReferenciaEspetaculos = FirebaseConfiguracao.getFirebase().child("espetaculos");
        firebaseReferenciaLocais = FirebaseConfiguracao.getFirebase().child("locais");
        firebaseReferenciaEnderecos = FirebaseConfiguracao.getFirebase().child("enderecos");

        buscarDetalhesEspetaculo();



    }

    private void init(){
        try{
            txtDetalhesNome = (TextView) findViewById(R.id.txtDetalhesNome);
            txtDetalhesSinopse = (TextView) findViewById(R.id.txtDetalhesSinopse);
            txtDetalhesGenero = (TextView) findViewById(R.id.txtDetalhesGenero);
            txtDetalhesClassificacao = (TextView) findViewById(R.id.txtDetalhesClassificacao);
            txtDetalhesDuracao = (TextView) findViewById(R.id.txtDetalhesDuracao);
            txtDetalhesEndereco = (TextView) findViewById(R.id.txtDetalhesEndereco);

            espetaculo = new Espetaculo();
            evento = new Evento();
            locais = new ArrayList<>();
            enderecos = new ArrayList<>();

        }catch (Exception ex){}

    }

    private void buscarDetalhesEspetaculo(){
        try{
            firebaseReferenciaEspetaculos.child(idEspetaculoClicado).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    espetaculo = new Espetaculo(idEspetaculoClicado,(HashMap<String, String>) dataSnapshot.getValue());
                    buscarDetalhesEvento();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.i("onCancelled", "buscarDetalhesEspetaculo");

                }
            });
        }catch (Exception ex){}
    }

    private void buscarDetalhesEvento(){
        try{

            firebaseReferenciaEventos.child(idEspetaculoClicado).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    evento = new Evento(idEspetaculoClicado,(HashMap<String, String>) dataSnapshot.getValue());
                    buscarDetalhesLocais();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.i("onCancelled", "buscarDetalhesEvento");
                }
            });
        }catch (Exception ex){}
    }

    private void buscarDetalhesLocais(){
        try{

            for(final String idLocal: evento.getIdsLocais()){
                firebaseReferenciaLocais.child(idLocal).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final Local local = new Local(idLocal,(HashMap<String, String>) dataSnapshot.getValue());
                        buscarDetalhesEnderecos(local);

                        locais.add(local);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.i("onCancelled", "buscarDetalhesLocais");
                    }
                });

            }

        }catch (Exception ex){}
    }

    private void buscarDetalhesEnderecos(Local local){
        final String idEndereco;
        try{
            idEndereco = local.getIdEndereco();

                firebaseReferenciaEnderecos.child(idEndereco).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Endereco endereco = new Endereco(idEndereco, (HashMap<String, String>) dataSnapshot.getValue());

                        retornarEspetaculo();
                        txtDetalhesEndereco.setText(txtDetalhesEndereco.getText()+ retornarEndereco(endereco));
                        enderecos.add(endereco);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.i("onCancelled", "buscarDetalhesEnderecos");
                    }
                });
        }catch (Exception ex){}
    }


    private void preecherEspeatculo(){
        try{


        }catch (Exception ex){}

    }

    private void retornarEspetaculo(){
        try{
            txtDetalhesNome.setText(espetaculo.getNome());
            txtDetalhesSinopse.setText("Sinopse:\n"+espetaculo.getSinopse());
            txtDetalhesGenero.setText("Gênero:\n"+espetaculo.getGenero());
            txtDetalhesClassificacao.setText("Classificação:\n"+espetaculo.getClassificacao());
            txtDetalhesDuracao.setText("Duração:\n"+espetaculo.getDuracao());


        }catch (Exception ex){}

    }

    private String retornarEndereco(Endereco endereco){
        String retorno = "";
        try{
                retorno = retorno+"Endereço:\n"+
                        "Logradouro: "+endereco.getLogradouro()+"\n"+
                        "Número: "+endereco.getNumero()+"\n"+
                        "Bairro: "+endereco.getBairro()+"\n"+
                        "Cidade: "+endereco.getCidade()+"\n"+
                        "Uf: "+endereco.getUf()+"\n"+
                        "Cep: "+endereco.getCep()+"\n"+
                        "País: "+endereco.getPais()+"\n\n";

            return retorno;

        }catch (Exception ex){}

        return retorno;
    }
}
