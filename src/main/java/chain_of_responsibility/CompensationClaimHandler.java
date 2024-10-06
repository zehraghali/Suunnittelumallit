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

public class DevelopmentSuggestionHandler implements FeedbackHandler {
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

public class GeneralFeedbackHandler implements FeedbackHandler {
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
