package senac.java.Domain;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Salesperson {
    int id = 0;
    public String name = "";
    public String lastName = "";
    public String phoneNumber = "";
    public String cpf = "";
    public String email = "";
    public String address = "";

    public Salesperson(){

    }

    public Salesperson(String name, String lastName, String phoneNumber, String cpf, String email, String address){

        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.cpf = cpf;
        this.email = email;
        this.address = address;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public JSONObject toJson(){
        JSONObject json = new JSONObject();

        json.put("name", name);
        json.put("lastName", lastName);
        json.put("phoneNumber", phoneNumber);
        json.put("cpf", cpf);
        json.put("email", email);
        json.put("address", address);

        return json;
    }

    public JSONObject arrayToJson(List<Salesperson> salespersonList) {
        JSONObject json = new JSONObject();

        if (!salespersonList.isEmpty()) {
            var keyJson = 0;

            for (Salesperson salesperson : salespersonList) {

                JSONObject jsonFor = new JSONObject();



                jsonFor.put("name", salesperson.getName());
                jsonFor.put("lastName", salesperson.getLastName());
                jsonFor.put("cpf", salesperson.getCpf());
                jsonFor.put("email", salesperson.getEmail());
                jsonFor.put("phoneNumber", salesperson.getPhoneNumber());
                jsonFor.put("address", salesperson.getAddress());


                json.put(String.valueOf(keyJson), jsonFor);

                keyJson++;

                System.out.println(salesperson.getName());



            }
            return json;
        }

        else{

            return null;
        }

    }

    public static Salesperson getSalesperson(int index, List<Salesperson> salespersonList){

        if(index >= 0 && index < salespersonList.size())  {

            return salespersonList.get(index);

        }

        else{
            return null;
        }
    }

    public static List<Salesperson> getAllSalesperson(List<Salesperson> salespersonList){
        return salespersonList;
    }
}
