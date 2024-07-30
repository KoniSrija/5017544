package week1pgms;

import java.util.*;

public class SortingCustomers {
	public static void main(String args[])
	{
	 Scanner sc = new Scanner(System.in);
    System.out.println("Enter no of orders:");
     int n = sc.nextInt();
     sc.nextLine(); 
     Order[] orders1 = new Order[n];

     for (int i = 0; i < n; i++) {
         System.out.println("Enter the order details: orderID, CustomerName, price");
         int id = sc.nextInt();
         sc.nextLine(); 
         String name = sc.nextLine();
         int price = sc.nextInt();
        Order neworder = new Order(id, name, price);
         orders1[i] = neworder;
	}
     Order[] orders2 = new Order[n];
     orders2=orders1;
         System.out.println("Bubble sort(print all passes )");
         BubbleSort.bubbleSort(orders1);
         System.out.println("Quick sort(print all passes)");
         BubbleSort.bubbleSort(orders2);
     }
	}


 class Order {
    private int orderId;
    private String customerName;
    private double totalPrice;

    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", customerName=" + customerName + ", totalPrice=" + totalPrice + "]";
    }
}


 class BubbleSort {
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        boolean swap=false;
        int pass=0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swap=true;
                }
            }
            System.out.println("Pass " + (++pass) + ":");
            for (Order order : orders) {
                System.out.print(order.getTotalPrice() + " ");
            }
           System.out.println();
           if(!swap)break;
        }
    }
}

 class QuickSort {
    public static void quickSort(Order[] orders, int low, int high) {
    	int pass=0;
        if (low < high) {
            int pi = partition(orders, low, high);
            System.out.println("Pass " + (++pass) + ":");
            for (Order order : orders) {
                System.out.print(order.getTotalPrice() + " ");
            }
            System.out.println();

            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = (low - 1); // Index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            if (orders[j].getTotalPrice() < pivot) {
                i++;

                // Swap orders[i] and orders[j]
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        // Swap orders[i + 1] and orders[high] (or pivot)
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }
}



