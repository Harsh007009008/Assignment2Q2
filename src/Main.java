import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.00");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the principal amount for the home loan (in Rs): ");
        double principalHomeLoan = scanner.nextDouble();
        System.out.print("Enter the annual interest rate for home loan (in %): ");
        double annualInterestRate = scanner.nextDouble();
        System.out.print("Enter the loan tenure (in years): ");
        int tenureYears = scanner.nextInt();
        System.out.print("Enter the monthly SIP investment amount (in Rs): ");
        double monthlySIP = scanner.nextDouble();
        System.out.print("Enter the annual SIP return rate (in %): ");
        double annualSIPRate = scanner.nextDouble();
        System.out.print("Enter the annual depreciation rate of the home asset (in %): ");
        double annualDepreciationRate = scanner.nextDouble();
        System.out.print("Enter the annual appreciation rate of the home asset (in %): ");
        double annualAppreciationRate = scanner.nextDouble();

//        EMI Calculator
//        P x R x (1+R)^N / [(1+R)^N-1] where-
//        P = Principal loan amount
//        N = Loan tenure in months
//        R = Monthly interest rate
//        The rate of interest (R) on your loan is calculated per month.
//        R = Annual Rate of interest/12/100

        double monthlyInterestRate = (annualInterestRate / 100) / 12;
        int totalMonths = tenureYears * 12;
        double emi = (principalHomeLoan * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, totalMonths))
                / (Math.pow(1 + monthlyInterestRate, totalMonths) - 1);

//        SIP Return Calculation
//        The formula for SIP return calculation is based on the formula for future value of annuity-due.
//                FV = P × ((1 + i)n - 1) / i) × (1 + i)
//        Where,
//                FV = Future value
//                P = Amount invested at the start of every payment interval
//        n = Number of payments
//        i = Periodic interest rate
//        r = Expected return rate in % per annum
//        Monthly SIP Formula
//        For a monthly SIP payment P for a period of n months:
//        Maturity amount = P × ((1 + i)n - 1) / i) × (1 + i)
//        where,
//                i = r / 100 / 12

        double totalSIPAmount = 0;
        double futureValueSIP = 0;
        double sipMonthlyRate = annualSIPRate / 100 / 12;
        for (int month = 1; month <= totalMonths; month++) {
            futureValueSIP += monthlySIP * Math.pow(1 + sipMonthlyRate, totalMonths - month + 1);
            totalSIPAmount += monthlySIP;
        }

        double homeValueDepreciated = principalHomeLoan;
        double homeValueAppreciated = principalHomeLoan;

        System.out.println("Yearly Breakdown:");
        for (int year = 1; year <= tenureYears; year++) {
            homeValueDepreciated -= homeValueDepreciated * (annualDepreciationRate / 100);
            homeValueAppreciated += homeValueAppreciated * (annualAppreciationRate / 100);
            System.out.println("Year " + year + ":");
            System.out.println("Home value depreciated: " + df.format(homeValueDepreciated));
            System.out.println("Home value appreciated: " + df.format(homeValueAppreciated));
            System.out.println();
        }

        // Summary
        double totalEMIPaid = emi * totalMonths;
        System.out.println("Summary at the end of the tenure:");
        System.out.println("Total EMI paid for home loan: ₹" + df.format(totalEMIPaid));
        System.out.println("Final home value after depreciation: ₹" + df.format(homeValueDepreciated));
        System.out.println("Final home value after appreciation: ₹" + df.format(homeValueAppreciated));
        System.out.println("Total amount invested through SIP: ₹" + df.format(totalSIPAmount));
        System.out.println("Future value of SIP investments: ₹" + df.format(futureValueSIP));

        scanner.close();
    }
}
