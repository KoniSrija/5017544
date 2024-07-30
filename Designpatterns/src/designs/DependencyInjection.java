
package designs;

import java.util.*;


class Customer {
    private int id;
    private String name;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

interface CustomerRepository {
    Customer findCustomerById(int id);
}


class CustomerRepositoryImpl implements CustomerRepository {
    
    private Map<Integer, Customer> database = new HashMap<>();

    public CustomerRepositoryImpl() {
        database.put(1, new Customer(1, "Sri"));
        database.put(2, new Customer(2, "Liki"));
    }

    @Override
    public Customer findCustomerById(int id) {
        return database.get(id);
    }
}


class CustomerService {
    private CustomerRepository customerRepository;

   
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findCustomerById(id);
    }
}

// Testing
public class DependencyInjection {
    public static void main(String[] args) {
        // Create a concrete repository
        CustomerRepository customerRepository = new CustomerRepositoryImpl();

        // Inject the repository into the service
        CustomerService customerService = new CustomerService(customerRepository);

        // Use the service to find a customer
        Customer customer = customerService.getCustomerById(1);
        if (customer != null) {
            System.out.println("Found customer: " + customer.getName());
        } else {
            System.out.println("Customer not found.");
        }
    }
}
