package com.example.yurifws.teste.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yurifws.teste.R;
import com.example.yurifws.teste.dao.FirebaseConfiguracao;
import com.example.yurifws.teste.model.Usuario;
import com.example.yurifws.teste.utils.StringEncryption;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;

public class CadastroUsuarioActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edCadEmail;
    private EditText edCadSenha;
    private EditText edCadConfirmarSenha;
    private Button btnCadastrar;

    private FirebaseAuth firebaseAutenticacao;
    private DatabaseReference firebaseReferencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        init();

        firebaseAutenticacao = FirebaseConfiguracao.getFirebaseAutentication();
        firebaseReferencia = FirebaseConfiguracao.getFirebase();
    }

    private void init(){
        try{
            edCadEmail = (EditText) findViewById(R.id.edCadEmail);
            edCadSenha = (EditText) findViewById(R.id.edCadSenha);
            edCadConfirmarSenha = (EditText) findViewById(R.id.edCadConfirmarSenha);
            btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

            btnCadastrar.setOnClickListener(this);
        }catch(Exception ex){}
    }

    @Override
    public void onClick(View view) {
        Usuario usuario;
        String strEmail;
        String strSenha;
        String strConfirmarSenha;
        try{
            strEmail = edCadEmail.getText().toString().trim();
            strSenha =  edCadSenha.getText().toString().trim();
            strConfirmarSenha = edCadConfirmarSenha.getText().toString().trim();
            if (view.getId() == R.id.btnCadastrar){
                if (!TextUtils.isEmpty(strEmail) && !TextUtils.isEmpty(strSenha)){
                    if(strSenha.equals(strConfirmarSenha)){

                        usuario = new Usuario();
                        usuario.setEmail(strEmail);
                        usuario.setSenha(StringEncryption.SHA1(strSenha));

                        cadastrarUsuario(usuario);
                    }
                }else{
                    Toast.makeText(CadastroUsuarioActivity.this, "CADASTRO INVALIDO", Toast.LENGTH_SHORT).show();
                }


            }
        }catch(Exception ex){}
    }

    private void cadastrarUsuario(final Usuario usuario){
        try{

            firebaseAutenticacao.createUserWithEmailAndPassword(
                    usuario.getEmail(),
                    usuario.getSenha()
            ).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        firebaseReferencia.child("usuarios").child(firebaseAutenticacao.getCurrentUser().getUid()).setValue(usuario);
                        Toast.makeText(CadastroUsuarioActivity.this, "USUARIO CADATRADO COM SUCESSO", Toast.LENGTH_SHORT).show();
                    }else{
                        String error;
                        try{
                            error = "";
                            throw  task.getException();
                        }catch(FirebaseAuthWeakPasswordException e){
                            error = "SENHA FRACA, POR FAVOR INFORMAR UMA SENHA MAIS FORTE";

                        }catch (FirebaseAuthInvalidCredentialsException e){
                            error ="EMAIL INVALIDO";

                        }catch (FirebaseAuthUserCollisionException e){
                            error = "USUARIO JA EXISTENTE";

                        }catch (Exception ex){
                            error = "ERROR AO EFETUAR CADASTRO";
                            ex.printStackTrace();
                        }

                        Toast.makeText(CadastroUsuarioActivity.this, error, Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }catch (Exception ex){}
    }


}
