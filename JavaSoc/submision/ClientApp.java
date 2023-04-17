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

        return "";

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


class ActivityStreams implements Activity{

    //> we shall need the following: uri, object, actor, target, summary and share; 
    String uri;
    StreamObject object;
    Person actor;
    Person target;
    String summary;
    boolean userStates; //? default state


    ActivityStreams(String uri, StreamObject object, Person actor, Person target, String summary, boolean userStates){
        this.uri = uri;
        this.object = object;
        this.actor = actor;
        this.target = target;
        this.summary = summary;
        this.setUserStates(false);
    }

    ActivityStreams(String uri, String summary, Person actor, Person target) {
        this.uri = uri;
        this.summary = summary;
        this.actor = actor;
        this.target = target;
    }

    // getters
    public String getURI(){
        return this.uri;
    }

    public StreamObject getObject(){
        return this.object;
    }

    public Person getActor(){
        return this.actor;
    }

    public Person getTarget(){
        return this.target;
    }

    public String getSummary(){
        return this.summary;
    }

    public boolean getUserStates(){
        return this.userStates;
    }

    // setters

    public void setURI(String uri){
        this.uri = uri;
    }

    public void setObject(StreamObject object){
        this.object = object;
    }

    public void setActor(Person actor){
        this.actor = actor;
    }

    public void setTarget(Person target){
        this.target = target;
    }

    public void setSummary(String summary){
        this.summary = summary;
    }

    public void setUserStates(boolean state){
        this.userStates = state;
    }

    //> This shows the content of the activity
    public void Creation() {

        System.out.println("Activity Created\n");
        System.out.println("URI: " + getURI());
        System.out.println("Summary: " + getSummary());
        System.out.println("Actor: " + getActor().getName());
        System.out.println("Target: " + getTarget().getName());

        System.out.println("Object: " + getObject().getURI());



    }

}

class Follow extends ActivityStreams{

    //> Constructor
    Follow(String uri, String summary, Person actor, Person target){
        super(uri, summary, actor, target);
    }

    //> This shows the content of the activity
    public void Creation() {

        System.out.println("Activity Created\n");
        System.out.println("URI: " + getURI());
        System.out.println("Summary: " + getSummary());
        System.out.println("Actor: " + getActor().getName());
        System.out.println("Target: " + getTarget().getName());

    }

}

class Unfollow extends ActivityStreams{

    //> Constructor
    Unfollow(String uri, String summary, Person actor, Person target){
        super(uri, summary, actor, target);
    }

    //> This shows the content of the activity
    public void Creation() {

        System.out.println("Activity Created\n");
        System.out.println("URI: " + getURI());
        System.out.println("Summary: " + getSummary());
        System.out.println("Actor: " + getActor().getName());
        System.out.println("Target: " + getTarget().getName());

    }

}

class Like extends ActivityStreams{

    //> Constructor
    Like(String uri, String summary, Person actor, Person target){
        super(uri, summary, actor, target);
    }

    //> This shows the content of the activity
    public void Creation() {

        System.out.println("Activity Created\n");
        System.out.println("URI: " + getURI());
        System.out.println("Summary: " + getSummary());
        System.out.println("Actor: " + getActor().getName());
        System.out.println("Target: " + getTarget().getName());

    }

}

class Unlike extends ActivityStreams{

    //> Constructor
    Unlike(String uri, String summary, Person actor, Person target){
        super(uri, summary, actor, target);
    }

    //> This shows the content of the activity
    public void Summary() {

        System.out.println("Activity Created\n");
        System.out.println("URI: " + getURI());
        System.out.println("Summary: " + getSummary());
        System.out.println("Actor: " + getActor().getName());
        System.out.println("Target: " + getTarget().getName());

    }

}

class Create extends ActivityStreams { //> needs object and userState

    //> Constructor
    Create(String uri, String summary, Person actor, Person target, StreamObject object, boolean userStates){
        super(uri, object, actor, target, summary, userStates);
    }

    //> This shows the content of the activity
    public void Creation() {

        System.out.println("Activity Created\n");
        System.out.println("URI: " + getURI());
        System.out.println("Summary: " + getSummary());
        System.out.println("Actor: " + getActor().getName());
        System.out.println("Target: " + getTarget().getName());
        System.out.println("Object: " + getObject().getURI());
        System.out.println("UserState: " + getUserStates());

    }

}

class Delete extends ActivityStreams { //> needs object and userState
    //> Constructor
    Delete(String uri, String summary, Person actor, Person target, StreamObject object, boolean userState){
        super(uri, object, actor, target, summary, userState);
    }

