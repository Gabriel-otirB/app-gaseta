package devandroid.gabrito.appgaseta.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import devandroid.gabriel.appgaseta.R;
import devandroid.gabrito.appgaseta.controller.CombustivelController;
import devandroid.gabrito.appgaseta.model.Combustivel;
import devandroid.gabrito.appgaseta.util.UtilGasEta;

public class GasEtaActivity extends AppCompatActivity {

    Combustivel combustivelGasolina;
    Combustivel combustivelEtanol;
    CombustivelController combustivelController;
    EditText editGasolina;
    EditText editEtanol;

    TextView txtResultado;

    Button btnCalcular;
    Button btnLimpar;
    Button btnSalvar;
    Button btnFinalizar;

    double precoGasolina;
    double precoEtanol;
    String recomendacao;

    List<Combustivel> dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gaseta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        combustivelController = new CombustivelController(GasEtaActivity.this);
        dados = combustivelController.getListaDeDados();

        Combustivel objAlteracao = dados.get(0);
        objAlteracao.setNomeDoCombustivel("**GASOLINA**");
        objAlteracao.setPrecoDoCombustivel(5.97);
        objAlteracao.setRecomendacao("**ABASTECER COM GASOLINA**");
//        combustivelController.alterar(objAlteracao);

        editGasolina = findViewById(R.id.editGasolina);
        editEtanol = findViewById(R.id.editEtanol);

        txtResultado = findViewById(R.id.txtResultado);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isDadosOk = true;

                if(TextUtils.isEmpty(editGasolina.getText())) {
                    editGasolina.setError("* Obrigatório");
                    editGasolina.requestFocus();
                    isDadosOk = false;
                }

                if(TextUtils.isEmpty(editEtanol.getText())) {
                    editEtanol.setError("* Obrigatório");
                    editEtanol.requestFocus();
                    isDadosOk = false;
                }

                if(isDadosOk) {

                    precoGasolina = Double.parseDouble(editGasolina.getText().toString());
                    precoEtanol = Double.parseDouble(editEtanol.getText().toString());

                    recomendacao = UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol);

                    txtResultado.setText(recomendacao);

                    btnSalvar.setEnabled(true);

                } else {

                    btnSalvar.setEnabled(false);

                    Toast.makeText(GasEtaActivity.this,
                            "Por favor digite os dados obrigatórios...",
                                Toast.LENGTH_LONG).show();

                }

            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                combustivelGasolina = new Combustivel();
                combustivelEtanol = new Combustivel();

                combustivelGasolina.setNomeDoCombustivel("Gasolina");
                combustivelGasolina.setPrecoDoCombustivel(precoGasolina);
                combustivelGasolina.setRecomendacao(UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol));

                combustivelEtanol.setNomeDoCombustivel("Etanol");
                combustivelEtanol.setPrecoDoCombustivel(precoEtanol);
                combustivelEtanol.setRecomendacao(UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol));

                combustivelController.salvar(combustivelEtanol);
                combustivelController.salvar(combustivelGasolina);
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editEtanol.setText("");
                editGasolina.setText("");

                btnSalvar.setEnabled(false);

                combustivelController.limpar();

            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GasEtaActivity.this, "GasEta boa economia...", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}
