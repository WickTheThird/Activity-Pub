package activitypub;
import java.util.*;

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

public class ClientApp implements App {
     //? this is the list of users
     List <Person> users = new ArrayList<>();

    //> init and testing
    public static void main(String... args) {
        ClientApp app = new ClientApp();
        app.demo();
    }

    //> Constructors
    public String demo() {
        
        //?statements about most of the activity objects
        //> creating some users
        Person user1 = new Person("https://example.com/users/1", "user1");
        Person user2 = new Person("https://example.com/users/2", "user2");
        Person user3 = new Person("https://example.com/users/3", "user3");

        //? adding them to uesers
        users.add(user1);
        users.add(user2);
        users.add(user3);

        //> creating some stream objects
        Article article1 = new Article("https://example.com/articles/1", "article1", "article", "THIS IS A LONG ARTICLE", user1);
        Image image1 = new Image("image", "sending image", "https://example.com/images/1", new Url("Image", "https://example.com/images/1", "png"));

        article1.Summary();
        image1.Summary();

        // create follow activities
        Follow follow1 = new Follow("http://example.com/follow/user2", "Follow user2", user1, user2);
        Follow follow2 = new Follow("http://example.com/follow/user3", "Follow user3", user2, user3);

        // call the creation method to display the activities
        follow1.Creation();
        follow2.Creation();

        Setter();
        Receiver();

        //> creating some activities
        user1.create("http://example.com/created/article/1", "this is a new article", user1, user2,  article1, true);
        user1.create("http://example.com/created/image/1", "this is a new article", user1, user3, article1, true);

        Setter();
        Receiver();

        return "done";

    }

    // getters --> not useful
    public Outbox getOutbox() {
        return null;
    }
    public Inbox getInbox() {
        return null;
    }

    //> this is the method that will be used to send messages and activities and clear them from the outbox
    public void Setter(){
        int i = 0;
        while(i < users.size()){
            users.get(i).setOutbox();
            i++;
        }
    }

    public void Receiver(){
        int i = 0;
        while(i < users.size()){
            users.get(i).readInbox();
            i++;
        }
    }



}
