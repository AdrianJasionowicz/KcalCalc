package controller.water;

import controller.MainController;
import service.water.WaterService;

import java.util.Scanner;

public class WaterController {
    Scanner scanner = new Scanner(System.in);

    private WaterService waterService;
    private MainController mainController = new MainController();


    public WaterController(WaterService waterService) {
        this.waterService = waterService;
        waterService.loadHistoryData();
        waterService.loadHistoryListData();
    }


    public void waterSupplyMenu() {
        waterService.loadTodayData();
        boolean shouldEnd = true;

        do {
            System.out.println("1. Dodaj szklanke wody");
            System.out.println("2. Zmien cel");
            System.out.println("3. Zobacz statystki");
            System.out.println("4. Zresetuj spozycie dzisiaj");
            System.out.println("5. Zobacz historie");
            System.out.println("9. Koniec");
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    waterService.addWater();
                    break;
                case 2:
                    System.out.println("Podaj cel spozycia w ml: ");
                    waterService.setDailyTarger(scanner.nextInt());
                    break;
                case 3:
                    waterService.showWaterConsumption();
                    break;
                case 4:
                    waterService.restartYourDay();
                    break;
                case 5:
                    waterService.showHistory();
                    break;
                case 9:
                    waterService.saveData();
                    shouldEnd = false;
                    break;
                default:
                    System.out.println("Błąd sprobuj jeszcze raz");
                    userChoice = scanner.nextInt();
                    break;
            }
        } while (shouldEnd);

    }
}
