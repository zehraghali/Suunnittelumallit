package chain_of_responsibility;

public class ContactRequestHandler implements FeedbackHandler {
    private FeedbackHandler nextHandler;

    @Override
    public void setNextHandler(FeedbackHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleFeedback(Message message) {
        if (message.getMessageType() == Message.MessageType.CONTACT_REQUEST) {
            System.out.println("Forwarding contact request: " + message.getContent());
            // Logic for forwarding contact requests
        } else if (nextHandler != null) {
            nextHandler.handleFeedback(message);
        }
    }
}
