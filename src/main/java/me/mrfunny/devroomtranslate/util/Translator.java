package me.mrfunny.devroomtranslate.util;

import me.mrfunny.devroomtranslate.providers.TranslationProvider;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Optional;

public class Translator {
    private final ArrayList<TranslationProvider> providers = new ArrayList<>();

    @SafeVarargs
    public Translator(Class<? extends TranslationProvider>... providers) {
        for (Class<? extends TranslationProvider> provider : providers) {
            try {
                this.providers.add(provider.getConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                System.out.println("Failed to instantiate translation provider " + provider.getName());
            }
        }
    }

    public Optional<String> translate(String contentRaw) {
        for(TranslationProvider provider : providers) {
            Optional<String> result = provider.translate(contentRaw);
            if(result.isPresent()) {
                return result;
            }
        }
        return Optional.empty();
    }
}
