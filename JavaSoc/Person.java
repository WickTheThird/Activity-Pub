package activitypub;
import java.util.*;

//> the Person actor has acces to the activity objects and utilizes them in par with the activty types aka activity streams
//? those are handled by their respective classes, but are set here...
//? they are managed by the inbox and outbox classes which each person actor should have
//> i will also be using an array of followers and following
class Person implements App {

    //? the following are the ones that i have observed from the spec (this also included the demp and w3);
    String URI;
    String name;
    String prefferedUsername;
    String summary;
    ActivityStreams currentActivity;
    InboxMan inbox = new InboxMan();
    OutboxMan outbox = new OutboxMan();
    List <Person> followers = new ArrayList<Person>();
    List <Person> following = new ArrayList<Person>();

    //> Constructor (apparently java does not support default values for parameters, so i will have to do this with setters)
    //? the above is refering to the demo on how when a person is created only their name and uri is set, thus the rest come after
    Person(String URI, String name){
        this.URI = URI;
        this.name = name;

        System.out.println("Person created" + "\n" + "URI: " + this.URI + "\n" + "Name: " + this.name);

    }

    //> this is a continuation of the obs above
    void setPrefferedUsername(String prefferedUsername){
        this.prefferedUsername = prefferedUsername;
    }

    void setSummary(String summary){
        this.summary = summary;
    }

    //> getters and setters
    public Outbox getOutbox(){
        return this.outbox;
    }

    public Inbox getInbox(){
        return this.inbox;
    }

    public String getURI(){
        return this.URI;
    }

    public String getName(){
        return this.name;
    }

    public String getPrefferedUsername(){
        return this.prefferedUsername;
    }

    public String getSummary(){
        return this.summary;
    }

    public ActivityStreams getCurrentActivity(){
        return this.currentActivity;
    }

    public void setCurrentActivity(ActivityStreams currentActivity){
        this.currentActivity = currentActivity;
    }

    //> implementing the activity streams
    //? this is the follow activity
    void follow(Person actor, Person target, String summary, String uri){
        Follow followActivity = new Follow(uri, summary, actor, target);
        
        if(outbox.send(followActivity)) {
            System.out.println("Follow activity sent");
        } else {
            System.out.println("Follow activity not sent");
        }
    }

    //? this is the unfollow activity
    void unfollow(Person actor, Person target, String summary, String uri){
        Unfollow unfollowActivity = new Unfollow(uri, summary, actor, target);
        
        if(outbox.send(unfollowActivity)) {
            System.out.println("Unfollow activity sent");
        } else {
            System.out.println("Unfollow activity not sent");
        }
    }

    //? this is the like activity
    void like(Person actor, Person target, String summary, String uri){
        Like likeActivity = new Like(uri, summary, actor, target);
        
        if(outbox.send(likeActivity)) {
            System.out.println("Like activity sent");
        } else {
            System.out.println("Like activity not sent");
        }
    }

    //? this is the unlike activity
    void unlike(Person actor, Person target, String summary, String uri){
        Unlike unlikeActivity = new Unlike(uri, summary, actor, target);
        
        if(outbox.send(unlikeActivity)) {
            System.out.println("Unlike activity sent");
        } else {
            System.out.println("Unlike activity not sent");
        }
    }

    //? this is the delete activity
    void delete(String uri, String summary, Person actor, Person target, StreamObject object, boolean state) {
        Delete deleteActivity = new Delete(uri, summary, actor, target, object, state);
        
        if(outbox.send(deleteActivity)) {
            System.out.println("Delete activity sent");
        } else {
            System.out.println("Delete activity not sent");
        }
    }

    //? this is the create activity
    void create(String uri, String summary, Person actor, Person target, StreamObject object, boolean state) {
        Create createActivity = new Create(uri, summary, actor, target, object, state);
        
        if(outbox.send(createActivity)) {
            System.out.println("Create activity sent");
        } else {
            System.out.println("Create activity not sent");
        }
    }

    //> implementing the inbox and outbox

    //> inbox (reading all activities recieved in the inbox) returning if it read it or not (true/false)
    public boolean readInbox() {
        boolean hasRead = false;
        for (ActivityStreams toread = inbox.readNext(); toread != null; toread = inbox.readNext()) {
            System.out.println(this.URI + " read: " + toread.getURI());
            // if it's a follow, unfollow, we need to do some more
            hasRead = true;
        }
        return hasRead;
    }

    //> outbox activities need are sent to the inbox of the target actor
    //> this ensures that all the activities are waiting in the user's outbox are proprely sent to the target actor
    //> so that they can ve viewed responded to or shared as needed... this will remain in the targets inbox until they delete it or otherwise
    public boolean setOutbox() {
        boolean hasDelivered = false;
        for (ActivityStreams todeliver = outbox.deliverNext(); todeliver != null; todeliver = outbox.deliverNext()) {
            if (todeliver.userStates) {
                for (Person followTarget : followers) {
                    followTarget.inbox.receive(todeliver);
                    hasDelivered = true;
                }
            }
            todeliver.target.inbox.receive(todeliver);
            hasDelivered = true;
            System.out.println(todeliver.getURI() + " left " + this.URI + "'s outbox:");
        }
        return hasDelivered;
    }

    //> this was inside app interface (im not using it, client app is)
    public String demo() {
        return "";
    }

    //> this is the created call
    public String toString() {
        return "Created Person: " + this.name + " (" + this.URI + ")";
    }

}

