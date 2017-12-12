package br.com.fiap.pizzaria;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username)
    TextInputLayout username;

    @BindView(R.id.password)
    TextInputLayout password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        username.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()){

                } else{

                }
            }
        });
    }

    @OnClick(R.id.btConectar)
    public void conectar(){
        if (validaUsuario() && validaSenha()) {
            Intent principal = new Intent(this, MainActivity.class);
            principal.putExtra("USUARIO", "Olá " + username.getEditText().getText().toString());
            startActivity(principal);
        }
    }

    private boolean validaUsuario() {
        if (username.getEditText().getText().toString().isEmpty()){
            username.setError("Login não informado");
            return false;
        } else{
            username.setError(null);
            return true;
        }
    }


    private boolean validaSenha() {
        if (password.getEditText().getText().toString().isEmpty()){
            password.setError("Senha não informada");
            return false;
        } else{
            password.setError(null);
            return true;
        }
    }

}
