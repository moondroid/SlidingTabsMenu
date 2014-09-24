package it.moondroid.slidingtabsmenu;

/**
 * Created by marco.granatiero on 24/09/2014.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import it.moondroid.slidingmenulib.R;

public class ChatTabLayout extends RelativeLayout {
    private static final int ANIMATION_DURATION = 100;
    private static final int AVATAR_BOUNCE_LIMIT = 3;
    private static final int AVATAR_BOUNCE_REPEAT_DELAY = 2000;
    private static final int NUM_OBJECTS_TO_CACHE = 20;
    private static final int UNREAD_COUNT_MAX = 9;
    private static Map<Message, Integer> bounceCount;
    //private static LruCache<Addresses, CachedChatTabInfo> cache;
    private final ImageView avatar;
    private final AnimationSet avatarAnimation;
    private boolean avatarAnimationIsAnimating;
    private final View avatarWrapper;
    private ChatTabEntry chatTabEntry;
    private final TextView title;
    private final TextView unreadIndicator;

    private static class CachedChatTabInfo {
        private final Bitmap bitmap;
        private final String title;

        private CachedChatTabInfo(Bitmap bitmap, String title) {
            this.bitmap = bitmap;
            this.title = title;
        }
    }


    static {
        bounceCount = new HashMap();
        resetCache();
    }

    public ChatTabLayout(Context context) {
        super(context);
        this.avatarAnimationIsAnimating = false;
        inflate(context, R.layout.tab_chat, this);
        setClipToPadding(true);
        this.avatar = (ImageView) findViewById(R.id.avatar);
        this.avatarWrapper = findViewById(R.id.avatar_wrapper);
        this.avatarAnimation = (AnimationSet) AnimationUtils.loadAnimation(context, R.anim.tabs_avatar_bounce);
        this.title = (TextView) findViewById(R.id.title);
        this.unreadIndicator = (TextView) findViewById(R.id.unread_indicator);
        setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ChatTabLayout.this.chatTabEntry.selectTab();
            }
        });
        setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                ChatTabLayout.this.chatTabEntry.deleteChat();
                return true;
            }
        });
    }

    private void bounceChatTab(boolean withDelay) {
        if (!(this.avatarAnimationIsAnimating)) {
            incrementBounceCount();
            this.avatarAnimation.setStartOffset((long) (withDelay ? AVATAR_BOUNCE_REPEAT_DELAY : 0));
            this.avatarAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationEnd(Animation anim) {
                    ChatTabLayout.this.clearBounceChatTab();
                    if (ChatTabLayout.this.canChatTabContinueBouncing()) {
                        ChatTabLayout.this.bounceChatTab(true);
                    }
                }

                public void onAnimationRepeat(Animation anim) {
                }

                public void onAnimationStart(Animation anim) {
                    ChatTabLayout.this.avatarAnimationIsAnimating = true;
                }
            });
            this.avatarWrapper.startAnimation(this.avatarAnimation);
        }
    }

    private boolean canChatTabContinueBouncing() {
        Integer count = (Integer) bounceCount.get(this.chatTabEntry.getMessage());
        return count != null && count.intValue() < this.avatarAnimation.getAnimations().size() * 3;
    }

    private boolean canChatTabStartBouncing() {
        if (this.chatTabEntry.getMessage().read) {
            return false;
        }
        Integer count = (Integer) bounceCount.get(this.chatTabEntry.getMessage());
        return count == null || count.intValue() < this.avatarAnimation.getAnimations().size() * 3;
    }

    private void clearBounceChatTab() {
        this.avatarAnimationIsAnimating = false;
        this.avatarAnimation.setAnimationListener(null);
        this.avatarWrapper.clearAnimation();
    }

    private void incrementBounceCount() {
        Message message = this.chatTabEntry.getMessage();
        Integer count = (Integer) bounceCount.get(message);
        bounceCount.put(message, Integer.valueOf(count == null ? 1 : Integer.valueOf(count.intValue() + 1).intValue()));
    }

    public static void resetBounces() {
        bounceCount.clear();
    }

    public static void resetCache() {
        //cache = new LruCache(20);
    }

    private void updateContent() {
        String unreadCountString;
        Message message = this.chatTabEntry.getMessage();
//        this.avatar.setImageBitmap(info.bitmap);
//        this.title.setText(info.title);
        this.title.setText(message.title);
        this.title.setVisibility(VISIBLE);
        this.unreadIndicator.setVisibility(message.read ? GONE : VISIBLE);
        int unreadCount = message.getUnreadCount();
        if (message.read || unreadCount <= 1) {
            unreadCountString = "";
        } else if (unreadCount > 9) {
            unreadCountString = new StringBuilder(String.valueOf(String.valueOf(UNREAD_COUNT_MAX))).append("+").toString();
        } else {
            unreadCountString = String.valueOf(unreadCount);
        }
        this.unreadIndicator.setText(unreadCountString);
        if (message.read) {
            bounceCount.remove(message);
        }
        if (canChatTabStartBouncing()) {
            bounceChatTab(false);
            return;
        }
        clearBounceChatTab();
    }

    public void loadChatTabContents(ChatTabEntry chatTabEntry) {
        this.chatTabEntry = chatTabEntry;

        this.avatar.clearAnimation();
        this.title.clearAnimation();
//        CachedChatTabInfo cachedChatTabInfo = (CachedChatTabInfo) cache.get(addresses);
//        if (cachedChatTabInfo != null) {
//            updateContent(cachedChatTabInfo);
//            return;
//        }

//        this.avatar.setImageBitmap(null);
        this.title.setVisibility(INVISIBLE);
        this.unreadIndicator.setVisibility(GONE);

        updateContent();
    }
}
