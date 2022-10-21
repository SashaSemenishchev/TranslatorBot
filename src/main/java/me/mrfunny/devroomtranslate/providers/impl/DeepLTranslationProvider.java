package me.mrfunny.devroomtranslate.providers.impl;

import me.mrfunny.devroomtranslate.providers.TranslationProvider;
import me.mrfunny.devroomtranslate.util.HttpUtil;
import me.mrfunny.devroomtranslate.util.MapBuilder;
import net.dv8tion.jda.api.utils.data.DataObject;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class DeepLTranslationProvider extends TranslationProvider {
    private final static String DEEPL_API_KEY = "TOKEN"; // Mattia pls don't steal it

    @Override
    public Optional<String> translate(String requestRaw) {
        String request = "text=" + URLEncoder.encode(requestRaw, StandardCharsets.UTF_8) + "&target_lang=EN-GB";
        String rawContent = HttpUtil.makePost("https://api-free.deepl.com/v2/translate", new MapBuilder<String, String>()
                .put("Host", "api-free.deepl.com")
                .put("Authorization", DEEPL_API_KEY)
                .put("Content-Type", "application/x-www-form-urlencoded")
                .put("User-Agent", "DevRoom/V3")
                .put("Content-Length", String.valueOf(request.length()))
                .build(),
                request
        );
        if(rawContent == null) return Optional.empty();
        DataObject object = DataObject.fromJson(rawContent);
//        return Optional.of(rawContent);
        return Optional.of(object.getArray("translations").getObject(0).getString("text")); // taking the text of the translation
    }
}
