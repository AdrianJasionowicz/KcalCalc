package entity.food;

public class Food {
    private int id;
    private String name;
    private int weight;
    private int portion;
    private int kcalPerPorion;
    private int kcalPer100g;
    private int proteins;
    private int fat;
    private int carbohydrates;


    public FoodType getType() {
        if (proteins > fat && proteins > carbohydrates ) {
            return FoodType.PROTEIN;
        } else if (fat > proteins && fat > carbohydrates) {
            return FoodType.FAT;
        } else return FoodType.CARBO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }

    public int getKcalPerPorion() {
        return kcalPerPorion;
    }

    public void setKcalPerPorion(int kcalPerPorion) {
        this.kcalPerPorion = kcalPerPorion;
    }

    public int getKcalPer100g() {
        return kcalPer100g;
    }

    public void setKcalPer100g(int kcalPer100g) {
        this.kcalPer100g = kcalPer100g;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Food(int id, String name, int weight, int portion, int kcalPerPorion, int kcalPer100g, int proteins, int fat, int carbohydrates) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.portion = portion;
        this.kcalPerPorion = kcalPerPorion;
        this.kcalPer100g = kcalPer100g;
        this.proteins = proteins;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
    }
}