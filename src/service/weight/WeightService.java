package service.weight;

import entity.user.User;
import entity.weight.WeightHistory;
import entity.weight.WeightMeasure;
import repository.WeightHistoryRepository;
import repository.WeightMeasureRepository;
import service.user.UserService;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeightService {
    private WeightHistoryRepository weightHistoryRepository;
    private WeightHistory weightHistory;
    private List<WeightHistory> weightHistories;
    private WeightMeasureRepository weightMeasureRepository;
    private UserService userService;

    public WeightService(WeightHistoryRepository weightHistoryRepository, WeightHistory weightHistory, WeightMeasureRepository weightMeasureRepository) {
        this.weightHistoryRepository = weightHistoryRepository;
        this.weightHistory = weightHistory;
        this.weightHistories = weightHistoryRepository.getWeightHistories();
        this.weightMeasureRepository = weightMeasureRepository;
    }

    public void saveWeightToFile() {
        User user = userService.getLoggedUserOrThrow();
        String filename = "weightHistory_" + user.getUsername() + ".txt";
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
        User user = userService.getLoggedUserOrThrow();
        List<WeightHistory> weightHistories = new ArrayList<>();
        String filename = "weightHistory_" + user.getUsername() + ".txt";
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
                    weightHistories.add(new WeightHistory(date, weight));
                }
                weightHistoryRepository.setWeightHistories(weightHistories);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addWeight(double weight) {
        weightHistoryRepository.add(LocalDate.now(), weight);
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

    public void saveMeasuresToFile() {
        User user = userService.getLoggedUserOrThrow();
        String fileName = "MeasuresHistory_" + user.getUsername() + ".txt";
        File file = new File(fileName);
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (WeightMeasure measure : weightMeasureRepository.getMeasures()) {
                writer.println(measure.getDate() + "," + measure.getAboveNavel() + "," + measure.getBelowNavel() + "," + measure.getNavel() + "," + measure.getArmBiceps() + "," + measure.getCalf() + "," + measure.getChest() + "," + measure.getHips() + "," + measure.getNeck() + "," + measure.getThig() + "," + measure.getWeight());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMeasuresFromFile() {
        User user = userService.getLoggedUserOrThrow();
        List<WeightMeasure> weightMeasures = new ArrayList<>();
        String filename = "MeasuresHistory_" + user.getUsername() + ".txt";

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Plik historii pomiarów sylwetki nie istnieje.");
            createMeasuresHistoryFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    LocalDate date = LocalDate.parse(parts[0]);
                    double aboveNavel = Double.parseDouble(parts[1]);
                    double belowNavel = Double.parseDouble(parts[2]);
                    double navel = Double.parseDouble(parts[3]);
                    double armBiceps = Double.parseDouble(parts[4]);
                    double calf = Double.parseDouble(parts[5]);
                    double chest = Double.parseDouble(parts[6]);
                    double hips = Double.parseDouble(parts[7]);
                    double neck = Double.parseDouble(parts[8]);
                    double thig = Double.parseDouble(parts[9]);
                    double weight = Double.parseDouble(parts[10]);

                    weightMeasures.add(new WeightMeasure(date, weight, neck, chest, armBiceps, aboveNavel, navel, belowNavel, hips, thig, calf));
                }
                weightMeasureRepository.setMeasures(weightMeasures);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addMeasure(WeightMeasure weightMeasure) {
        weightMeasureRepository.addMeasure(weightMeasure);
    }

    public List<WeightMeasure> getBodyMeasurements() {
        return weightMeasureRepository.getMeasures();
    }

    public void printMeasuresHistory() {
        System.out.println("Historia pomiarów sylwetki:");
        List<WeightMeasure> bodyMeasurements = getBodyMeasurements();
        if (bodyMeasurements.isEmpty()) {
            System.out.println("Brak danych o pomiarach sylwetki.");
        } else {
            for (WeightMeasure measure : bodyMeasurements) {
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

    public void editMeasure(LocalDate date, double newNeck, double newChest, double newArmBiceps, double newAboveNavel, double newNavel, double newBelowNavel, double newHips, double newThig, double newCalf) {
        List<WeightMeasure> measures = weightMeasureRepository.getMeasures();
        boolean found = false;
        for (WeightMeasure measure : measures) {
            if (measure.getDate().equals(date)) {
                measure.setNeck(newNeck);
                measure.setChest(newChest);
                measure.setArmBiceps(newArmBiceps);
                measure.setAboveNavel(newAboveNavel);
                measure.setNavel(newNavel);
                measure.setBelowNavel(newBelowNavel);
                measure.setHips(newHips);
                measure.setThig(newThig);
                measure.setCalf(newCalf);
                found = true;
                System.out.println("Pomiar sylwetki na dzień " + date + " został zaktualizowany.");
                break;
            }
        }
        if (!found) {
            System.out.println("Nie znaleziono pomiaru sylwetki dla podanej daty.");
        }
        saveMeasuresToFile();
    }

    public void createMeasuresHistoryFile() {
        User user = userService.getLoggedUserOrThrow();
        String fileName = "MeasuresHistory_" + user.getUsername() + ".txt";
        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("Utworzono nowy plik: " + file.getName());
            } else {
                System.out.println("Plik już istnieje.");
            }
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas tworzenia pliku: " + e.getMessage());
        }
    }

}