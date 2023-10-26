package senac.java.Domain;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Products {
    int id = 0;
    public String pName = "";
    public String pPrice = "";
    public String pColor = "";
    public String pDescription = "";
    public String pQuantity = "";
    public String pImg = "";


    public Products(){

    }

    public Products(String pName, String pPrice, String pColor, String pDescription, String pQuantity, String img){

        this.pName = pName;
        this.pPrice = pPrice;
        this.pColor = pColor;
        this.pDescription = pDescription;
        this.pQuantity = pQuantity;
        this.pImg = img;
    }

    public String getPName(){
        return pName;
    }

    public void setPName(String pName){
        this.pName = pName;
    }

    public String getPPrice(){
        return pPrice;
    }

    public void setPPrice(String pPrice){
        this.pPrice = pPrice;
    }

    public String getPColor(){
        return pColor;
    }

    public void setPColor(String pColor){
        this.pColor = pColor;
    }

    public String getPDescription(){
        return pDescription;
    }

    public void setPDescription(String pDescription){
        this.pDescription = pDescription;
    }

    public String getPQuantity(){
        return pQuantity;
    }

    public void setPQuantity(String pQuantity){
        this.pQuantity = pQuantity;
    }

    public String getPImg(){
        return pImg;
    }

    public void setPImg(String pImg){
        this.pImg = pImg;
    }



    public JSONObject toJson(){
        JSONObject json = new JSONObject();

        json.put("name",pName);
        json.put("price", pPrice);
        json.put("color", pColor);
        json.put("description", pDescription);
        json.put("quantity", pQuantity);
        json.put("img", pImg);

        return json;
    }

    public JSONObject arrayToJson(List<Products> productsList) {
        JSONObject json = new JSONObject();

        if (!productsList.isEmpty()) {
            var keyJson = 0;

            for (Products products : productsList) {

                JSONObject jsonFor = new JSONObject();



                jsonFor.put("name", products.getPName());
                jsonFor.put("price", products.getPPrice());
                jsonFor.put("color", products.getPColor());
                jsonFor.put("description", products.getPDescription());
                jsonFor.put("quantity", products.getPQuantity());
                jsonFor.put("img", products.getPImg());


                json.put(String.valueOf(keyJson), jsonFor);

                keyJson++;

                System.out.println(products.getPName());



            }
            return json;
        }

        else{

            return null;
        }

    }

    public static Products getProducts(int index, List<Products> productsList){

        if(index >= 0 && index < productsList.size())  {

            return productsList.get(index);

        }

        else{
            return null;
        }
    }

    public static List<Products> getAllProducts(List<Products> productsList){
        return productsList;
    }
}

