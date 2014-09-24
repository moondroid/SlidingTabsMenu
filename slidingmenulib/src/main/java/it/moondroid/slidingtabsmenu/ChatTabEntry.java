package it.moondroid.slidingtabsmenu;

/**
 * Created by marco.granatiero on 24/09/2014.
 */
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;


public class ChatTabEntry implements TabsFragment.SelectableTab {
//    private final Addresses addresses;
    private final Message message;
    private final Context context;
    private Listener listener;
    private long threadId = 0;

    public static interface Listener {
        void onChatClick(long j, boolean z);

        void onChatDelete(long j);
    }

    public ChatTabEntry(FragmentActivity activity, Message message) {
        this.context = activity;
        this.listener = (Listener) activity;
        this.message = message;
//        this.threadId = message.threadId;
//        this.addresses = message.getAddresses();
    }

    public void deleteChat() {
        this.listener.onChatDelete(this.threadId);
    }

//    public Addresses getAddresses() {
//        return this.addresses;
//    }

    public Message getMessage() {
        return this.message;
    }


//    public long getThreadId() {
//        return this.threadId;
//    }

    public View getView(View view) {
        ChatTabLayout chatTabLayout = (ChatTabLayout) view;
        if (chatTabLayout == null) {
            chatTabLayout = new ChatTabLayout(this.context);
        }
        chatTabLayout.loadChatTabContents(this);
        return chatTabLayout;
    }

    public void selectTab() {
        this.listener.onChatClick(this.threadId, false);
    }
}
