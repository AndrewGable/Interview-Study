public class OpenHashTable<K, V> implements HashMapper<K, V> {
	
	private Entry<K, V>[] table;
	private static final int START_CAPACITY = 101;
	private double LOAD_THRESHOLD = 0.75;
	private int numKeys;
	private int numDeletes;
	private final Entry<K, V> DELETED = new Entry<K, V>(null, null);

	public OpenHashTable() {
		table = new Entry[START_CAPACITY];
	}

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

	public int size() {
		return table.length;
	}

	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}