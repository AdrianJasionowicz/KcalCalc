package repository;

import entity.food.Food;
import entity.food.FoodType;

import java.util.List;

public class FoodRepository {
    private List<Food> foodList;


    public void getFoodByType(FoodType foodType) {
        for (Food food : foodList) {
            if(foodType.equals(foodType)) {
                System.out.println(food);
            }
        }
    }

    public void addFood(Food food) {
        foodList.add(food);
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFood(int id,Food food) {
        for (Food food1 : foodList) {
            if (food1.getId() == id ) {
                food1.setName(food.getName());
                food1.setWeight(food.getWeight());
                food1.setPortion(food.getPortion());
                food1.setKcalPer100g(food.getKcalPer100g());
                food1.setProteins(food.getProteins());
                food1.setCarbohydrates(food.getCarbohydrates());
                food1.setFat(food.getFat());

        }
        }
    }

    public List<Food> showFood() {
        return foodList;
    }


}
