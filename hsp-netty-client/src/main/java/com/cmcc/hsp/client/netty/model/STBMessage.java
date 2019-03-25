package com.cmcc.hsp.client.netty.model;


/**
 *
 * @param <T>
 */
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
}
