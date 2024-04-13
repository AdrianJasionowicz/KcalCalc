package repository;

import entity.activity.Activity;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ActivityRepository {
    private LocalDate date;
    private List<Activity> activityList;
    private Map<Integer, Activity> activityMap;

    public ActivityRepository(List<Activity> activityList, Map<Integer, Activity> activityMap) {
        this.activityList = activityList;
        this.activityMap = activityMap;
    }


    public Optional<Activity> getActivityById(Integer id) throws IOException {
        Optional<Activity> optionalActivity = Optional.ofNullable(activityMap.get(id));

        if (optionalActivity.isEmpty()) {
            throw new IOException("No Activity found with this id");

        }
        return optionalActivity;
    }

    public void deleteActivityById(Integer Id) {
        activityMap.remove(Id);
    }
    public void addActivity(Activity activity) {
        int newId = generateNewId();

        activity.setId(newId);

        activityMap.put(newId, activity);

        activityList.add(activity);
    }

    private int generateNewId() {
        int maxId = activityMap.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);
        return maxId + 1;
    }

    public List<Activity> getAllActivities() {
        return new ArrayList<>(activityMap.values());
    }

    public void updateActivity(Activity activity) throws IOException {
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
