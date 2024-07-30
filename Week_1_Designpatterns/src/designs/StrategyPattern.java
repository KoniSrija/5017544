package designs;


 interface PaymentStrategy {
    void pay(double amount);
}
 class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card.");
        // Add logic to process credit card payment
    }
}
 class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal.");
        // Add logic to process PayPal payment
    }
}
 class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("PaymentStrategy not set");
        }
        paymentStrategy.pay(amount);
    }
}
 
 //Testing
 class StrategyPattern {
	 public static void main(String[] args) {
	        PaymentContext paymentContext = new PaymentContext();

	        // Test Credit Card Payment
	        PaymentStrategy creditCardPayment = new CreditCardPayment("8989898989", "Alexa");
	        paymentContext.setPaymentStrategy(creditCardPayment);
	        paymentContext.executePayment(100.00);

	        // Test PayPal Payment
	        PaymentStrategy payPalPayment = new PayPalPayment("alexa@****.com");
	        paymentContext.setPaymentStrategy(payPalPayment);
	        paymentContext.executePayment(150.00);
	    }
}