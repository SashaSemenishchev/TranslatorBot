package me.mrfunny.devroomtranslate.providers;

import java.util.Optional;

public abstract class TranslationProvider {
    public Optional<String> translate(String contentRaw) {
        return Optional.empty();
    }
}
