package designs;


interface PaymentProcessor{
	void processPayment(double amount);
}
 class Payment1 {
    public void makePayment(double amount) {
       
        System.out.println("Processing  payment1 of $" + amount);
    }
}
 class Payment2 {
	    public void charge(double amount) {
	      
	        System.out.println("Processing  payment2 of $" + amount);
	    }
	}
 class PaymentAdapter1 implements PaymentProcessor {
	    private Payment1 payment1;

	    public PaymentAdapter1(Payment1 payment1) {
	        this.payment1 = payment1;
	    }

	    @Override
	    public void processPayment(double amount) {
	        payment1.makePayment(amount);
	    }
	}
 class PaymentAdapter2 implements PaymentProcessor {
	    private Payment2 payment2;

	    public PaymentAdapter2(Payment2 payment2) {
	        this.payment2 = payment2;
	    }

	    @Override
	    public void processPayment(double amount) {
	        payment2.charge(amount);
	    }
	}
 public class AdapterPattern {
	    public static void main(String[] args) {
	        // Create an instance of Payment1
	        Payment1 pay1 = new Payment1();
	        // Create a Payment1 adapter
	        PaymentProcessor pay1Adapter = new PaymentAdapter1(pay1);
	        // Process a payment using Pay1
	        pay1Adapter.processPayment(100.00);

	        // Create an instance of Payment1
	        Payment2 pay2 = new Payment2();
	        // Create a Payment2 adapter
	        PaymentProcessor pay2Adapter = new PaymentAdapter2(pay2);
	        // Process a payment using Stripe
	        pay2Adapter.processPayment(200.00);

	        
	    }
	}
 