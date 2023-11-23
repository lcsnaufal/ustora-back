package senac.java.Services;

import com.sun.net.httpserver.Headers;
import senac.java.Domain.Users;
import com.sun.net.httpserver.HttpServer;  // Criar um servidor
import com.sun.net.httpserver.HttpExchange;  //  Envia a requisicao do front pro back (passa pelos caminhos)
import com.sun.net.httpserver.HttpHandler; //

import java.io.IOException; //Erros do servidor e o que fazer
import java.io.OutputStream; //
import java.net.InetSocketAddress; // Protocolo de leitura da internet // ele quem abre o caminho para as informacoes na internet

import senac.java.Controllers.ProductsController;
import senac.java.Controllers.UserController;
import senac.java.Controllers.SalesPersonController;

public class Servidor {


    public void apiServer() throws IOException{


        HttpServer server = HttpServer.create(new InetSocketAddress(8090),
                0);

        HttpHandler SalespersonHandler = new SalesPersonController.SalesPersonHandler();
        HttpHandler UserHandler = new UserController.UserHandler();
        HttpHandler ProductsHandler = new ProductsController.ProductsHandler();


        server.createContext("/api/vendedor", exchange -> {
            configureCorsHeaders(exchange);
            SalespersonHandler.handle(exchange);
        });

        server.createContext("/api/usuario", exchange -> {
            configureCorsHeaders(exchange);
            UserHandler.handle(exchange);
        });

        server.createContext("/api/produtos", exchange -> {
            configureCorsHeaders(exchange);
            ProductsHandler.handle(exchange);
        });

        server.setExecutor(null);
        System.out.println("Servidor Iniciado");
        server.start();
    }

    private void configureCorsHeaders(HttpExchange exchange){
        Headers headers = exchange.getResponseHeaders();
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        headers.set("Access-Control-Allow-Headers", "Content-Type");

    }
}























//    public static void serverRoute(HttpExchange exchange) throws IOException{
//
//        if("GET".equals(exchange.getRequestMethod())){
//
//            String response =  "ta indo";
//            exchange.sendResponseHeaders(200, response.getBytes().length);
//
//
//            OutputStream os = exchange.getResponseBody();
//            os.write(response.getBytes());
//            os.close();
//        }else {
//            String response = "Método nao implementado";
//
//            exchange.sendResponseHeaders(405, response.getBytes().length);
//
//            OutputStream os = exchange.getResponseBody();
//            os.write(response.getBytes());
//            os.close();
//        }
//    };