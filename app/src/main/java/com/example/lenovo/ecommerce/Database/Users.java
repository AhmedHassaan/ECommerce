package com.example.lenovo.ecommerce.Database;

import android.content.Context;
import android.content.SharedPreferences;

public class Users {
    private Context context;
    private SharedPreferences.Editor set;
    private SharedPreferences get;
    private String de = "N/A";

    public Users(Context context){
        this.context = context;
        get = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        set = context.getSharedPreferences("user",context.MODE_PRIVATE).edit();
    }

    public void registerUser(String username,String password){
        set.putString(username,password);
        set.putBoolean("login",true);
        set.commit();
    }

    public boolean checkUser(String username,String password){
        boolean exist = false;
        if(get.contains(username))
            if(get.getString(username,de).equalsIgnoreCase(password))
                exist = true;
        return exist;
    }

    public void setLogin(String username){
        set.putBoolean("login",true);
        set.putString("username",username);
        set.commit();
    }

    public void setLogout(){
        set.remove("username");
        set.putBoolean("login",false);
        set.commit();
    }

    public String getCurrentUsername(){
        return get.getString("username",de);
    }

    public boolean alreadyExist(String username){
        return get.contains(username);
    }
}
