package service.activity;

import entity.activity.Activity;
import entity.user.User;
import repository.ActivityRepository;
import service.user.UserService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class ActivityService {
    private ActivityRepository activityRepository;
    private Activity activity;
    private User loggedUser;
    private UserService userService;
    private List<Activity> activityList;
    private Map<Integer, Activity> activityMap;
    Scanner scanner = new Scanner(System.in);

    public ActivityService(ActivityRepository activityRepository, Activity activity, UserService userService, List<Activity> activityList, Map<Integer, Activity> activityMap) {
        this.activityRepository = activityRepository;
        this.activity = activity;
        this.activityList = activityList;
        this.activityMap = activityMap;
        this.userService = userService;
        loggedUser = userService.getLoggedUserOrThrow();
    }

    public void addActivity(Activity activity) {
        activityRepository.addActivity(activity);
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


    public void checkActivities() {
        List<Activity> allActivities = activityRepository.getAllActivities();
        if (!allActivities.isEmpty()) {
            System.out.println("Lista wszystkich aktywności:");
            for (Activity activity : allActivities) {

                System.out.println("ID: " + activity.getId());
                System.out.println("Nazwa: " + activity.getActivityName());
                System.out.println("Czas trwania: " + activity.getTimeInHours() + " godzin");
                System.out.println("Stopień wyczerpania: " + activity.getExhaustedScale());
                System.out.println("Opis: " + activity.getDescription());
                System.out.println("-------------------------");
            }
        } else {
            System.out.println("Brak aktywności do wyświetlenia.");
        }
    }

    public void editActivity(int activityId) throws IOException {
        Optional<Activity> optionalActivity = activityRepository.getActivityById(activityId);
        if (optionalActivity.isPresent()) {
            Activity activity = optionalActivity.get();
            System.out.println("Aktualne informacje o aktywności:");
            System.out.println(activity);

            activityRepository.updateActivity(activity);
        } else {
            System.out.println("Nie znaleziono aktywności o podanym identyfikatorze: " + activityId);
        }
    }

    public void deleteActivity(int activityId) {
        activityRepository.deleteActivityById(activityId);
    }

    public void saveActivities() {
        try {
            String fileName = "activity_" + loggedUser.getUsername() + ".txt";
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter writer = new PrintWriter(fileWriter);
            List<Activity> allActivities = activityRepository.getAllActivities();
            for (Activity activity : allActivities) {
                writer.println(activity.getId() + "," + activity.getDate() + "," + activity.getActivityName() + "," +
                        activity.getTimeInHours() + "," + activity.getExhaustedScale() + "," + activity.getDescription());
            }
            writer.close();

            System.out.println("Zapisano aktywności do pliku: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadActivities() {
        try {
            String fileName = "activity_" + loggedUser.getUsername() + ".txt";
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");

                    Integer id = Integer.parseInt(parts[0]);
                    LocalDate date = LocalDate.parse(parts[1]);
                    String activityName = parts[2];
                    double timeInHours = Double.parseDouble(parts[3]);
                    int exhaustedScale = Integer.parseInt(parts[4]);
                    String description = parts[5];

                    Activity activity = new Activity(date, activityName, timeInHours, exhaustedScale, description);
                    activity.setId(id);
                    activityRepository.addActivity(activity);
                    activityMap.put(id, activity);
                }
                scanner.close();
            } else {
                System.out.println("Plik z aktywnościami nie istnieje.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }


    public void updateActivity(Activity activity) throws IOException {
        activityRepository.updateActivity(activity);
    }

    public void deleteActivityById(int activityId) {
        activityRepository.deleteActivityById(activityId);
    }
}
