package it.moondroid.slidingtabsmenu;

/**
 * Created by marco.granatiero on 24/09/2014.
 */


public class Message {
    public final boolean failed;
    public final long id;
    public final String title;
    public final int messageType;
    public final boolean read;
    public final long threadId;
    public final long time;
    private int unreadCount;


    public Message (long id, String title, boolean read){
        this.id = id;
        this.title = title;
        this.read = read;

        this.failed = false;
        this.unreadCount = 0;
        this.messageType = 0;
        this.time = 0;
        this.threadId = 0;
    }

    public Message(long id, int messageType, String title, boolean read, long time, long threadId) {
        boolean z = false;
        this.unreadCount = 0;
        this.id = id;
        this.title = title;
        this.messageType = messageType;
        this.read = read;
        this.time = time;
        this.threadId = threadId;
        if (messageType == 5) {
            z = true;
        }
        this.failed = z;
    }

//    public abstract void delete(Context context);

//    public abstract String getBody();

//    public abstract String getDetailsPreview(Context context);

    public int getUnreadCount() {
        return this.unreadCount;
    }

//    public abstract boolean hasImageAttachment();

//    public abstract boolean hasOtherAttachment();

    public boolean isMediaSms() {
        return false;
    }

    public boolean isPartiallyLoaded() {
        return false;
    }

    public boolean isSent() {
        //return getSenderAddress() == null;
        return true;
    }

    public boolean isTap() {
        return false;
    }

//    public abstract void resend(Context context);

    public void setUnreadCount(int count) {
        this.unreadCount = count;
    }


}
