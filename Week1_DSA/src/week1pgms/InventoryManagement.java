package week1pgms;

import java.util.*;

class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return productId;
    }

    public void setId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return productName;
    }

    public void setName(String productName) {
        this.productName = productName;
    }

   
}

class Operation {
    private HashMap<String, Product> products;

    public Operation() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public Product updateProduct(String productId, Product updatedProduct) {
        return products.replace(productId, updatedProduct);
    }

    public Product deleteProduct(String productId) {
        return products.remove(productId);
    }
    public void displayProducts() {
    	System.out.println(products);
    }
}

public class InventoryManagement {
    public static void main(String[] args) {
        Operation op = new Operation();
        Scanner sc = new Scanner(System.in);
        int n;

        do {
            System.out.println("1. Add 2. Update 3. Delete 4. Exit");
            System.out.println("Choose the operation:");
            n = sc.nextInt();
            sc.nextLine(); 

            switch (n) {
                case 1:
                    System.out.println("Enter the product details: ID, Name, Quantity, Price");
                    String id = sc.nextLine();
                    String name = sc.nextLine();
                    int quantity = sc.nextInt();
                    double price = sc.nextDouble();
                    sc.nextLine(); 
                    Product newProduct = new Product(id, name, quantity, price);
                    op.addProduct(newProduct);
                    break;

                case 2:
                    System.out.println("Enter the ID of the product to update:");
                    String originalId = sc.nextLine();
                    System.out.println("Enter the updated product details: Name, Quantity, Price");
                    String updatedName = sc.nextLine();
                    int updatedQuantity = sc.nextInt();
                    double updatedPrice = sc.nextDouble();
                    sc.nextLine(); 
                    Product updatedProduct = new Product(originalId, updatedName, updatedQuantity, updatedPrice);
                    op.updateProduct(originalId, updatedProduct);
                    break;

                case 3:
                    System.out.println("Enter the ID of the product to delete:");
                    String delProductId = sc.nextLine();
                    op.deleteProduct(delProductId);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (n != 4);

        sc.close();
    }
}
/*Time Complexity for adding a product: o(1)
                                        Because hashmap takes constant time add elements into it.
   Time Complexity for Updating a product:o(1)
                                        Hashmap retrives the values based on the keys which 
                                        takes constant time to change values
   Time compllexity for deleting a product:o(1)
                                       Hashmap retrives the values based on the keys which 
                                        takes constant time to delete or remove them*/
/*Optimization:  If the Entries are more ,to avoid collision use a good hash Function*/