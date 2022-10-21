package me.mrfunny.devroomtranslate;

import me.mrfunny.devroomtranslate.providers.impl.DeepLTranslationProvider;
import me.mrfunny.devroomtranslate.providers.impl.GoogleTranslationProvider;
import me.mrfunny.devroomtranslate.util.Translator;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.function.BiConsumer;

public class TranslatorBot extends ListenerAdapter {
    private final Translator translator;
    private final BiConsumer<MessageContextInteractionEvent, Translator> eventConsumer;

    public TranslatorBot(BiConsumer<MessageContextInteractionEvent, Translator> eventConsumer) {
        this.translator = new Translator(
                DeepLTranslationProvider.class,
                GoogleTranslationProvider.class
        );
        this.eventConsumer = eventConsumer;
    }

    public static void init(JDA jda, BiConsumer<MessageContextInteractionEvent, Translator> eventConsumer) {
        jda.addEventListener(new TranslatorBot(eventConsumer));
        for(Guild guild : jda.getGuilds()) {
            System.out.println("Initializing translation service for guild with ID: " + guild.getId());
            initGuild(guild);
        }
    }

    private static void initGuild(Guild guild) {
        guild.upsertCommand(Commands.message("Translate").setGuildOnly(true)).queue();
    }

    @Override
    public void onMessageContextInteraction(MessageContextInteractionEvent event) {
        if (event.getName().equals("Translate")) {
            eventConsumer.accept(event, this.translator);
        }
    }
}
