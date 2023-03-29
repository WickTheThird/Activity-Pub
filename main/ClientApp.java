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

    User user;
    ActivityStream userActivity;
    InputStream userInstr;
    OutputStream userOutstr;

    // init and testing
    public static void main(String... args) {
        ClientApp app = new ClientApp();
        app.clientApp("test");
        app.demo();
    }

    public void clientApp(String name) {
        this.user = new User();
        this.userActivity = new ActivityStream();
        this.userInstr = new InputStream();
        this.userOutstr = new OutputStream();
    }

    // constructors
    public String demo() {

        ClientApp app1 = new ClientApp();
        ClientApp app2 = new ClientApp();

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
