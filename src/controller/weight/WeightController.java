package controller.weight;

import entity.weight.WeightHistory;
import service.weight.WeightService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class WeightController {
    private final WeightService weightService;

    public WeightController(WeightService weightService) {
        this.weightService = weightService;
    }

    Scanner scanner = new Scanner(System.in);

    public void WeightMenu() {
        weightService.loadMeasurementsFromFile();
        weightService.loadWeightFromFile();
        boolean shouldEnd = true;
        do {
            System.out.println(" ");
            System.out.println("1. Dodaj wage");
            System.out.println("2. Zmien dzisiejsza wage");
            System.out.println("3. Zobacz historie");
            System.out.println("4. Zmien zapisany pomiar wagi");
            System.out.println("5. Dodaj pomiary ciala");
            System.out.println("6. Zobacz historie pomiarow sylwetki");
            System.out.println("7. Zmien zapisany pomiar sylwetki");
            System.out.println("9. Wyjscie ");
            System.out.println(" ");
            int userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1:
                    System.out.println("Podaj dzisiejsza wage: ");
                    weightService.addWeight(scanner.nextDouble());
                    break;
                case 2:
                    System.out.println("Podaj skorygowaną wagę: ");
                    double correctedWeight = scanner.nextDouble();
                    weightService.setWeight(LocalDate.now(), correctedWeight);
                    break;
                case 3:
                    System.out.println(" ");
                    weightService.showHistory();
                    break;
                case 4:
                    scanner.nextLine();
                    System.out.println("Podaj datę pomiaru do edycji (RRRR-MM-DD):");
                    String dateString = scanner.nextLine();
                    LocalDate date = LocalDate.parse(dateString);
                    System.out.println("Podaj nową wagę:");
                    double newWeight = scanner.nextDouble();
                    weightService.editWeight(date, newWeight);
                    break;
                case 5:
                    scanner.nextLine();
                    System.out.println("Podaj datę pomiaru ciała (RRRR-MM-DD):");
                    String measureDateString = scanner.nextLine();
                    LocalDate measureDate = LocalDate.parse(measureDateString);
                    System.out.println("Podaj pomiary ciała w kolejności (szyja, klatka piersiowa, biceps, powyżej pępka, pępek, poniżej pępka, biodra, udo, łydka, waga):");
                    System.out.print("Szyja (cm): ");
                    double neck = scanner.nextDouble();
                    System.out.print("Klatka piersiowa (cm): ");
                    double chest = scanner.nextDouble();
                    System.out.print("Biceps (cm): ");
                    double armBiceps = scanner.nextDouble();
                    System.out.print("Powyżej pępka (cm): ");
                    double aboveNavel = scanner.nextDouble();
                    System.out.print("Pępek (cm): ");
                    double navel = scanner.nextDouble();
                    System.out.print("Poniżej pępka (cm): ");
                    double belowNavel = scanner.nextDouble();
                    System.out.print("Biodra (cm): ");
                    double hips = scanner.nextDouble();
                    System.out.print("Udo (cm): ");
                    double thig = scanner.nextDouble();
                    System.out.print("Łydka (cm): ");
                    double calf = scanner.nextDouble();
                    System.out.print("Waga (kg): ");
                    WeightHistory newMeasure = new WeightHistory(measureDate, neck, chest, armBiceps, aboveNavel, navel, belowNavel, hips, thig, calf);
                    weightService.addMeasure(newMeasure);
                    break;
                case 6:
                    scanner.nextLine();
                    System.out.println("Podaj datę:");
                    weightService.printMeasuresHistory();
                    break;
                case 7:
                    weightService.printMeasuresHistory();
                    scanner.nextLine();
                    System.out.println("Podaj datę pomiaru do edycji (RRRR-MM-DD):");
                    String measuresDateString = scanner.nextLine();
                    LocalDate measuresDate = LocalDate.parse(measuresDateString);

                    System.out.print("Nowa wartość szyi (cm): ");
                    double newNeck = scanner.nextDouble();
                    System.out.print("Nowa wartość klatki piersiowej (cm): ");
                    double newChest = scanner.nextDouble();
                    System.out.print("Nowa wartość bicepsa (cm): ");
                    double newArmBiceps = scanner.nextDouble();
                    System.out.print("Nowa wartość powyżej pępka (cm): ");
                    double newAboveNavel = scanner.nextDouble();
                    System.out.print("Nowa wartość pępka (cm): ");
                    double newNavel = scanner.nextDouble();
                    System.out.print("Nowa wartość poniżej pępka (cm): ");
                    double newBelowNavel = scanner.nextDouble();
                    System.out.print("Nowa wartość bioder (cm): ");
                    double newHips = scanner.nextDouble();
                    System.out.print("Nowa wartość uda (cm): ");
                    double newThig = scanner.nextDouble();
                    System.out.print("Nowa wartość łydki (cm): ");
                    double newCalf = scanner.nextDouble();
                    WeightHistory newMeasures = new WeightHistory(newNeck,newChest,newArmBiceps, newAboveNavel, newNavel, newBelowNavel, newHips, newThig, newCalf)
                    weightService.editMeasure(measuresDate,newMeasures);
                    break;
                case 9:
                    shouldEnd = false;
                    weightService.saveMeasurementsToFile();
                    weightService.saveWeightToFile();
                    break;
            }
        } while (shouldEnd);


    }


}
