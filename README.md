## Interview Study
### Andrew Gable - Spring 2015

Repo to store some code study for various topics. 

Yes, some of the examples may be elementary but I am rusty with this stuff. Also, I don't get to use my trusty IDE. Just a simple text editor and the command line to compile and run code. 

### Recursion 
1. There must be one case for a small value of n that can be solved directly. **The base case**
2. The problem can be split into a smaller version of the same problem. **The recursive case**

### Trees
1. Binary trees - Can only have two children
2. Binary search trees - Can only have two children, when item is inserted it is sorted in order. 

##### Tree Traversals 
- **Pre-Order**
```
    if tree = empty
        return null
    else 
        visit root 
        preOrder() left subtree 
        preOrder() right subtree
```
- **In-Order**
```
    if tree = empty
        return null
    else 
        inOrder() left subtree
        visit root
        inOrder() right subtree
```
- **Post-Order**
```
    if tree = empty
        return null
    else 
        postOrder() left subtree
        postOrder() right subtree
        visit root
```

### Graphs

- **Breadth First Search (BFS)**
```
1. Start at a vertex, mark it identified and place it in the queue
2. while the queue is not empty
3.   Take a vertex (u) out of the queue and visit it 
4.   for all vertices (v) adjacent to the vertex (u)
5.       if the v has not be identified or visited
6.           mark if identified 
7.           insert v into the queue
8.           set the parent of v to the vertex (u)
9. return parent
```

- **Depth First Search (DFS)**
```
1. Mark the current vertex (u) as visited and enter it in the discovery order list
2. for each vertex (v) adjacent to the current vertex (u)
3.    if v has not been visited
4.        set parent of v = u
5.        recursively call dfs starting a v
6. Mark u as finished, enter u into the finish order list.
```


## HashTable

1. Open Addressing - Calculate hash code, if there is a collision then index until a free spot is found
2. Channing - Calculate hash code, if there is a collision then add it to the array list (or linked list, tree, etc) at that index. 



