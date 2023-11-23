package com.Instruments;

import org.json.JSONObject;

public class MessageConstructor {

    private String message;
    private String clientIp;
    private String clientName;
    private String recipient;
    private String content;

    /**
     *  <pre> This constructor is intended for use in the Client </pre>
     *
     *  Creates a constructor instance for subsequent
     *  preparation of messages in json format
     * @param clientIp String
     * @param clientName String
     */
    public MessageConstructor(String clientIp, String clientName) {
        this.clientIp = clientIp;
        this.clientName = clientName;
    }

    /**
     *  <pre> This constructor is intended for use in the Server </pre>
     *
     *  <b>For the server:</b> this class breaks down the message that came from the client
     *  and provides convenient access to message elements
     * @param message String
     */
    public MessageConstructor(String message) {

        this.message = message;
        initInputData();
    }


    private void initInputData() {

        JSONObject jsonMessage = new JSONObject(message);
        clientIp = jsonMessage.getString("ip");
        clientName = jsonMessage.getString("name");
        recipient = jsonMessage.getString("recipient");
        content = jsonMessage.getString("content");
    }

    public String prepareMessage(String recipient, String content) {

        return "{" +
                "\"ip\":\"" + clientIp + "\", " +
                "\"name\":\"" + clientName + "\", " +
                "\"recipient\":\"" + recipient + "\", " +
                 "\"content\":\"" + content + "\"}";
    }

    public String getClientIp() {
        return clientIp;
    }

    public String getClientName() {
        return clientName;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }
}