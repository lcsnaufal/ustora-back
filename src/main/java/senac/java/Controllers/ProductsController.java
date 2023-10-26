package senac.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

import senac.java.Domain.Products;
import senac.java.Services.ResponseEndPoints;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class ProductsController {

    static ResponseEndPoints res = new ResponseEndPoints();

    private static List<Products> productsList = new ArrayList<>();
    public static class ProductsHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String response = "";

            if ("GET".equals(exchange.getRequestMethod())){

                List<Products> getAllFromArray = Products.getAllProducts(productsList);


                if(!getAllFromArray.isEmpty()){

                    Products products = new Products();

                    for(Products productsJson : getAllFromArray){
                        System.out.println("Name: " + productsJson.getPName());
                        System.out.println("Price: " + productsJson.getPPrice());
                        System.out.println("Color" + productsJson.getPColor());
                        System.out.println("Description: " + productsJson.getPDescription());
                        System.out.println("Quantity: " + productsJson.getPQuantity());
                        System.out.println("Img: " + productsJson.getPImg());
                        System.out.println("");
                    }

                    System.out.println("getallfromarray"+getAllFromArray);
                    System.out.println("productsList"+productsList);

                    response = "Dados encontrados com sucesso";
                    res.enviarResponseJson(exchange, products.arrayToJson(getAllFromArray), 200);
                }

                else{
                    response = "Dados não encontrados";
                    res.enviarResponse(exchange, response);
                }
            }

            else if ("POST".equals(exchange.getRequestMethod())){

                try(InputStream requestBody = exchange.getRequestBody()){

                    JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));

                    Products products = new Products(
                            json.getString("name"),
                            json.getString("price"),
                            json.getString("color"),
                            json.getString("description"),
                            json.getString("quantity"),
                            json.getString("img")
                    );

                    productsList.add(products);

                    System.out.println("ProductsList contém: " + products.toJson());

                    res.enviarResponseJson(exchange, products.toJson(), 200);
                }
                catch(Exception e){
                    String ExceptionResponse = e.toString();

                    System.out.println(ExceptionResponse);
                    System.out.println("------------------------------------------------");

                }
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
    }
}