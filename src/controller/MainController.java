package controller;

import controller.activity.ActivityController;
import controller.user.UserController;
import controller.water.WaterController;
import controller.weight.WeightController;
import entity.activity.Activity;
import entity.water.WaterConsumptionToday;
import repository.ActivityRepository;
import repository.UserRepository;
import repository.WaterConsumptionRepository;
import repository.WeightHistoryRepository;
import service.activity.ActivityService;
import service.user.UserService;
import service.water.WaterService;
import service.weight.WeightService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MainController {
    Scanner scanner = new Scanner(System.in);
    private final UserController userController;
    private final UserRepository userRepository;
    private final UserService userService;

    public MainController() {
        this.userRepository = new UserRepository();
        this.userService = new UserService(userRepository);
        this.userController = new UserController(userService);
        userService.loadUsersFromFile();
    }

    public void securityLogin() {
        userController.authorization();
    }

    public void run() {
        WeightHistoryRepository weightHistoryRepository = new WeightHistoryRepository(LocalDate.now());

        boolean shouldEnd = true;
        do {
            System.out.println(" ");
            System.out.println("Co chcesz zrobic?:");
            System.out.println("5. Asystent Aktywnosci");
            System.out.println("6. Asystent picia wody");
            System.out.println("7. Asystent wagi");
            System.out.println("8. Ustawienia konta");
            System.out.println("9. Wyjscie");
            System.out.println(" ");

            int userChoice = scanner.nextInt();
            switch (userChoice) {
                case 5:
                    Activity activity = new Activity(LocalDate.now(), "Test", 1.5, 5, "opis aktywności");
                    ActivityRepository activityRepository = new ActivityRepository();
                    ActivityService activityService = new ActivityService(activityRepository, activity, userService,new ArrayList<>());
                    activityService.loadActivities();
                    ActivityController activityController = new ActivityController(activityService);
                    activityController.activityAssistant();
                    break;

                case 6:
                    WaterConsumptionToday todayData = new WaterConsumptionToday(LocalDate.now(), 0, 1000);
                    WaterConsumptionRepository history = new WaterConsumptionRepository();
                    WaterService waterService = new WaterService(todayData, history, userService);
                    WaterController waterController = new WaterController(waterService);
                    waterController.waterSupplyMenu();
                    break;
                case 7:
                    WeightService weightService = new WeightService(weightHistoryRepository, null, userService);
                    WeightController weightController = new WeightController(weightService);
                    weightController.WeightMenu();
                    break;
                case 8:
                    userController.userSettings();
                    break;
                case 9:
                    shouldEnd = false;
                    break;
            }
        } while (shouldEnd);
    }
}
