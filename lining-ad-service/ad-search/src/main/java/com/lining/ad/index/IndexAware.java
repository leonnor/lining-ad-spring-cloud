package com.lining.ad.index;

/**
 * className IndexAware
 * description 索引的增删改查
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/25 20:20
 */
public interface IndexAware<K, V> {

    V get(K key);

    void add(K key, V value);

    void update (K key, V value);

    void delete(K key, V value);
}
