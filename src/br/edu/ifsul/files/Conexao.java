package br.edu.ifsul.files;

import br.edu.ifsul.util.JsonTreatment;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Conexao {
    public String getToken(){
        HttpURLConnection connection = null;
        try {
            String urlParameters = "grant_type=client_credentials&USERNAME=1_ouX5f7pJA1F2CM3A6mqJRZglIa&PASSWORD=DDy0fWGECeLRYntruBCDFSfbePEa";
            //Create connection
            URL url = new URL("https://api.cnptia.embrapa.br/token");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            connection.setRequestProperty("Authorization",
                    "Basic MV9vdVg1ZjdwSkExRjJDTTNBNm1xSlJaZ2xJYTpERHkwZldHRUNlTFJZbnRydUJDREZTZmJlUEVh");
            
            connection.setRequestProperty("Content-Length",
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "UTF-8");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            
            JsonTreatment token = new JsonTreatment();
            String strToken = token.getAccessToken(response.toString());
            return strToken;
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
    public void createWD(){
        HttpURLConnection connection = null;
        try {
            
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.cnptia.embrapa.br/clima/v1/ws?objeto=PrevisaoDiaria&acao=Proximos15Dias&idMunicipio=4314100"))
                .header("Authorization", "Bearer "+getToken()+"\"")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JsonTreatment json = new JsonTreatment();
            
            json.createWDJson(response.body());
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}

