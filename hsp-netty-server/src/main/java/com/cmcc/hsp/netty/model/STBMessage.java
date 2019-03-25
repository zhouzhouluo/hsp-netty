package com.cmcc.hsp.netty.model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class STBMessage<T> {

    private STBHeader stbHeader;

    private T data;


    public STBMessage() {

    }

    public STBMessage(STBHeader stbHeader, T data) {
        this.stbHeader = stbHeader;
        this.data = data;
    }

    public STBHeader getStbHeader() {
        return stbHeader;
    }

    public void setStbHeader(STBHeader stbHeader) {
        this.stbHeader = stbHeader;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public <K> K fromJson(Class<K> classOfT){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        K k = gson.fromJson(data.toString(),classOfT);
        return k;
    }

}
