package activitypub;

//? all activity types will go here...
//> based on the spec provided, the main types are: follow, unfollow, like, unlike, delete and create; 
//? at least those are the ones that i have observed used in the demo

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
