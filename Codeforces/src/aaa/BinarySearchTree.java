package aaa;

/**
 * Created by ankurverma1994 on 1/10/15.
 */

/*
    Arrays.sort(ym,(int a[],int b[]) ->
            {
                if(a[0]<b[0])
                    return -1;
                return 1;
            });
*/
class BinarySearchTree {

    /* Class containing left and right child of current node and key value*/
    class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    /* A utility function to insert a new node with given key in BST */
    Node insert(Node node, int key) {

        /* If the tree is empty, return a new node */
        if (node == null) {
            node = new Node(key);
            return node;
        }

        /* Otherwise, recur down the tree */
        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);

        /* return the (unchanged) node pointer */
        return node;
    }

    // A utility function to do inorder traversal of BST
    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(root.key);
            inorder(root.right);
        }
    }

    // Driver Program to test above functions
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 */
        Node root = null;
        root = tree.insert(root, 50);
        tree.insert(root, 50);
        tree.insert(root, 20);
        tree.insert(root, 40);
        tree.insert(root, 70);
        tree.insert(root, 60);
        tree.insert(root, 80);
        // print inorder traversal of the BST
        tree.inorder(root);
    }
}
