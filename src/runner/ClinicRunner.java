package runner;

import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ClinicRunner {
    public static void main(String[] args) throws FileNotFoundException {
        searchPatientAndDisplayDetails(readAnimals());
    }

    private static void searchPatientAndDisplayDetails(Animal[] animals) {
        lookUpPatient(animals, readLookUpPatientName());
    }

    private static String readLookUpPatientName() {
        Scanner screenInput = new Scanner(System.in);
        System.out.println("Introduceti un nume: ");
        String lookUpName = screenInput.nextLine();
        System.out.println();
        return lookUpName;
    }

    private static void lookUpPatient(Animal[] animals, String lookUpName) {
        boolean found = false;
        if (animals != null && animals.length != 0) {
            for (Animal a : animals) {
                if (lookUpName != null && lookUpName.equals(a.getName())) {
                    found = true;
                    a.displayAllDetailsToConsole();
                }
            }
        } else {
            System.out.println("Lista de pacienti este goala.");
        }

        if (!found){
            System.out.println("Animalul cautat nu a fost gasit.");
        }
    }

    private static Animal[] readAnimals() throws FileNotFoundException {
        Treatment[] treatments = readTreatmentsFromFile();
        Scanner input;
        input = new Scanner(new File("pacienti.txt"));
        Animal[] animals = null;
        while (input.hasNextLine()){
            String line = input.nextLine();

            Animal a = createAnimal(line);

            if (a != null) {
                String[] mapping = line.split("-");
                String[] treatmentDescriptions = mapping[1].split(",");
                for (String treatmentDescription: treatmentDescriptions) {
                    for (Treatment t : treatments) {
                        if (t.getDescription().equals(treatmentDescription)){
                            a.addTreatment(t);
                        }
                    }
                }

                if (animals == null) {
                    animals = new Animal[1];
                } else {
                    animals = Arrays.copyOf(animals, animals.length + 1);
                }
                animals[animals.length - 1] = a;
            }
        }
        return animals;
    }

    private static Animal createAnimal(String line) {
        String[] mapping = line.split("-");
        Animal a = null;
        String[] animal = mapping[0].split(",");
        switch (animal[0]) {
            case "pisica" -> a = new Cat(animal[1], animal[2], animal[3], Integer.parseInt(animal[4]));
            case "caine" ->
                    a = new Dog(animal[1], Double.parseDouble(animal[2]), animal[3], Integer.parseInt(animal[4]));
            case "papagal" -> a = new Parrot(animal[1], animal[2], Boolean.parseBoolean(animal[3]));
            default -> System.out.println("Intrare nevalida detectata: " + line);
        }
        return a;
    }

    private static Treatment[] readTreatmentsFromFile() throws FileNotFoundException {
        Scanner input = new Scanner(new File("tratament.txt"));
        Treatment[] treatments = null;
        while (input.hasNextLine()){
            String line = input.nextLine();
            String[] fields = line.split(",");
            Treatment t = new Treatment(fields[0], Double.parseDouble(fields[1]));
            if (treatments == null) {
                treatments = new Treatment[1];
            } else {
                if (!isTreatmentAlreadyInList(treatments, t)) {
                    treatments = Arrays.copyOf(treatments, treatments.length + 1);
                }
            }
            treatments[treatments.length - 1] = t;
        }
        input.close();
        return treatments;
    }

    private static boolean isTreatmentAlreadyInList(Treatment[] treatments, Treatment t) {
        boolean found = false;
        for (Treatment recordedTreatment: treatments){
            if (recordedTreatment.equals(t)){
                found = true;
                break;
            }
        }
        return found;
    }
}
