package me.mrfunny.devroomtranslate.util;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder <K, V> {
    public MapBuilder(){
        this.result = new HashMap<K, V>();
    }

    private final Map<K, V> result;

    public MapBuilder<K, V> put(K key, V value) {
        result.put(key, value);
        return this;
    }

    public Map<K, V> build() {
        return this.result;
    }
}
