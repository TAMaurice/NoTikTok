package de.tamaurice.listeners.impl;

import de.tamaurice.listeners.TikTokLinkListener;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TikTokLinkListenerImpl implements TikTokLinkListener {
    private final static Pattern pattern = Pattern.compile("^(http://www\\.|https://www\\.|http://|https://|http://vm\\.|https://vm\\.)?[a-z0-9]+([\\-.]tiktok+)\\.[a-z]{2,5}(:[0-9]{1,5})?(/.*)?$");   // Searches for vm.tiktok.com and www.tiktok.com

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        Matcher matcher = pattern.matcher(event.getMessageContent());
        System.out.println("Analyzing " + event.getMessageContent());

        if(matcher.matches()) {
            System.out.println("found a tiktok link");
            Message message = event.getMessage();
            message.reply("<@" + event.getMessageAuthor().getId() + "> just posted a TikTok Link! That's a no-go >:(");
            
            // I added this try-catch, because the bot might be so fast and delete the message before it can reply. So this is an intentional slow-down to prevent that.
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            message.delete();
        } else {
            System.out.println("found no tiktok link");
        }
    }
}
