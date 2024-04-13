package controller.activity;

import entity.activity.Activity;
import service.activity.ActivityService;

import java.io.IOException;
import java.util.Scanner;

public class ActivityController {
    Scanner scanner = new Scanner(System.in);
    private ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
        activityService.loadActivities();
    }

    public void activityAssistant() throws IOException {
        boolean shouldEnd = true;
        do {
            System.out.println("1 Dodaj Aktywnosc:");
            System.out.println("2 Sprawdz historie aktywnosci");
            System.out.println("3 Edytuj atywnosc");
            System.out.println("4 Usun aktywnosc");
            System.out.println("9 Zamknij asystenta");


            int userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1:
                    Activity activity = activityService.createActivity();
                    activityService.addActivity(activity);
                    break;
                case 2:
                    activityService.checkActivities();
                    break;
                case 3:
                    System.out.println("Podaj numer aktywnosci");
                    int activityId = scanner.nextInt() - 1;
                    activityService.editActivity(activityId);
                    break;
                case 4:
                    System.out.println("Podaj numer aktywnosci");
                    int toDelete = scanner.nextInt();
                    activityService.deleteActivity(toDelete);
                    break;
                case 9:
                    activityService.saveActivities();
                    shouldEnd = false;
                    break;
                default:
            }
        } while (shouldEnd);
    }


}
