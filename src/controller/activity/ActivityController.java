package controller.activity;

import entity.activity.Activity;
import service.activity.ActivityService;

import java.time.LocalDate;
import java.util.Scanner;

public class ActivityController {
    Scanner scanner = new Scanner(System.in);
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
        activityService.loadActivities();
    }

    public void activityAssistant() {
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
                    Activity activity = createActivity();
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

    public Activity createActivity() {
        System.out.println("Podaj nazwę aktywności:");
        String activityName = scanner.nextLine();

        System.out.println("Podaj czas trwania aktywności (w godzinach):");
        double timeInHours = scanner.nextDouble();

        System.out.println("Podaj skalę wyczerpania (od 1 do 10):");
        int exhaustedScale = scanner.nextInt();
        if (exhaustedScale > 10 || exhaustedScale < 1) {
            System.out.println("Blad skala dziala od 1 do 10 podaj jeszcze raz");
            exhaustedScale = scanner.nextInt();
        }
        System.out.println("Podaj opis aktywności:");
        scanner.nextLine();
        String description = scanner.nextLine();


        Activity activity = new Activity(LocalDate.now(), activityName, timeInHours, exhaustedScale, description);


        System.out.println("Dodano nową aktywność.");
        return activity;

    }


}
