package de.tamaurice;

import de.tamaurice.listeners.impl.TikTokLinkListenerImpl;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.user.UserStatus;

public class Main extends Thread {
    private final String TOKEN = ""; // Add your bot token yourself here!

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        DiscordApi api = new DiscordApiBuilder()
                .setToken(TOKEN)
                .login().join();

        api.updateActivity(ActivityType.CUSTOM, "Censoring T*kt*k");
        api.updateStatus(UserStatus.ONLINE);

        System.out.println("Discord Bot Invite Link: " + api.createBotInvite());

        api.addListener(new TikTokLinkListenerImpl());
    }
}
