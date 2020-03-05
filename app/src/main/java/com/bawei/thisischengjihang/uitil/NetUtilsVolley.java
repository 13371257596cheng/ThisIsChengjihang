package com.bawei.thisischengjihang.uitil;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.thisischengjihang.base.App;

import java.util.HashMap;
import java.util.Map;

public class NetUtilsVolley {
    private final RequestQueue requestQueue;

    private NetUtilsVolley(){
        requestQueue = Volley.newRequestQueue(App.getAppction());
    }
    private static class Simapterncter{
        private static final NetUtilsVolley STARTCONT=new NetUtilsVolley();
    }
    public static NetUtilsVolley getInstance(){
        return Simapterncter.STARTCONT;
    }
    public interface Collack{
        void onSuccess(String json);
        void onFialuse(String msg);
    }
    public void doGet(String str, final Collack collack){
        StringRequest stringRequest = new StringRequest(str, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                collack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                collack.onFialuse(error.getMessage());
            }
        });requestQueue.add(stringRequest);
    }
    public void doPost(String str, final HashMap<String,String> map, final Collack collack){
        StringRequest stringRequest = new StringRequest(str, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                collack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                collack.onFialuse(error.getMessage());
            }
        }) {
            @Override
            protected Map getParams() {
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