    //> This shows the content of the activity
    public void Creation() {

        System.out.println("Activity Created\n");
        System.out.println("URI: " + getURI());
        System.out.println("Summary: " + getSummary());
        System.out.println("Actor: " + getActor().getName());
        System.out.println("Target: " + getTarget().getName());
        System.out.println("Object: " + getObject().getURI());
        System.out.println("UserState: " + getUserStates());

    }

}

class InboxMan implements Inbox {

    // Inbox is said to be a collection of activities
    Queue<ActivityStreams> inbox = new LinkedList<>();

    // ReadNext acts a lot like a queue
    @Override
    public ActivityStreams readNext(){
        return inbox.poll();
    }

    // Receive is said to be a collection of activities
    public boolean receive(Activity activity){

        inbox.add((ActivityStreams) activity);
        return true;
    }

    // Get count is said to be a collection of activities
    public int getCount(){
        return inbox.size();
    }
}

// Outbox is said to be a collection of activities (due to be sent)
// DeliverNext acts a lot like a queue
class OutboxMan implements Outbox {
    // Outbox is said to be a collection of activities (due to be sent)
    Queue<ActivityStreams> outbox = new LinkedList<>();

    // DeliverNext acts a lot like a queue
    @Override
    public ActivityStreams deliverNext(){
        return outbox.poll();
    }

    // Send is said to be a collection of activities (due to be sent)
    public boolean send(Activity activity){
        outbox.add((ActivityStreams) activity);
        return true;
    }

    // Get count is said to be a collection of activities (due to be sent)
    public int getCount(){
        return outbox.size();
    }
}

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


//> Stream Object generates the type of objects that the users will be able to interact with

//? this is a parent class, the part that unifies all the objects
abstract class StreamObject {
    //> based on the spec given, an object must containt: URI, Name, Type
    //> the content of these will be added via activity streams, this will initiate the objects

    String URI;
    String Name;
    String Type;

    //> Constructor
    StreamObject(String URI, String Name, String Type) {
        this.URI = URI;
        this.Name = Name;
        this.Type = Type;
    }

    //? getters --> just in case ill need them
    public String getURI() {
        return URI;
    }

    public String getName() {
        return Name;
    }

    public String getType() {
        return Type;
    }

    //? setters --> just in case ill need them
    public void setURI(String URI) {
        this.URI = URI;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    //> This prints out the process
    public String Creation() {
        return "New Stream Object created with the following:" + "URI: " + URI + " Name: " + Name + " Type: " + Type + "\n";
    }

}

//? based on https://www.w3.org/TR/activitystreams-vocabulary/#object-types
//> I need the following activities: RelationShip, Article, Document, Audio, Image, Video, Page, Note, Event, Place, Mention, Profile;
//! I will not be implementing tombstone
//! Mention will is part of Link object, thus thats where you will find it

class RelationShip extends StreamObject {
    //> based on the spec a relatiobship object needs the following: Subject, Object, State, Type (we will supper together with the URI and Name), Summary
    Person subject;
    Person object;
    String state;

    //> Constructor
    RelationShip(String URI, String Name, String Type, Person subject, Person object, String state) {
        super(URI, Name, Type);
        this.subject = subject;
        this.object = object;
        this.state = state;
    }

    //> This shows the content of the relationship --> this will be used to show the relationship between the two people
    public void Summary() {
        System.out.println(Creation());
        System.out.println("The relationship between " + subject.getName() + " and " + object.getName() + " is " + state);
    }

}

class Article extends StreamObject {
    //> based on the spec provided the Article has the following propreties: type, name, content, attrubutedTo;
    String content;
    Person attributedTo;

    //> Constructor
    Article(String URI, String Name, String Type, String content, Person attributedTo) {
        super(URI, Name, Type);
        this.content = content;
        this.attributedTo = attributedTo;
    }

    //> This shows the content of the article
    public void Summary() {
        System.out.println(Creation());
        System.out.println("The article " + Name + " was written by " + attributedTo.getName() + " and it says: \n" + content);
    }

}

class Note extends StreamObject {
    //> based on the spec provided the Note has the following propreties: type, name, content;

    String content;

    //> Constructor
    Note(String URI, String Name, String Type, String content) {
        super(URI, Name, Type);
        this.content = content;
    }

    //> This shows the content of the note
    public void Summary() {
        System.out.println(Creation());
        System.out.println("The note " + Name + " says: \n" + content);
    }

}

class Event extends StreamObject {
    //> based on the spec provided the Event has the following propreties: type, name, startTime, endTime, location, summary, actor, object;
    String startTime;
    String endTime;
    String location;
    String summary;
    Person actor;
    StreamObject object;

