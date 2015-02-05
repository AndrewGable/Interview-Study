import java.util.*; 

public class ChainHashTable<K, V> implements HashMapper<K, V> {
	private LinkedList<Entry <K, V>>[] table;
	private int numKeys;
	private static final int CAPACITY = 101;


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

	public ChainHashTable() {
		table = new LinkedList[CAPACITY];
	}

	public V get(Object key) {
		int index = key.hashCode() % table.length;

		if (index < 0) {
			index += table.length;
		}

		if (table[index] == null) {
			return null;
		}

		// Search for the item
		for (Entry<K, V> nextItem : table[index]) {
			if (nextItem.key.equals(key)) {
				return nextItem.value;
			}
		}

		// Key is not in the table
		return null;
	}

	public V put(K key, V value) {
		int index = key.hashCode() % table.length;

		if (index < 0) {
			index += table.length;
		}

		if (table[index] == null) {
			table[index] = new LinkedList<Entry <K, V>>();
		}

		// Search the list for the value
		for (Entry<K, V> nextItem : table[index]) {
			if (nextItem.key.equals(key)) {
				// value found, replace it
				V oldValue = nextItem.value;
				nextItem.setValue(value);
				return oldValue;
			}
		}

		// Key is not in the table, add it
		table[index].addFirst(new Entry<K,V>(key, value));
		numKeys++;

		return null;
	}

	public V remove(Object key) {
		int index = key.hashCode() % table.length;

		if (index < 0) {
			index += table.length;
		}

		if (table[index] == null) {
			// Key is not in table
			return null;
		}

		// Search the list for the value
		for (Entry<K, V> nextItem : table[index]) {
			if (nextItem.key.equals(key)) {
				table[index].remove(nextItem);
				numKeys--;
				if (table[index].isEmpty()) {
					table[index] = null;
				}

				return nextItem.value;
			}
		}

		// Key is not in the table
		return null;
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