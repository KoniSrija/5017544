package week1pgms;
import java.util.*;
public class FinacialForecasting {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Intial value:");
        double initialValue = sc.nextDouble();
        System.out.println("Enter rate of growth:");
        double growthRate = sc.nextDouble();   
        System.out.println("Enter no of periods:");
        int periods = sc.nextInt();              

        double futureValue = FutureValue(initialValue, growthRate, periods);
        System.out.println("The predicted future value after " + periods + " periods is: " + futureValue);
    }
	public static double FutureValue(double initialValue, double growthRate, int periods) {
        if (periods == 0) {
            return initialValue;
        }
        double nextValue = initialValue * (1 + growthRate);
        return FutureValue(nextValue, growthRate, periods - 1);
    }

}
