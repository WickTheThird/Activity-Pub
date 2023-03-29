package activitypub;

class ActivityStream implements Activity {
    String uri;

    // init and testing
    public static void main(String... args) {}

    public void activityStream(String uri) {
        this.uri = uri;
    }

    // constructors

    // getters
    public String getURI() {
        return "this is uri";
    }

    // setters
    public void setURI(String uri) {
        this.uri = uri;
    }

}

class User {
    String name;
    String preferedUsername;
    String summary;
    int followers;

    // init and testing
    public static void main(String... args) {}

    public void user(String name) {
        this.name = name;

        System.out.println("User " + this.name + " created");

    }

    // constructors

    public void Inbox() {}

    public void Outbox() {}

    // getters
    public String getName() {
        return this.name;
    }

    public String getPreferedUsername() {
        return this.preferedUsername;
    }

    public String getSummary() {
        return this.summary;
    }

    public int getFollowers() {
        return this.followers;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.preferedUsername = username;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

}
