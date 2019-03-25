package com.cmcc.hsp.client.netty.model;

/**
 *
 */
public class STBHeader {

    private String clientUuId;

    private String clientSessenId;

    private String command;

    public String getClientUuId() {
        return clientUuId;
    }

    public void setClientUuId(String clientUuId) {
        this.clientUuId = clientUuId;
    }

    public String getClientSessenId() {
        return clientSessenId;
    }

    public void setClientSessenId(String clientSessenId) {
        this.clientSessenId = clientSessenId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
