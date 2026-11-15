import java.io.*;
import java.util.ArrayList;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    int age;

    Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class Main {

    public static void serialize(ArrayList<Employee> employees) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(
                             new FileOutputStream("emp.ser"))) {

            for (Employee employee : employees) {
                oos.writeObject(employee);
            }

            System.out.println("Data berhasil disimpan.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void deserialize() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new FileInputStream("emp.ser"))) {

            System.out.println("\nData Employee:");

            while (true) {
                Employee employee = (Employee) ois.readObject();

                System.out.println("Nama: " + employee.name);
                System.out.println("Umur: " + employee.age);
                System.out.println();
            }

        } catch (EOFException e) {
            System.out.println("Semua data berhasil dibaca.");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(new Employee("Deva", 19));
        employees.add(new Employee("Dita", 24));
        employees.add(new Employee("Icha", 29));

        serialize(employees);

        deserialize();
    }
}