    //> Constructor
    Event(String URI, String Name, String Type, String startTime, String endTime, String location, String summary, Person actor,
            StreamObject object) {
        super(URI, Name, Type);
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.summary = summary;
        this.actor = actor;
        this.object = object;
    }

    //> This shows the content of the event
    public void Summary() {
        System.out.println(Creation());
        System.out.println("The event " + Name + " will take place at " + location + " from " + startTime + " to " + endTime
                + " and it will be hosted by " + actor.getName() + " and it will be about " + object.getName());
    }

}

class Place extends StreamObject {
    //> based on the spec provided the Place has the following propreties: type, name, latitude, longitude, accuracy, altitude, altitudeAccuracy, radius, units;
    String latitude;
    String longitude;
    String accuracy;
    String altitude;
    String altitudeAccuracy;
    String radius;
    String units;

    //> Constructor
    Place(String URI, String Name, String Type, String latitude, String longitude, String accuracy, String altitude,
            String altitudeAccuracy, String radius, String units) {
        super(URI, Name, Type);
        this.latitude = latitude;
        this.longitude = longitude;
        this.accuracy = accuracy;
        this.altitude = altitude;
        this.altitudeAccuracy = altitudeAccuracy;
        this.radius = radius;
        this.units = units;
    }

    //> This shows the content of the place
    public void Summary() {
        System.out.println(Creation());
        System.out.println("The place " + Name + " is located at " + latitude + " and " + longitude + " and it has an accuracy of "
                + accuracy + " and an altitude of " + altitude + " and an altitude accuracy of " + altitudeAccuracy
                + " and a radius of " + radius + " and the units are " + units);
    }

}

class Profile extends StreamObject {
    //> based on the spec provided the profile has the following propreties: summary, type, name, uri, describes;
    String summary;
    Person describes;

    //> Constructor
    Profile(String URI, String Name, String Type, String summary, Person describes) {
        super(URI, Name, Type);
        this.summary = summary;
        this.describes = describes;
    }

    //> This shows the content of the profile --> acts like a descriptor
    public void Summary() {
        System.out.println(Creation());
        System.out.println("The profile " + Name + " is about " + describes.getName() + " and it says: \n" + summary);
    }

}

//> based on https://www.w3.org/TR/activitystreams-vocabulary/#actor-types the document should act as a parent class for the following: Audio, Image, Video, Page, 
class Document extends StreamObject {
    Url url;

    //> based on the spec all the things we need are already in the parent class, except for URL to the document;
    Document(String URI, String Name, String Type, Url url) {
        super(URI, Name, Type);
        this.url = url;
    }

    //> Shows document
    public void Summary() {
        System.out.println(Creation());
        System.out.println("URL:" + this.url);
    }

}

class Audio extends Document {

    //> based on the spec all the things we need are already in the parent class, except for URL to the document;
    Audio(String URI, String Name, String Type, Url url) {
        super(URI, Name, Type, url);
    }

    //> Shows audio
    public void Summary() {
        System.out.println(Creation());
        System.out.println("URL:" + this.url);
    }

}

class Image extends Document {

    //> based on the spec all the things we need are already in the parent class, except for URL to the document;
    Image(String URI, String Name, String Type, Url url) {
        super(URI, Name, Type, url);
    }

    //> Shows image
    public void Summary() {
        System.out.println(Creation());
        System.out.println("URL:" + this.url);
    }

}

class Video extends Document {

    //> based on the spec all the things we need are already in the parent class, except for URL to the document;
    Video(String URI, String Name, String Type, Url url) {
        super(URI, Name, Type, url);
    }

    //> Shows video
    public void Summary() {
        System.out.println(Creation());
        System.out.println("URL:" + this.url);
    }

}

class Page extends Document {

    //> based on the spec all the things we need are already in the parent class, except for URL to the document;
    Page(String URI, String Name, String Type, Url url) {
        super(URI, Name, Type, url);
    }

    //> Shows page
    public void Summary() {
        System.out.println(Creation());
        System.out.println("URL:" + this.url);
    }

}

//> this is not an stream object, but URL is used 9in documents and other, thus I will place it here;
class Url { //> based on https://www.w3.org/TR/activitystreams-vocabulary/#dfn-url we have: type, href and mediaType;

    String type;
    String mediaType;
    String href;

    //> Constructor
    Url(String type, String href, String mediaType) {
        this.type = type;
        this.href = href;
        this.mediaType = mediaType;
    }

    Url(String type) {
        this.type = type;
    }

    // getters
    public String getType() {
        return this.type;
    }

    public String getHref() {
        return this.href;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    // setters
    public void setType(String type) {
        this.type = type;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    //> This shows the content of the URL
    public String toString() {
       return "The URL " + href + " is of type " + type + " and it has a media type of " + mediaType;
    }

}

