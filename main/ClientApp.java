package activitypub;

interface Activity {
    String getURI();
}

// InStream
interface ReceiveMessage {
    boolean receive(Activity activity);
}

interface ReadNextMessage {
    Activity readNext();
}

interface Inbox extends ReceiveMessage, ReadNextMessage {
    int getCount();
}

// OutStream
interface SendMessage {
    boolean send(Activity activity);
}

interface DeliverNextMessage {
    Activity deliverNext();
}

interface Outbox extends SendMessage, DeliverNextMessage {
    int getCount();
}

// Linker between in and out
interface App {
    Inbox getInbox();
    Outbox getOutbox();
    String demo();
}

/*
* Client app main class aka parent class.
*/

public class ClientApp implements App {

    // init and testing
    public static void main(String... args) {
        ClientApp app = new ClientApp();
        app.demo();
    }

    public void clientApp(String name) {
        ClientApp userInstr = new InputStream();
        ClientApp userOutstr = new OutputStream();
        ClientApp userActivity = new ActivityStream();
    }

    // constructors
    public String demo() {
        return "hello";
    }

    // getters
    public Inbox getInbox() {
        return null;
    }

    public Outbox getOutbox() {
        return null;
    }

    public int getCount() {
        return 0;
    }

    // setters

}
