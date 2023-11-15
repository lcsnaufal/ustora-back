package senac.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import senac.java.DAL.UserDal;
import senac.java.Domain.Salesperson;
import senac.java.Domain.Users;
import senac.java.Services.ResponseEndPoints;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;


public class UserController {

    static ResponseEndPoints res = new ResponseEndPoints();
    private static List<Users> usersList = new ArrayList<>();

    public static class UserHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String response = "";
            UserDal userDal = new UserDal();

            if ("GET".equals(exchange.getRequestMethod())) {

                List<Users> getAllFromArray = Users.getAllUsers(usersList);

                if(!getAllFromArray.isEmpty()){

                    Users users = new Users();

                    for(Users user : getAllFromArray){
                        System.out.println("Name: " + user.getName());
                        System.out.println("Last Name: " + user.getLastName());
                        System.out.println("Age: " + user.getAge());
                        System.out.println("Address: " + user.getAddress());
                        System.out.println("Email: " + user.getEmail());
                        System.out.println("Password: " + user.getPassword());
                        System.out.println("Cpf" + user.getCpf());
                        System.out.println("");
                    }

                    System.out.println("getallfromarray"+getAllFromArray);
                    System.out.println("usersList"+usersList);

                    response = "Dados encontrados com sucesso";
                    res.enviarResponseJson(exchange, users.arrayToJson(getAllFromArray), 200);

                }
                else {
                    System.out.println("Nenhum usuario foi encontrado");
                }
            }

            else if ("POST".equals(exchange.getRequestMethod())) {

                try (InputStream requestBody = exchange.getRequestBody()) {

                    JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
                    int resp = 0;

                    Users user = new Users(
                            json.getString("name"),
                            json.getString("lastName"),
                            json.getString("age"),
                            json.getString("address"),
                            json.getString("email"),
                            json.getString("password"),
                            json.getString("cpf")
                    );

                    usersList.add(user);

                    resp = userDal.inserirUsuario(user.name, user.lastName, user.age, user.address, user.email, user.password, user.cpf);



                    if (resp == 0){
                        response = "Houve um problema ao criar o usuário";
                    }
                    else {
                        response = "Usuário criado com sucesso";
                    }

                    res.enviarResponse(exchange, response);
                }
                catch(Exception e){
                    String ExceptionResponse = e.toString();

                    System.out.println(ExceptionResponse);
                    System.out.println("------------------------------------------------");

                }
            }

            else if ("PUT".equals(exchange.getRequestMethod())) {

                userDal = new UserDal();

                try{
                    userDal.atualizarUsuario();
                } catch (Exception e){
                    System.out.println("O erro foi: " + e);
                }

                response = "Essa e a rota de usuario - PUT";
                res.enviarResponse(exchange, response);

            }

            else if ("DELETE".equals(exchange.getRequestMethod())) {

                response = "Essa e a rota de usuario - DELETE";
                res.enviarResponse(exchange, response);

            }
            else if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                exchange.close();
                return;
            }
            else {
                response = "nao definido." + "O metodo utilizado foi: " + exchange.getRequestMethod() + " So aceitamos get, put, post e delete";
                res.enviarResponse(exchange, response);
            }
        }
    }
}