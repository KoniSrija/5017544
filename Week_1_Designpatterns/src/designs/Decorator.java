package designs;

interface Notifier{
	void send(String message);
}
class EmailNotifier implements Notifier
{
	public void send(String message)
	{
		 System.out.println("Sending Email with message: " + message);
	}
}
 abstract class NotifierDecorator implements Notifier {
    protected Notifier wrappedNotifier;

    public NotifierDecorator(Notifier notifier) {
        this.wrappedNotifier = notifier;
    }

    @Override
    public void send(String message) {
        wrappedNotifier.send(message);
    }
}
  class SMSNotifierDecorator extends NotifierDecorator {
	    public SMSNotifierDecorator(Notifier notifier) {
	        super(notifier);
	    }

	    @Override
	    public void send(String message) {
	        super.send(message);
	        sendSMS(message);
	    }

	    private void sendSMS(String message) {
	        System.out.println("Sending SMS with message: " + message);
	    }
	}
   class SlackNotifierDecorator extends NotifierDecorator {
	    public SlackNotifierDecorator(Notifier notifier) {
	        super(notifier);
	    }

	    @Override
	    public void send(String message) {
	        super.send(message);
	        sendSlackMessage(message);
	    }

	    private void sendSlackMessage(String message) {
	        System.out.println("Sending Slack message: " + message);
	    }
	}
   public class Decorator {
   public static void main(String args[])
   {
	   Notifier emailNotifier = new EmailNotifier();

      
       Notifier slackOnlyNotifier = new SlackNotifierDecorator(emailNotifier);
       slackOnlyNotifier.send("This is a Slack only message!");

       Notifier smsOnlyNotifier = new SMSNotifierDecorator(emailNotifier);
       smsOnlyNotifier.send("This is an SMS only message!");
   }
   }