## Interview Study
### Andrew Gable
### 1/30/15

Repo to store some code study for various topics. 

Yes, some of the examples may be elementary but I am rusty with this stuff. Also, I don't get to use my trust IDE. Just a simple text editor and the command line to compile and run code. 

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



