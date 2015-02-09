/**
 * Open addressing hash table 
 * Only allows a load threshold of 0.75
 */
public class OpenHashTable<K, V> implements HashMapper<K, V> {
	
	private Entry<K, V>[] table;
	private static final int START_CAPACITY = 101;
	private double LOAD_THRESHOLD = 0.75;
	private int numKeys;
	private int numDeletes;
	private final Entry<K, V> DELETED = new Entry<K, V>(null, null);

	/**
	 * Public constructor 
	 */
	public OpenHashTable() {
		table = new Entry[START_CAPACITY];
	}

	/**
	 * Entry object for hash table
	 */
	private static class Entry<K, V> {
		
		private K key;

		private V value;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			this.value = value;
			return value;
		}
	}

	/**
	 * private function to find an object in a hash table
	 * @param  key to find in hash table
	 * @return     item if found
	 */
	private int find(Object key) {
		int index = key.hashCode() % table.length;

		if (index < 0) {
			index += table.length;
		}

		while ((table[index] != null) && (!key.equals(table[index].key))) {
			index++;

			// Wrap around the entire table
			if (index >= table.length) {
				index = 0;
			}
		}
		return index;
	}

	/**
	 * Get object from hash table
	 * @param  key to add to hash table
	 * @return     null if no item found, item if found
	 */
	public V get(Object key) {
		// Find the object
		int index = find(key);

		if (table[index] != null) {
			// We actually found the key
			return table[index].value;
		} else {
			return null;
		}
	}

	/**
	 * Put an item in the hash table, rehash maybe called if load is more than LOAD_THRESHOLD
	 * @param  key   to put in table
	 * @param  value to put in table
	 * @return       null if there was no collision, old item if collided 
	 */
	public V put(K key, V value) {
		int index = find(key);

		// We found an empty spot
		if (table[index] == null) {
			table[index] = new Entry<K, V>(key, value);
			numKeys++;

			// Check if we need to rehash
			double loadFactor = (double) (numKeys + numDeletes) / table.length;

			if (loadFactor > LOAD_THRESHOLD) {
				rehash();
			}

			return null;
		}

		// Replace old value
		V oldValue = table[index].value;
		table[index].value = value;
		return oldValue;
	}

	/**
	 * Remove an item from the hash table
	 * @param  key object to add to hash table
	 * @return     null if not deleted, item if remove is successful 
	 */
	public V remove(Object key) {
		int index = find(key);

		if (table[index] == null) {
			return null;
		}

		V deletedValue = table[index].value;

		table[index] = DELETED;
		numDeletes++;
		numKeys--;


		return deletedValue;
	}

	/**
	 * Private method to rehash the hash table if the load is too large.
	 */
	private void rehash() {
		Entry<K, V>[] oldTable = table;

		// Double capacity of table
		table = new Entry[2 * oldTable.length + 1];

		// Reinsert old items
		numKeys = 0;
		numDeletes = 0;

		for (int i = 0; i < oldTable.length; i++) {
			if ((oldTable[i] != null) && (oldTable[i] != DELETED)) {
				// Insert element into new table
				put(oldTable[i].key, oldTable[i].value);
			}
		} 
	}

	/**
	 * Get size of hash table
	 * @return size of hash table
	 */
	public int size() {
		return table.length;
	}

	/**
	 * Is the hash table empty
	 * @return true if the hash table is empty
	 */
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}