import java.util.Comparator;
import java.util.Objects;

public class Person {
    private String name;
    private double amount;

    public Person(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }

    static class sortByAmount implements Comparator<Person> {
        public int compare(Person a, Person b) {
            return (int)(b.amount - a.amount);
        }
    }
}
