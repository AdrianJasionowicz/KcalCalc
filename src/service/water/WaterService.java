package service.water;

import entity.user.User;
import entity.water.WaterConsumptionToday;
import repository.WaterConsumptionRepository;
import service.user.UserService;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WaterService {
    private WaterConsumptionToday waterConsumptionToday;
    private final WaterConsumptionRepository waterConsumptionRepository;
    private final UserService userService;


    public WaterService(WaterConsumptionToday waterConsumptionToday, WaterConsumptionRepository waterConsumptionRepository, UserService userService) {
        this.waterConsumptionToday = waterConsumptionToday;
        this.waterConsumptionRepository = waterConsumptionRepository;
        this.userService = userService;

    }

    public void loadHistoryData() {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String fileName = "water_history_" + userService.getLoggedUserOrThrow().getUsername() + ".txt";
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Plik historii wody nie istnieje.");

        } else {
            try {
                if (file.exists()) {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        LocalDate date = LocalDate.parse(parts[0], dateFormatter);
                        int waterConsumption = Integer.parseInt(parts[1]);
                        int dailyTarget = Integer.parseInt(parts[2]);
                        WaterConsumptionToday dayData = new WaterConsumptionToday(date, waterConsumption, dailyTarget);
                        waterConsumptionRepository.addEntry(date, dayData);
                    }
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveData() {
        try {

            String fileName = "water_history_" + userService.getLoggedUserOrThrow().getUsername() + ".txt";
            String dateString = waterConsumptionToday.getDate().toString();
            String newData = dateString + "," + waterConsumptionToday.getWaterConsumption() + "," + waterConsumptionToday.getDailyTarget();

            File file = new File(fileName);
            List<String> lines = new ArrayList<>();
            boolean dateExists = false;

            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String fileDate = parts[0];

                    if (fileDate.equals(dateString)) {
                        lines.add(newData);
                        dateExists = true;
                    } else {
                        lines.add(line);
                    }
                }
                reader.close();
            }

            if (!dateExists) {
                lines.add(newData);
            }

            FileWriter writer = new FileWriter(fileName);
            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WaterConsumptionToday loadTodayData() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        WaterConsumptionToday todayData = null;
        WaterConsumptionToday previousDayData = null;

        boolean isTargetChanged = false;
        User user = userService.getLoggedUserOrThrow();
        String fileName = "water_history_" + userService.getLoggedUserOrThrow() + ".txt";

        try {
            File file = new File(fileName);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    LocalDate date = LocalDate.parse(parts[0], dateFormatter);
                    if (date.equals(today)) {
                        int waterConsumption = Integer.parseInt(parts[1]);
                        int dailyTarget = Integer.parseInt(parts[2]);
                        todayData = new WaterConsumptionToday(date, waterConsumption, dailyTarget);
                        break;
                    }
                }

                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (todayData == null) {
            int oldDailytarget = getYesterdayDailyTarget();
            if (oldDailytarget == 0) {
                oldDailytarget = 4000;
            }
            waterConsumptionToday = new WaterConsumptionToday(today, 0, oldDailytarget);
        } else {
            waterConsumptionToday = todayData;
        }

        return todayData;
    }

    public int getYesterdayDailyTarget() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate yesterday = LocalDate.now().minusDays(1);
        int target = -1;
        String fileName = "water_history_" + userService.getLoggedUserOrThrow().getUsername() + ".txt";
        try {
            File file = new File(fileName);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                boolean foundYesterday = false;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    LocalDate date = LocalDate.parse(parts[0], dateFormatter);
                    if (date.equals(yesterday)) {
                        target = Integer.parseInt(parts[2]);
                        foundYesterday = true;
                        break;
                    }
                }
                if (!foundYesterday) {
                    reader.close();
                    reader = new BufferedReader(new FileReader(file));
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        target = Integer.parseInt(parts[2]);
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return target;
    }


    public void addWater() {
        int water = waterConsumptionToday.getWaterConsumption() + 200;
        waterConsumptionToday.setWaterConsumption(water);
    }

    public void setDailyTarger(int target) {
        waterConsumptionToday.setDailyTarget(target);
    }

    public void showWaterConsumption() {
        System.out.println("Wypito: " + waterConsumptionToday.getWaterConsumption() + " ml");
        System.out.println("Cel to: " + waterConsumptionToday.getDailyTarget() + " ml");
        System.out.println(waterConsumptionToday.getWaterConsumption() + "/" + waterConsumptionToday.getDailyTarget());

    }

    public void loadHistoryListData() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fileName = "water_history_ " + userService.getLoggedUserOrThrow().getUsername() + ".txt";

        try {
            File file = new File(fileName);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    LocalDate date = LocalDate.parse(parts[0], dateFormatter);
                    int waterConsumption = Integer.parseInt(parts[1]);
                    int dailyTarget = Integer.parseInt(parts[2]);
                    WaterConsumptionToday dayData = new WaterConsumptionToday(date, waterConsumption, dailyTarget);
                    waterConsumptionRepository.addEntry(date, dayData);
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restartYourDay() {
        waterConsumptionToday.setWaterConsumption(0);
    }

    public void showHistory() {
        Map<LocalDate, WaterConsumptionToday> historyMap = waterConsumptionRepository.getHistoryMap();
        for (Map.Entry<LocalDate, WaterConsumptionToday> entry : historyMap.entrySet()) {
            LocalDate date = entry.getKey();
            WaterConsumptionToday data = entry.getValue();

            System.out.println("Data: " + date);
            System.out.println("Wypito: " + data.getWaterConsumption() + " ml");
            System.out.println("Cel: " + data.getDailyTarget() + " ml");
            System.out.println();
        }
    }
}