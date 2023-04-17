package activitypub;

import java.util.*;

// Inbox is said to be a collection of activities
// ReadNext acts a lot like a queue
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
