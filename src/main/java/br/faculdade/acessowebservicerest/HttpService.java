package br.faculdade.acessowebservicerest;

//  https://github.com/mahenrique94/requisicao-http
// https://blog.matheuscastiglioni.com.br/consumindo-web-service-no-android/

import android.os.AsyncTask;

import com.google.gson.Gson;


import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class HttpService extends AsyncTask<Void, Void, String> {
    private String cpf;
    public HttpService(String cpf){
        this.cpf = cpf;
    }

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
            try {
                // precisa er o Ip da sua maquina
               //URL url = new URL("http://192.168.15.17:8080/webmavenheroku/rest/cartaofidelidade/123");
                // https://webmavenheroku.herokuapp.com/rest/centralservicos/1/123456/Douglas
                // https://webmavenheroku.herokuapp.com/rest/centralservicos/2/123456/Douglas
                URL url = new URL("https://webmavenheroku.herokuapp.com/rest/centralservicos" + this.cpf);


                HttpURLConnection connection = (HttpURLConnection) url.openConnection();


                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "text/plain");
                connection.setRequestProperty("Accept", "text/plain");
                connection.setDoOutput(true);
                connection.setConnectTimeout(15000);
                connection.connect();

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }
            } catch (MalformedURLException e) {
                System.out.println(e.toString());

                e.printStackTrace();
            } catch (IOException e) {
                System.out.println(e.toString());
                e.printStackTrace();
            }

        System.out.println("Resposta: " + resposta.toString());
            if (resposta != null) {
                return resposta.toString();
            }
            return null;
       // return new Gson().fromJson(resposta.toString(), String.class);
    }
}