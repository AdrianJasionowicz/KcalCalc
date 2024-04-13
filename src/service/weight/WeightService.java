package service.weight;

import entity.weight.WeightHistory;
import repository.WeightHistoryRepository;
import service.user.UserService;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeightService {
    private final WeightHistoryRepository weightHistoryRepository;
    private final UserService userService;
    private final WeightHistory weightHistory;

    public WeightService(WeightHistoryRepository weightHistoryRepository, WeightHistory weightHistory, UserService userService) {
        this.weightHistoryRepository = weightHistoryRepository;
        this.userService = userService;
        this.weightHistory = weightHistory;
    }

    public void saveWeightToFile() {
        String filename = "weightHistory_" + userService.getLoggedUserOrThrow().getUsername() + ".txt";
        File file = new File(filename);

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (WeightHistory history : weightHistoryRepository.getWeightHistories()) {
                writer.println(history.getDate() + "," + history.getWeight());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadWeightFromFile() {
        List<WeightHistory> weightHistories = new ArrayList<>();
        String filename = "weightHistory_" + userService.getLoggedUserOrThrow().getUsername() + ".txt";
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Plik historii wagi nie istnieje.");

        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    LocalDate date = LocalDate.parse(parts[0]);
                    double weight = Double.parseDouble(parts[1]);
                    double bmi = Double.parseDouble(parts[2]);
                    weightHistories.add(new WeightHistory(date, weight, bmi));
                }
                weightHistoryRepository.setWeightHistories(weightHistories);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setWeight(LocalDate date, double weight) {
        weightHistoryRepository.setWeight(date, weight);
    }

    public void showHistory() {
        weightHistoryRepository.showHistory();
    }

    public void editWeight(LocalDate date, double newWeight) {
        boolean found = false;
        for (WeightHistory measurement : weightHistoryRepository.getWeightHistories()) {
            if (measurement.getDate().equals(date)) {
                measurement.setWeight(newWeight);
                System.out.println("Waga na dzień " + date + " została zaktualizowana na " + newWeight);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Nie znaleziono pomiaru dla podanej daty.");
        }
    }

    public void addMeasure(WeightHistory weightHistory) {
        weightHistoryRepository.addMeasure(LocalDate.now(),weightHistory);
    }

    public List<WeightHistory> getBodyMeasurements() {
        return weightHistoryRepository.getMeasuresHistory();
    }

    public void printMeasuresHistory() {
        System.out.println("Historia pomiarów sylwetki:");
        List<WeightHistory> bodyMeasurements = weightHistoryRepository.getWeightHistories();
        if (bodyMeasurements.isEmpty()) {
            System.out.println("Brak danych o pomiarach sylwetki.");
        } else {
            for (WeightHistory measure : bodyMeasurements) {
                System.out.println("Data: " + measure.getDate());
                System.out.println("Szyja: " + measure.getNeck() + " cm");
                System.out.println("Klatka piersiowa: " + measure.getChest() + " cm");
                System.out.println("Biceps: " + measure.getArmBiceps() + " cm");
                System.out.println("Powyżej pępka: " + measure.getAboveNavel() + " cm");
                System.out.println("Pępek: " + measure.getNavel() + " cm");
                System.out.println("Poniżej pępka: " + measure.getBelowNavel() + " cm");
                System.out.println("Biodra: " + measure.getHips() + " cm");
                System.out.println("Udo: " + measure.getThig() + " cm");
                System.out.println("Łydka: " + measure.getCalf() + " cm");
                System.out.println("Waga: " + measure.getWeight() + " kg");
                System.out.println("--------------------------------------");
            }
        }
    }

    public void editMeasure(LocalDate date,WeightHistory weightHistory) {
        List<WeightHistory> measures = weightHistoryRepository.getWeightHistories();
        boolean found = false;
        for (WeightHistory measure : measures) {
            if (measure.getDate().equals(date)) {
                measure.setNeck(measure.getNeck());
                measure.setChest(measure.getChest());
                measure.setArmBiceps(measure.getArmBiceps());
                measure.setAboveNavel(measure.getAboveNavel());
                measure.setNavel(measure.getNavel());
                measure.setBelowNavel(measure.getBelowNavel());
                measure.setHips(measure.getHips());
                measure.setThig(measure.getThig());
                measure.setCalf(measure.getCalf());
                found = true;
                System.out.println("Pomiar sylwetki na dzień " + date + " został zaktualizowany.");
                break;
            }
        }
        if (!found) {
            System.out.println("Nie znaleziono pomiaru sylwetki dla podanej daty.");
        }
    }



    public void addWeight(double weight) {
        LocalDate date = LocalDate.now();
        double height = userService.getLoggedUserOrThrow().getHeight();
        double bmi = calculateBmi(weight, height);
        System.out.println(bmi);

        WeightHistory weightHistory = new WeightHistory(date, weight, bmi);
        weightHistoryRepository.addWeightHistory(weightHistory);
    }


    private double calculateBmi(double weight, double height) {
        return weight / (height * height);
    }

    public void saveMeasurementsToFile() {
        String filename = "measurementsHistory_" + userService.getLoggedUserOrThrow().getUsername() + ".txt";
        File file = new File(filename);

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (WeightHistory history : weightHistoryRepository.getMeasuresHistory()) {
                writer.println(history.getDate() + ","
                        + history.getNeck() + ","
                        + history.getChest() + ","
                        + history.getArmBiceps() + ","
                        + history.getAboveNavel() + ","
                        + history.getNavel() + ","
                        + history.getBelowNavel() + ","
                        + history.getHips() + ","
                        + history.getThig() + ","
                        + history.getCalf() + ","
                        + history.getWeight() + ","
                        + history.getBmi());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<WeightHistory> loadMeasurementsFromFile() {
        List<WeightHistory> measurements = new ArrayList<>();
        String filename = "measurementsHistory_" + userService.getLoggedUserOrThrow().getUsername() + ".txt";
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("Plik historii pomiarów nie istnieje.");
            return measurements;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                LocalDate date = LocalDate.parse(parts[0]);
                double neck = Double.parseDouble(parts[1]);
                double chest = Double.parseDouble(parts[2]);
                double armBiceps = Double.parseDouble(parts[3]);
                double aboveNavel = Double.parseDouble(parts[4]);
                double navel = Double.parseDouble(parts[5]);
                double belowNavel = Double.parseDouble(parts[6]);
                double hips = Double.parseDouble(parts[7]);
                double thig = Double.parseDouble(parts[8]);
                double calf = Double.parseDouble(parts[9]);
                measurements.add(new WeightHistory(date, neck, chest, armBiceps, aboveNavel, navel, belowNavel, hips, thig, calf));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return measurements;
    }
}