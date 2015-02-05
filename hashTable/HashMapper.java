
public interface HashMapper<K, V> {
		
	public V get(Object key);

	public boolean isEmpty();

	public V put(K key, V value);

	public V remove(Object key);

	public int size();
}