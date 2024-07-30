package week1pgms;
import java.util.*;

// Main class to handle the e-commerce search functionality
public class Ecommerce {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of products:");
        int n = sc.nextInt();
        sc.nextLine(); 
        Product1[] products = new Product1[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter the product details: ID, Name, Category");
            int id = sc.nextInt();
            sc.nextLine(); 
            String name = sc.nextLine();
            String category = sc.nextLine();
            Product1 newProduct = new Product1(id, name, category);
            products[i] = newProduct;
        }

        // Linear Search
        System.out.println("Implementation of Linear Search:\nEnter the product ID which you want to search:");
        int searchId = sc.nextInt();
        int r = LinearSearch.linearSearch(products, searchId);
        if (r != -1) {
            System.out.println("Product found at index " + r);
        } else {
            System.out.println("Product not found");
        }

        // Binary Search
        System.out.println("Implementation of Binary Search:\nEnter the product ID which you want to search:");
        searchId = sc.nextInt();
        int r1 = BinarySearch.binarySearch(products, searchId);
        if (r1 != -1) {
            System.out.println("Product found at index " + r1);
        } else {
            System.out.println("Product not found");
        }

        sc.close();
    }
}

class Product1 {
    private int productId;
    private String productName;
    private String category;

    public Product1(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", category=" + category + "]";
    }
}

// Linear Search class
class LinearSearch {
    public static int linearSearch(Product1[] products, int targetProductId) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductId() == targetProductId) {
                return i;
            }
        }
        return -1;
    }
}

// Binary Search class
class BinarySearch {
    public static int binarySearch(Product1[] products, int targetProductId) {
        // Sort products based on productId
        Arrays.sort(products, Comparator.comparingInt(Product1::getProductId));
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = products[mid].getProductId();

            if (comparison == targetProductId) {
                return mid; // Product found at index mid
            } else if (comparison < targetProductId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; 
    }
}
