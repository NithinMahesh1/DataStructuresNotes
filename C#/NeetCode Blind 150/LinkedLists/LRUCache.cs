// Implement the Least Recently Used (LRU) cache class LRUCache. 
// The class should support the following operations
// LRUCache(int capacity) Initialize the LRU cache of size capacity.
// int get(int key) Return the value corresponding to the key if the key exists, 
// otherwise return -1.
// void put(int key, int value) Update the value of the key if the key exists. 
// Otherwise, add the key-value pair to the cache. 
// If the introduction of the new pair causes the cache to exceed its capacity, 
// remove the least recently used key.
// A key is considered used if a get or a put operation is called on it.

// Ensure that get and put each run in O(1) average time complexity.

using System.Net.Http.Headers;
using System.Reflection;
using System.Security.Cryptography.X509Certificates;

public class Node {
    public int Key { get; set; }
    public int Val { get; set; }
    public Node prev { get; set; }
    public Node next { get; set; }
    public Node(int key, int val) {
        Key = key;
        Val = val;
        prev = null;
        next = null;
    }
}

public class LRUCache {
    private int cap;
    private Dictionary<int,Node> cache;
    private Node left;
    private Node right;

    public LRUCache(int capacity) {
        // Use a hashset to keep track of k/v's
        // Need to make sure we do not exceed cap
        cap = capacity;
        cache = new Dictionary<int, Node>();
        left = new Node(0,0);
        right = new Node(0,0);
        left.next = right;
        right.prev = left;
    }
    
    public int Get(int key) {
        if(cache.ContainsKey(key)) {
            // We first get the node with the key
            Node node = cache[key];

            // Then since we are accessing that key
            // we have to set it to most recently used
            // by removing that node and adding back so it does to the right
            // in our linked list
            Remove(node);
            Insert(node);

            return node.Val;
        }

        // If there is no key there
        return -1;
    }
    
    public void Put(int key, int value) {
        // Check if the key exists first and update val
        // also add it back so we are making it most recent
        if(cache.ContainsKey(key)) {
            Node exists = cache[key];
            // Update val
            exists.Val = value;

            // Remove and Insert back to make latest
            Remove(exists);
            Insert(exists);

            return;
        }

        // Handle if we are past capacity
        // we remove from left
        if(cache.Count >= cap) {
            Node lessFreq = left.next;
            Remove(lessFreq);
            cache.Remove(lessFreq.Key);
        }

        // If value is put we need to put that at the right
        Node newNode = new Node(key,value);
        cache.Add(key,newNode);
        Insert(newNode);
    }
    private void Remove(Node curr) {
        // Remove only left since it is the least used
        Node prev = curr.prev;
        Node nxt = curr.next;
        prev.next = nxt;
        nxt.prev = prev;
    } 
    private void Insert(Node curr) {
        // last node before right
        Node end = right.prev;
        end.next = curr;
        curr.prev = end;
        curr.next = right;
        right.prev = curr;
    }
    public static void main() {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.Put(1, 10);  // cache: {1=10}
        lRUCache.Get(1);      // return 10
        lRUCache.Put(2, 20);  // cache: {1=10, 2=20}
        lRUCache.Put(3, 30);  // cache: {2=20, 3=30}, key=1 was evicted
        lRUCache.Get(2);      // returns 20 
        lRUCache.Get(1);      // return -1 (not found)
    }
}