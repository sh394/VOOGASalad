package voogasalad.authoring.util;

import java.util.HashMap;
import java.util.Map;

public class ReversableMap<K,V> extends HashMap<K, V> {

    Map<V,K> reverseMap = new HashMap<V,K>();

    @Override
    public V put(K key, V value) {
        // TODO Auto-generated method stub
        reverseMap.put(value, key);
        return super.put(key, value);
    }

    public K getKey(V value){
        return reverseMap.get(value);
    }
}
