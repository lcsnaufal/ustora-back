package senac.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import senac.java.Services.ResponseEndPoints;

import org.json.JSONObject;
public class ProductController {

    public static ResponseEndPoints res = new ResponseEndPoints();
    public static class ProductsHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String response = "";


            if ("GET".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de produtos - GET";
                res.enviarResponse(exchange, response);
            }
            else if ("POST".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de produtos - POST";
                res.enviarResponse(exchange, response);
            }
            else if ("PUT".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de produtos - PUT";
                res.enviarResponse(exchange, response);
            }
            else if ("DELETE".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de produtos - DELETE";
                res.enviarResponse(exchange, response);
            }

            else {
                response = "nao definido." + "O metodo utilizado foi: " + exchange.getRequestMethod() + " So aceitamos get, put, post e delete";
                res.enviarResponse(exchange, response);
            }
        }

}}
