package runner;

import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ClinicRunner {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("input.txt"));
        String s = input.nextLine();
        int noOfAnimals = Integer.parseInt(s);
        Animal[] animals = new Animal[noOfAnimals];

        for (int i=0; i<noOfAnimals;i++){
            String[] values = input.nextLine().split(",");
            String animal = values[0];
            Animal a = null;
            switch (animal){
                case "pisica": a = new Cat(values[1], values[2], values[3], Integer.parseInt(values[4])); break;
                case "caine": a = new Dog(values[1], Double.parseDouble(values[2]), values[3], Integer.parseInt(values[4])); break;
                case "papagal": a = new Parrot(values[1], values[2], Boolean.parseBoolean(values[3])); break;
                default: System.out.println("Pacientul introdus nu este valid");
            }
            s = input.nextLine();
            int noOfTreatments = Integer.parseInt(s);
            for (int j = 0; j<noOfTreatments; j++){
                s = input.nextLine();
                values = s.split(",");
                Treatment t = new Treatment(values[0], Double.parseDouble(values[1]));
                a.addTreatment(t);
            }
            animals[i] = a;
        }

        Scanner screenInput = new Scanner(System.in);
        System.out.println("Introduceti un nume: ");
        String lookUpName = screenInput.nextLine();

        boolean found = false;
        for (Animal a: animals){
            if (lookUpName != null && lookUpName.equals(a.getName())) {
                found = true;
                a.displayAllDetailsToConsole();
            }
        }

        if (!found){
            System.out.println("Animalul cautat nu a fost gasit.");
        }
    }
}
