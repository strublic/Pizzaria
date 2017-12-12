package br.com.fiap.pizzaria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import br.com.fiap.pizzaria.model.Pedido;
import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.cbBacon)
    CheckBox cbBacon;

    @BindView(R.id.cbCalabresa)
    CheckBox cbCalabresa;

    @BindView(R.id.cbAtum)
    CheckBox cbAtum;

    @BindView(R.id.cbMussarela)
    CheckBox cbMussarela;

    @BindView(R.id.rgTamanho)
    RadioGroup rgTamanho;

    @BindView(R.id.spPagamentos)
    Spinner spPagamentos;

    private String usuarioName;
    private TextView tvUsuarioName;

    Pedido pedido = new Pedido();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUsuarioName = (TextView) findViewById(R.id.tvUsuarioName);

        if(getIntent() != null) {
            usuarioName = getIntent().getStringExtra("USUARIO");
        }

        tvUsuarioName.setText(usuarioName);

        setListenerCheckbox(cbAtum);
        setListenerCheckbox(cbBacon);
        setListenerCheckbox(cbCalabresa);
        setListenerCheckbox(cbMussarela);


    }


    private void setListenerCheckbox(final CheckBox checkbox){
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pedido.addSabor(checkbox.getText().toString());
                } else{
                    pedido.removerSabor(checkbox.getText().toString());
                }
            }
        });
    }

    @OnClick(R.id.btFecharPedido)
    public void fecharPedido(){
        pedido.setCliente(usuarioName);
        pedido.setTamanho(getTamanhoSelecionado());
        pedido.setFormaPagamento(spPagamentos.getSelectedItem().toString());

        Intent i = new Intent(this, ConfirmarPedidoActivity.class);
        i.putExtra("PEDIDO", pedido);
        startActivity(i);
    }

    public String getTamanhoSelecionado(){
        return ((RadioButton)findViewById(rgTamanho.getCheckedRadioButtonId())).getText().toString();
    }
}
