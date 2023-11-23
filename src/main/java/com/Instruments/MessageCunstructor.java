package com.Instruments;

import org.json.JSONObject;

public class MessageCunstructor {

    private String message;
    private String ip;
    private String name;
    private String recipient;
    private String content;

    /**
     *  <pre> This constructor is intended for use in the Client </pre>
     *
     *  Creates a constructor instance for subsequent
     *  preparation of messages in json format
     * @param ip String
     * @param name String
     */
    public MessageCunstructor(String ip, String name) {
        this.ip = ip;
        this.name = name;
    }


    /**
     *  <pre> This constructor is intended for use in the Server </pre>
     *
     *  <b>For the server:</b> this class breaks down the message that came from the client
     *  and provides convenient access to message elements
     * @param message String
     */
    public MessageCunstructor(String message) {

        this.message = message;
        initInputData();
    }


    private void initInputData() {

        JSONObject jsonMessage = new JSONObject(message);
        ip = jsonMessage.getString("ip");
        name = jsonMessage.getString("name");
        recipient = jsonMessage.getString("recipient");
        content = jsonMessage.getString("content");
    }

    public String prepareMessage(String recipient, String content) {

        return "{" +
                "\"ip\":\"" + ip + "\", " +
                "\"name\":\"" + name + "\", " +
                "\"recipient\":\"" + recipient + "\", " +
                 "\"content\":\"" + content + "\"}";
    }

    public String getIp() {
        return ip;
    }

    public String getName() {
        return name;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }
}