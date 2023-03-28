package activitypub;

public class ActivityStream extends ClientApp implements Activity {
    String uri;

    public static void main(String... args) {}

    public void activityStream(String uri) {
        this.uri = uri;
    }

    public String getURI() {
        return "this is uri";
    }

    public void setURI(String uri) {
        this.uri = uri;
    }

}
