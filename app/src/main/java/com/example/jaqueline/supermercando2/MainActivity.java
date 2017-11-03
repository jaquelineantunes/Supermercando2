package com.example.jaqueline.supermercando2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSingin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSingin = (TextView) findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(this);
        textViewSingin.setOnClickListener(this);
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Por favor insira o Email", Toast.LENGTH_SHORT).show();
            //para a função e conrinua mais tarde
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Por favor insira a Senha", Toast.LENGTH_LONG).show();
            return;
        }
        //Se a validação estiver correta
        //Nós vamos mostrar o progressDialog

        progressDialog.setMessage("Registrando");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    Toast.makeText(MainActivity.this, "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Falha ao cadastrar. Por favor tente novamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
        @Override
        public void onClick(View view) {
        if(view == buttonRegister){
            registerUser();
        }
        if(view == textViewSingin){
            //startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
