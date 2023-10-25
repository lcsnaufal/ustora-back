package senac.java.Services;

import  com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseEndPoints {
    public static void enviarResponse(HttpExchange exchange, String response) throws IOException {

            exchange.sendResponseHeaders(200, response.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
    }

    public static void enviarResponseJson(HttpExchange exchange, JSONObject salespersonJson, Integer statusCode) throws IOException{
//        JSONObject response
        exchange.getResponseHeaders().set("Content-Type", "application/json");      // aceitar o tipo json

        byte[] responseBytes = salespersonJson.toString().getBytes("UTF-8");      // transforma em array e pega todos os dados em BYTES na UTF 8

        exchange.sendResponseHeaders(statusCode, responseBytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(responseBytes);
        os.close();
    }
}

