package entity.user;

public enum UserTypes {
    //obiekty:
    MODERATOR("abvc"), ADMIN("fg"), USER("fgh");

    private String text;

    private UserTypes(String text){
        this.text = text;
    }

    public void sayHello() {

        System.out.println("Hello");
    }
}
