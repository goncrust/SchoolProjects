/* avoid worst case O(n), but overhead constructing */
/* search, remove and insert: O(logN) */
/* not finished */

#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int value;
    int height;
    struct Node *left;  
    struct Node *right;
} *Node;

static Node head;

void init() {
    head = NULL;
}

Node new(int value, Node left, Node right) {
    Node new_node = (Node) malloc(sizeof(struct Node));

    new_node->value = value;
    new_node->height = 1;
    new_node->left = left;
    new_node->right = right;

    return new_node;
}

Node search_aux(int value, Node node) {
    if (node == NULL)
        return NULL;
    else if (node->value == value)
        return node;
    else if (node->value < value)
        return search_aux(value, node->right);
    else
        return search_aux(value, node->left);
}

Node search(int value) {
    return search_aux(value, head);
}

Node insert_aux(int value, Node node) {
    if (node == NULL)
        return new(value, NULL, NULL);
    else if (node->value < value)
        node->left = insert_aux(value, node->left);
    else
        node->right = insert_aux(value, node->right);
    
    return node;
}

void insert(int value) {
    head = insert_aux(value, head);
}

Node max(Node node) {
    while (node != NULL || node->right != NULL)
        node = node->right;

    return node;
}

Node min(Node node) {
    while (node != NULL || node->left != NULL)
        node = node->left;

    return node;
}

Node remove_aux(int value, Node node) {
    if (node == NULL)
        return NULL;
    else if (node->value < value)
        node->right = remove_aux(value, node->right);
    else if (node->value > value)
        node->left = remove_aux(value, node->left);
    else {
        if (node->left != NULL && node->right != NULL) {
            /* Replace with max from left */
            int temp = node->value;

            Node max_node = max(node->left);
            node->value = max_node->value;

            max_node->value = temp;
            node->left = remove_aux(temp, node->left);
        } else {
            Node temp = node;
            if (node->left == NULL && node->right == NULL)
                node = NULL;
            else if (node->left != NULL)
                node = node->left;
            else
                node = node->right;

            free(temp);
        }
    }

    return node;
}

void remove_node(int value) {
    head = remove_aux(value, head);
}

int count(Node node) {
    if (node == NULL)
        return 0;
    
    return count(node->left)+count(node->right)+1;
}

int tree_count() {
    return count(head);
}

int height(Node node) {
    if (node == NULL)
        return 0;

    return node->height;
}

int tree_height() {
    return height(head);
}

void visit(Node node) {
    printf("%d", node->value);
}

void traverse_preorder(Node node) {
    if (node == NULL)
        return;

    visit(node);
    traverse_preorder(node->left);
    traverse_preorder(node->right);
}

void traverse_inorder(Node node) {
    if (node == NULL)
        return;

    traverse_preorder(node->left);
    visit(node);
    traverse_preorder(node->right);
}

void traverse_postorder(Node node) {
    if (node == NULL)
        return;

    traverse_preorder(node->left);
    traverse_preorder(node->right);
    visit(node);
}

Node rotL(Node node) {
    Node x = node->right;
    node->right = x->left;
    x->left = node;
    return x;
}

Node rotR(Node node) {
    Node x = node->left;
    node->left = x->right;
    x->right = node;
    return x;
}
