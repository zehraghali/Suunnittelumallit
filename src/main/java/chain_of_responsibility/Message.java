package chain_of_responsibility;

public class Message {
    public enum MessageType {
        COMPENSATION_CLAIM,
        CONTACT_REQUEST,
        DEVELOPMENT_SUGGESTION,
        GENERAL_FEEDBACK
    }

    private MessageType messageType;
    private String content;
    private String senderEmail;

    public Message(MessageType messageType, String content, String senderEmail) {
        this.messageType = messageType;
        this.content = content;
        this.senderEmail = senderEmail;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getContent() {
        return content;
    }

    public String getSenderEmail() {
        return senderEmail;
    }
}
