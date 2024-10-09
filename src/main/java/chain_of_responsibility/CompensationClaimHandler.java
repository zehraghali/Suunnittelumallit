package chain_of_responsibility;

public class CompensationClaimHandler implements FeedbackHandler {
    private FeedbackHandler nextHandler;

    @Override
    public void setNextHandler(FeedbackHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleFeedback(Message message) {
        if (message.getMessageType() == Message.MessageType.COMPENSATION_CLAIM) {
            System.out.println("Handling compensation claim: " + message.getContent());
        } else if (nextHandler != null) {
            nextHandler.handleFeedback(message);
        }
    }
}

class DevelopmentSuggestionHandler implements FeedbackHandler {
    private FeedbackHandler nextHandler;

    @Override
    public void setNextHandler(FeedbackHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleFeedback(Message message) {
        if (message.getMessageType() == Message.MessageType.DEVELOPMENT_SUGGESTION) {
            System.out.println("Logging development suggestion: " + message.getContent());
            // Logic for logging development suggestions
        } else if (nextHandler != null) {
            nextHandler.handleFeedback(message);
        }
    }
}

class GeneralFeedbackHandler implements FeedbackHandler {
    private FeedbackHandler nextHandler;

    @Override
    public void setNextHandler(FeedbackHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleFeedback(Message message) {
        if (message.getMessageType() == Message.MessageType.GENERAL_FEEDBACK) {
            System.out.println("Analyzing general feedback: " + message.getContent());
            // Logic for analyzing general feedback
        } else if (nextHandler != null) {
            nextHandler.handleFeedback(message);
        }
    }
}
