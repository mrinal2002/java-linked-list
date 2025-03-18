class ItemNode {
    int itemId;
    String itemName;
    int quantity;
    double price;
    ItemNode next; // The one-way ticket to the next item!

    ItemNode(int itemId, String itemName, int quantity, double price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.next = null; // Default to null, because loneliness is real!
    }
}

class Inventory {
    private ItemNode head;

    // Add item at the beginning - Because some items like to be first!
    public void addItemAtBeginning(int itemId, String itemName, int quantity, double price) {
        ItemNode newNode = new ItemNode(itemId, itemName, quantity, price);
        newNode.next = head;
        head = newNode;
    }

    // Add item at the end - Some items prefer grand entries!
    public void addItemAtEnd(int itemId, String itemName, int quantity, double price) {
        ItemNode newNode = new ItemNode(itemId, itemName, quantity, price);
        if (head == null) {
            head = newNode;
            return;
        }
        ItemNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    // Add item at a specific position - Because some items have VIP access!
    public void addItemAtPosition(int position, int itemId, String itemName, int quantity, double price) {
        if (position <= 0) {
            addItemAtBeginning(itemId, itemName, quantity, price);
            return;
        }
        ItemNode newNode = new ItemNode(itemId, itemName, quantity, price);
        ItemNode temp = head;
        for (int i = 0; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }
        if (temp == null) return;
        newNode.next = temp.next;
        temp.next = newNode;
    }

    // Remove an item by Item ID - Because some items must go!
    public void removeItemById(int itemId) {
        if (head == null) return;
        if (head.itemId == itemId) {
            head = head.next;
            return;
        }
        ItemNode temp = head;
        while (temp.next != null && temp.next.itemId != itemId) {
            temp = temp.next;
        }
        if (temp.next != null) temp.next = temp.next.next;
    }

    // Update quantity of an item - Because stock levels matter!
    public void updateQuantityById(int itemId, int newQuantity) {
        ItemNode temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                temp.quantity = newQuantity;
                return;
            }
            temp = temp.next;
        }
    }

    // Search item by ID - Because finding lost items is important!
    public void searchById(int itemId) {
        ItemNode temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                System.out.println("Found: " + temp.itemName + " (ID: " + temp.itemId + ")");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found!");
    }

    // Search item by Name - Because names are easier than numbers!
    public void searchByName(String itemName) {
        ItemNode temp = head;
        while (temp != null) {
            if (temp.itemName.equalsIgnoreCase(itemName)) {
                System.out.println("Found: " + temp.itemName + " (ID: " + temp.itemId + ")");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found!");
    }

    // Calculate total inventory value - Because money matters!
    public double calculateTotalValue() {
        double total = 0;
        ItemNode temp = head;
        while (temp != null) {
            total += temp.quantity * temp.price;
            temp = temp.next;
        }
        return total;
    }

    // Display all items - Because organization is key!
    public void displayInventory() {
        if (head == null) {
            System.out.println("Inventory is empty!");
            return;
        }
        ItemNode temp = head;
        while (temp != null) {
            System.out.println(temp.itemId + ": " + temp.itemName + " | Quantity: " + temp.quantity + " | Price: " + temp.price);
            temp = temp.next;
        }
    }
}

public class InventorySystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        
        inventory.addItemAtEnd(1, "Laptop", 10, 75000);
        inventory.addItemAtBeginning(2, "Phone", 20, 30000);
        inventory.addItemAtPosition(1, 3, "Tablet", 15, 40000);
        
        System.out.println("Current Inventory:");
        inventory.displayInventory();
        
        System.out.println("Total Inventory Value: " + inventory.calculateTotalValue());
        
        System.out.println("Updating quantity of Item ID 2...");
        inventory.updateQuantityById(2, 25);
        inventory.displayInventory();
        
        System.out.println("Searching for Item ID 3...");
        inventory.searchById(3);
        
        System.out.println("Removing Item ID 1 (Laptop)...");
        inventory.removeItemById(1);
        inventory.displayInventory();
    }
}
