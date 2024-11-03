package proxy;

import java.util.HashMap;
import java.util.Map;

class AccessDeniedException extends Exception {
    public AccessDeniedException(String message) {
        super(message);
    }
}

interface DocumentInterface {
    String getContent() throws AccessDeniedException;
    String getCreationDate();
    String getId();
}

class Document implements DocumentInterface {
    private final String id;
    private final String creationDate;
    private final String content;

    public Document(String id, String creationDate, String content) {
        this.id = id;
        this.creationDate = creationDate;
        this.content = content;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getCreationDate() {
        return creationDate;
    }

    @Override
    public String getContent() {
        return content;
    }
}

class DocumentProxy implements DocumentInterface {
    private final Document document;
    private final AccessControlService accessControlService;

    public DocumentProxy(Document document) {
        this.document = document;
        this.accessControlService = AccessControlService.getInstance();
    }

    @Override
    public String getContent() throws AccessDeniedException {
        String username = UserContext.getCurrentUser().getUsername();
        if (accessControlService.isAllowed(document.getId(), username)) {
            return document.getContent();
        } else {
            throw new AccessDeniedException("Pääsy kielletty: " + username);
        }
    }

    @Override
    public String getCreationDate() {
        return document.getCreationDate();
    }

    @Override
    public String getId() {
        return document.getId();
    }
}

class AccessControlService {
    private static AccessControlService instance;
    private final Map<String, String> accessMap;

    private AccessControlService() {
        accessMap = new HashMap<>();
    }

    public static AccessControlService getInstance() {
        if (instance == null) {
            instance = new AccessControlService();
        }
        return instance;
    }

    public void grantAccess(String username, String documentId) {
        accessMap.put(username, documentId);
    }

    public boolean isAllowed(String documentId, String username) {
        return accessMap.containsKey(username) && accessMap.get(username).equals(documentId);
    }
}

class User {
    private final String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

class UserContext {
    private static User currentUser;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}

class Library {
    private final Map<String, DocumentInterface> documents;

    public Library() {
        documents = new HashMap<>();
    }

    public void addDocument(DocumentInterface document) {
        documents.put(document.getId(), document); // Käytetään ID:tä avaimena
    }

    public DocumentInterface getDocument(String id) {
        return documents.get(id);
    }

    public void addProtectedDocument(String id, String creationDate, String content) {
        Document doc = new Document(id, creationDate, content);
        DocumentProxy proxy = new DocumentProxy(doc);
        addDocument(proxy);
    }
}

class Main {
    public static void main(String[] args) {
        Library library = new Library();
        AccessControlService accessControlService = AccessControlService.getInstance();

        User user1 = new User("käyttäjä1");
        User user2 = new User("käyttäjä2");

        library.addProtectedDocument("doc1", "2024-11-01", "Suojattu sisältö 1");
        library.addProtectedDocument("doc2", "2024-11-02", "Suojattu sisältö 2");

        accessControlService.grantAccess("käyttäjä1", "doc1");

        UserContext.setCurrentUser(user1);
        try {
            DocumentInterface doc = library.getDocument("doc1");
            System.out.println("Käyttäjän 1 sisältö: " + doc.getContent());
        } catch (AccessDeniedException e) {
            System.out.println(e.getMessage());
        }

        UserContext.setCurrentUser(user2);
        try {
            DocumentInterface doc = library.getDocument("doc1");
            System.out.println("Käyttäjän 2 sisältö: " + doc.getContent());
        } catch (AccessDeniedException e) {
            System.out.println(e.getMessage());
        }
    }
}
