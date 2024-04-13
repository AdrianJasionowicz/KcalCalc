package repository;

import entity.activity.Activity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivityRepository {
    private LocalDate date;
    private final List<Activity> activityList = new ArrayList<>();


    public Optional<Activity> getActivityById(Integer id) {
        for (Activity activity : activityList) {
           if ( activity.getId().equals(id) ) {
               return Optional.of(activity);
           }
        }
        return Optional.empty();

    }

    public void deleteActivityById(Integer id) {
      /*  for (int i = 0; i < activityList.size(); i++) {
            if (activityList.get(i).getId().equals(id)) {
                activityList.remove(i);
                break;
            }
        }*/
      /*  for (int i = 0; i < activityList.size(); i++) {
            Activity activity = activityList.get(i);
            if (activity.getId().equals(id)) {
                activityList.remove(activity);
                break;
            }
        }*/

        for ( Activity activity : new ArrayList<>( activityList)) {
            if (activity.getId().equals(id)) {
                activityList.remove(activity);
                break;
            }
        }
    }
    public void addActivity(Activity activity) {
        int newId = generateNewId();

        activity.setId(newId);


        activityList.add(activity);
    }

    private int generateNewId() {
        int maxId =0;
        for (Activity activity : activityList) {
            if (activity.getId() > maxId) {
                maxId = activity.getId();
            }
        }
        return maxId + 1;
    }

    public List<Activity> getAllActivities() {
        return activityList;
    }

    public void updateActivity(Activity activity) {
        Integer activityId = activity.getId();
        Optional<Activity> optionalActivity = getActivityById(activityId);

        if (optionalActivity.isPresent()) {
            Activity existingActivity = optionalActivity.get();
            existingActivity.setDate(activity.getDate());
            existingActivity.setActivityName(activity.getActivityName());
            existingActivity.setTimeInHours(activity.getTimeInHours());
            existingActivity.setExhaustedScale(activity.getExhaustedScale());
            existingActivity.setDescription(activity.getDescription());
        } else {
            System.out.println("Nie można zaktualizować aktywności. Brak aktywności o identyfikatorze: " + activityId);
        }
    }
    public void addActivityToList(Activity activity) {
        activityList.add(activity);
    }
}
