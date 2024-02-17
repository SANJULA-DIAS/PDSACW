package restaurant.menu.management.system;

import java.util.Scanner;

class MenuItem {
    String name;
    double price; 

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class BinarySearchTree {
    class TreeNode {
        MenuItem menuItem;
        TreeNode left, right;

        public TreeNode(MenuItem item) {
            this.menuItem = item;
            this.left = this.right = null;
        }
    }

    private TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(MenuItem item) {
        root = insertRec(root, item);
    }

    private TreeNode insertRec(TreeNode root, MenuItem item) {
        if (root == null) {
            root = new TreeNode(item);
            return root;
        }

        if (item.name.compareTo(root.menuItem.name) < 0) {
            root.left = insertRec(root.left, item);
        } else if (item.name.compareTo(root.menuItem.name) > 0) {
            root.right = insertRec(root.right, item);
        }
        else{
            System.out.println("This Dish "+"("+item.name +")"+" already in the menu");
        }

        return root;
    }

    // In-order traversal to get items in alphabetical order
    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println("Name: " + root.menuItem.name + ", Price: " + root.menuItem.price);
            inorderRec(root.right);
        }
    }
}

public class RestaurantMenuManagementSystem {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);

        // Getting menu items from the user
        System.out.println("Enter menu items (Dish Name and Price). "
                + "Ex:- Pizza 890.00 "
                + " To finish for adding items type 'done' .");
        String input;
        while (!(input = scanner.nextLine()).equalsIgnoreCase("done")) {
            String[] inputParts = input.split(" ");
            if (inputParts.length == 2) {
                String name = inputParts[0];
                double price = Double.parseDouble(inputParts[1]);
                bst.insert(new MenuItem(name, price));
            } else {
                System.out.println("Invalid input. Please enter Dish Name and Price separated by space."
                        + "Ex:- Pizza 890.00 ");
            }
        }

        // Displaying menu items in alphabetical order with prices
        System.out.println("\nMenu Items in Alphabetical Order:");
        bst.inorder();
    }
    
}
