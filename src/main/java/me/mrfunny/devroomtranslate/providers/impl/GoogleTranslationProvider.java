package me.mrfunny.devroomtranslate.providers.impl;

import me.mrfunny.devroomtranslate.util.HttpUtil;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class GoogleTranslationProvider extends DeepLTranslationProvider {
    private static final String googleApiUrl = "LINK TO GOOGLE SCRIPT";
    @Override
    public Optional<String> translate(String requestRaw) {
        return Optional.ofNullable(HttpUtil.makeGet(googleApiUrl
                + "?q=" + URLEncoder.encode(requestRaw, StandardCharsets.UTF_8)));
    }
}
