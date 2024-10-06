package chain_of_responsibility;

public class Main {
    public static void main(String[] args) {

        FeedbackHandler compensationHandler = new CompensationClaimHandler();
        FeedbackHandler contactHandler = new ContactRequestHandler();
        FeedbackHandler suggestionHandler = new DevelopmentSuggestionHandler();
        FeedbackHandler generalFeedbackHandler = new GeneralFeedbackHandler();

        compensationHandler.setNextHandler(contactHandler);
        contactHandler.setNextHandler(suggestionHandler);
        suggestionHandler.setNextHandler(generalFeedbackHandler);

        Message compensationClaim = new Message(Message.MessageType.COMPENSATION_CLAIM,
                "Requesting compensation for damaged product.",
                "customer@example.com");

        Message contactRequest = new Message(Message.MessageType.CONTACT_REQUEST,
                "I would like to get in touch with support.",
                "customer@example.com");

        Message developmentSuggestion = new Message(Message.MessageType.DEVELOPMENT_SUGGESTION,
                "Please consider adding dark mode.",
                "customer@example.com");

        Message generalFeedback = new Message(Message.MessageType.GENERAL_FEEDBACK,
                "Great service, very satisfied!",
                "customer@example.com");

        compensationHandler.handleFeedback(compensationClaim);
        compensationHandler.handleFeedback(contactRequest);
        compensationHandler.handleFeedback(developmentSuggestion);
        compensationHandler.handleFeedback(generalFeedback);
    }
}
