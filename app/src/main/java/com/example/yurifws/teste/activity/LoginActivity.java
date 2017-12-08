package com.example.yurifws.teste.activity;

import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity implements  View.OnClickListener {

    private Button btnLogin;
    private Button btnSignup;
    private EditText edEmail;
    private EditText edSenha;

    private FirebaseAuth autenticacaoFirebase;
    private DatabaseReference databaseFirebase;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        autenticacaoFirebase = FirebaseConfiguracao.getFirebaseAutentication();
        databaseFirebase = FirebaseConfiguracao.getFirebase();

        init();

        edEmail.setText("yuri@gmail.com");
        edSenha.setText("123456");
    }

    private void init(){
        try{
            btnLogin = (Button) findViewById(R.id.btnLogin);
            edEmail = (EditText) findViewById(R.id.edEmail);
            edSenha = (EditText) findViewById(R.id.edESenha);
            btnSignup = (Button) findViewById(R.id.btnSignup);

            btnLogin.setOnClickListener(this);
            btnSignup.setOnClickListener(this);
        }catch (Exception ex){}

    }

    @Override
    public void onClick(View view) {
        String strEmail;
        String strSenha;
        try {
            strEmail = edEmail.getText().toString().trim();
            strSenha = edSenha.getText().toString().trim();
            if (view.getId() == R.id.btnLogin) {
                if (!TextUtils.isEmpty(strEmail) && !TextUtils.isEmpty(strSenha)) {
                    usuario = new Usuario();
                    usuario.setEmail(strEmail);
                    usuario.setSenha(StringEncryption.SHA1(strSenha));

                    validarLogin();
                } else {
                    Toast.makeText(LoginActivity.this, "PREENCHA OS CAMPOS!!", Toast.LENGTH_SHORT).show();
                }
            } else if (view.getId() == R.id.btnSignup) {
                Intent it = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                startActivity(it);
            }
        }catch (Exception ex){}
    }



    private void validarLogin(){
        autenticacaoFirebase.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "AUTENTICADO COM SUCESSO", Toast.LENGTH_SHORT).show();

                    startActivity( new Intent(LoginActivity.this,MainActivity.class));

                }else{
                    Toast.makeText(LoginActivity.this, "USUARIO OU SENHA INVALIDOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
