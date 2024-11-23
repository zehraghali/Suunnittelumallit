import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


public class ChatApp extends Application {

    public interface ChatMediator {
        void addClient(ChatClient client);
        void sendMessage(String message, String senderUsername, String recipientUsername);
    }

    public class ConcreteChatMediator implements ChatMediator {
        private Map<String, ChatClient> clients;

        public ConcreteChatMediator() {
            clients = new HashMap<>();
        }

        @Override
        public void addClient(ChatClient client) {
            clients.put(client.getUsername(), client);
        }

        @Override
        public void sendMessage(String message, String senderUsername, String recipientUsername) {
            ChatClient recipient = clients.get(recipientUsername);
            if (recipient != null) {
                recipient.receiveMessage(message, senderUsername);
            }
        }
    }

    public class ChatClient {
        private String username;
        private ChatMediator mediator;

        public ChatClient(String username, ChatMediator mediator) {
            this.username = username;
            this.mediator = mediator;
            mediator.addClient(this);
        }

        public String getUsername() {
            return username;
        }

        public void sendMessage(String message, String recipientUsername) {
            mediator.sendMessage(message, username, recipientUsername);
        }

        public void receiveMessage(String message, String senderUsername) {
            displayMessage(message, senderUsername);
        }
    }
    private ChatMediator mediator;
    private ChatClient currentClient;

    @FXML
    private TextArea messagesTextArea;
    @FXML
    private TextField messageTextField;
    @FXML
    private TextField recipientTextField;
    @FXML
    private Button sendButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mediator = new ConcreteChatMediator();

        ChatClient client1 = new ChatClient("User1", mediator);
        ChatClient client2 = new ChatClient("User2", mediator);
        ChatClient client3 = new ChatClient("User3", mediator);

        currentClient = client1;

        VBox layout = new VBox();

        messagesTextArea = new TextArea();
        messagesTextArea.setEditable(false);
        messagesTextArea.setPrefHeight(300);

        messageTextField = new TextField();
        messageTextField.setPromptText("Type your message here...");

        recipientTextField = new TextField();
        recipientTextField.setPromptText("Enter recipient username");

        sendButton = new Button("Send Message");
        sendButton.setOnAction(event -> sendMessage());

        layout.getChildren().addAll(messagesTextArea, recipientTextField, messageTextField, sendButton);

        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setTitle(currentClient.getUsername());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void sendMessage() {
        String message = messageTextField.getText();
        String recipient = recipientTextField.getText();
        if (!message.isEmpty() && !recipient.isEmpty()) {
            currentClient.sendMessage(message, recipient);
            messageTextField.clear();
        }
    }

    public void displayMessage(String message, String senderUsername) {
        messagesTextArea.appendText(senderUsername + ": " + message + "\n");
    }
}
