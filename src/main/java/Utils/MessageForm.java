package Utils;

import org.json.JSONObject;

public class MessageForm {

    /**
     * <h2>for Server:</h2>
     * <b>The class provides getters for:</b>
     * <li>userName</li>
     * <li>userIp</li>
     * <li>messageRecipient</li>
     * <li>textMessage</li>
     *
     * <h2>for Client:</h2>
     * <h4>method for preparing a message using a template for sending</h4>
     *
     * @see MessageForm#prepareMessage_toSend(String, String)
     */
    private String message;
    private String clientIp;
    private String clientName;
    private String messageRecipient;
    private String textMessage;

    /**
     * <pre> This constructor is intended for use in the Client </pre>
     * <p>
     * Creates a constructor instance for subsequent
     * preparation of messages in json format
     *
     * @param clientIp   String
     * @param clientName String
     */
    public MessageForm(String clientIp, String clientName) {
        this.clientIp = clientIp;
        this.clientName = clientName;
    }

    /**
     * <pre> This constructor is intended for use in the Server </pre>
     *
     * <b>For the server:</b> this class breaks down the message that came from the client
     * and provides convenient access to message elements
     *
     * @param message String
     */
    public MessageForm(String message) {

        this.message = message;
        initInputData();
    }

    public String prepareMessage_toSend(String recipient, String content) {

        return "{" +
                "\"ip\":\"" + clientIp + "\", " +
                "\"name\":\"" + clientName + "\", " +
                "\"recipient\":\"" + recipient + "\", " +
                "\"content\":\"" + content + "\"}";
    }

    public String get_clientIp() {
        return clientIp;
    }

    public String get_clientName() {
        return clientName;
    }

    public String get_recipientIp() {
        return messageRecipient;
    }

    public String get_textMessage() {
        return textMessage;
    }

    private void initInputData() {

        JSONObject jsonMessage = new JSONObject(message);
        clientIp = jsonMessage.getString("ip");
        clientName = jsonMessage.getString("name");
        messageRecipient = jsonMessage.getString("recipient");
        textMessage = jsonMessage.getString("content");
    }
}
