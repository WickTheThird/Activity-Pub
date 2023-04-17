package activitypub;

//> Stream Object generates the type of objects that the users will be able to interact with

//? this is a parent class, the part that unifies all the objects
public abstract class StreamObject {
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

