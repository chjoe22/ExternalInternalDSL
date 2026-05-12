package model;

public class RandomItemEvent extends GameEvent {

    private String name;
    private int quantity;
    private String description;

    public RandomItemEvent(String name, int quantity, String description) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }

    @Override
    public String toString() {
        return "        random_item " + name
                + " found " + quantity
                + " \"" + description + "\"";
    }
}