package me.mrfunny.devroomtranslate;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws LoginException, InterruptedException {
        JDA jda = JDABuilder
                .create(GatewayIntent.GUILD_MESSAGES)
                .setToken("TOKEN")
                .build();

        jda.awaitReady();
        TranslatorBot.init(jda, (event, translator) -> {
            event.deferReply(true).queue(hook -> {
                translator.translate(event.getTarget().getContentRaw()).ifPresentOrElse(
                        (translation) -> hook.sendMessage(translation).queue(),
                        () -> hook.sendMessageEmbeds(new EmbedBuilder().setTitle("Failed to translate this message").setColor(Color.RED).build()).queue()
                );
            });
        });
    }
}