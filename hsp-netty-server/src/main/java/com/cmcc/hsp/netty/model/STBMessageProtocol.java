package com.cmcc.hsp.netty.model;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class STBMessageProtocol {

    Gson gson = new Gson();
    /**
     * 消息的开头的标志
     */
    private int head_data = ConstantValue.HEAD_DATA;

    /**
     * 消息的长度
     */
    private int contentLength;

    /**
     * 消息的内容
     */
    private byte[] content;


    public STBMessageProtocol(STBMessage stbMessage) {
        String stbMessageStr =  gson.toJson(stbMessage);
        log.info("stbMessageStr:"+stbMessageStr);
        content = stbMessageStr.getBytes();
        contentLength = content.length;
    }

    public STBMessageProtocol(int contentLength, byte[] content) {
        this.contentLength = contentLength;
        this.content = content;
    }


    public int getHead_data() {
        return head_data;
    }

    public void setHead_data(int head_data) {
        this.head_data = head_data;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getContentToJson() {
        String contentStr = new String(content);
        return contentStr;
    }

}
