import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    static private ArrayList<Transaction> calculate (String[] names, Payment[] payments){
        Person[] persons = new Person[names.length];
        for (int i = 0; i < persons.length; i++){
            persons[i] = new Person(names[i], 0.0);
        }
        persons = sumPayments(payments, persons);
        double total = 0.0;
        for (Payment payment : payments){
            total += payment.getAmount();
        }
        double split = total / persons.length;

        for (Person person : persons){
            person.setAmount(person.getAmount() - split);
        }

        Arrays.sort(persons, new Person.sortByAmount());

        ArrayList<Transaction> result = new ArrayList<>();

        for (int i = 0; i < persons.length; i++){
            for (int j = persons.length - 1; j >= 0; j--){
                if (persons[j].getAmount() < 0 && persons[i].getAmount() + persons[j].getAmount() >= 0) {
                    persons[i].setAmount(persons[i].getAmount() + persons[j].getAmount());
                    result.add(new Transaction(persons[j].getName(), persons[i].getName(), -persons[j].getAmount()));
                    persons[j].setAmount(0.0);
                }
            }
        }
//        for(Person person : persons) {
//            System.out.println(person);
//        }
        for (int j = persons.length - 1; j >= 0; j--){
            for (int i = 0; i < persons.length; i++){
                if (persons[j].getAmount() < -0.01){
                    persons[j].setAmount(persons[j].getAmount() + persons[i].getAmount());
                    result.add(new Transaction(persons[j].getName(), persons[i].getName(), persons[i].getAmount()));
                    persons[i].setAmount(0.0);
                }
//                if (persons[j].getAmount() < -0.01){
//                    persons[j].setAmount(0.0);
//                }
            }
        }

//        for(Person person : persons) {
//            System.out.println(person);
//        }
        return result;
    }

    static private Person[] sumPayments (Payment[] payments, Person[] persons) {
        for (Payment payment : payments){
            for (Person person : persons){
                if (payment.getPayer().equals(person.getName())){
                    person.setAmount(person.getAmount() + payment.getAmount());
                }
            }
        }
        return persons;
    }

    public static void main (String[] args) {
//        Payment payment1 = new Payment("hzy", 100);
//        Payment payment2 = new Payment("hzy", 288);
//        Payment payment3 = new Payment("hzy", 100);
//        Payment payment4 = new Payment("hhl", 200);
//        Payment payment5 = new Payment("hzy", 168);
//        Payment payment6 = new Payment("hhl", 90);
//        Payment payment7 = new Payment("zx", 81);
//        Payment payment8 = new Payment("hzy", 70);
//        Payment payment9 = new Payment("hzy", 80);
//        Payment payment10 = new Payment("qz", 200);
//        Payment payment11 = new Payment("hzy", 200);
//        Payment payment12 = new Payment("hhl", 200);
//        Payment [] payments = {payment1,payment2,payment3,payment4,payment5,payment6,payment7,payment8,payment9,payment10,payment11,payment12};
//        String [] names = {"hhl", "hzy", "zx", "qz", "lyf", "whz", "syp"};

        Scanner input = new Scanner(System.in);

        System.out.println("Enter the payments, separate names and amount by space and payments by coma.");
        System.out.println();
        String paymentString = input.nextLine();

        String[] paymentsStrings = paymentString.split(",");
        Payment[] payments = new Payment[paymentsStrings.length];

        for (int i = 0; i < paymentsStrings.length; i++){
            String[] fields = paymentsStrings[i].split(" ");
            Payment payment = new Payment(fields[0], Double.parseDouble(fields[1]));
            payments[i] = payment;
        }

        System.out.println("Enter the name of participants, separate by space.");
        System.out.println();
        String nameString = input.nextLine();

        String[] names = nameString.split(" ");

        ArrayList<Transaction> result = calculate(names, payments);
        for (Transaction transaction : result){
            System.out.println(transaction.toString());
        }
    }
}
