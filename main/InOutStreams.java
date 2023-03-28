package activitypub;

class InputStream extends ClientApp implements Inbox {

    // init and testing
    public static void main(String... args) {}

    public static void InputStream() {}

    // constructors
    public boolean receive(Activity activity) {
        return false;
    }

    public Activity readNext() {
        return null;
    }

    // getters
    public int getCount() {
        return 0;
    }

    // setters

}

class OutputStream extends ClientApp implements Outbox {

    // init and testing
    public static void main(String... args) {}

    public static void OutputStream() {}

    // constructors
    public boolean send(Activity activity) {
        return false;
    }

    public Activity deliverNext() {
        return null;
    }

    // getters
    public int getCount() {
        return 0;
    }

    // setters

}
