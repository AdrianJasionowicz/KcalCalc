package service.food;

import entity.food.Food;
import repository.FoodRepository;

public class FoodService {
    private FoodRepository foodRepository;
    private Food food;


    public FoodService(FoodRepository foodRepository, Food food) {
        this.foodRepository = foodRepository;
        this.food = food;
    }








}
