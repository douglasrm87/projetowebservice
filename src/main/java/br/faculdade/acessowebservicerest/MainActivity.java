package br.faculdade.acessowebservicerest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

//https://blog.matheuscastiglioni.com.br/consumindo-web-service-no-android/


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText cpf = findViewById(R.id.etMain_cep);
        final TextView resposta = findViewById(R.id.etMain_resposta);

        Button btnBuscarCep = findViewById(R.id.btnMain_buscarCep);
        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("ola");
                try {
                    //Cep retorno = new HttpService(cep.getText().toString()).execute().get();
                    HttpService ws = new HttpService(cpf.getText().toString() );
                    String retorno = ws.execute().get();

                    if (retorno != null) {
                        resposta.setText(retorno.toString());
                    }
                    else {
                        resposta.setText("Não retornou dados");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
