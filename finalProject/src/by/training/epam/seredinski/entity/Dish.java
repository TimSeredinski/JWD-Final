package by.training.epam.seredinski.entity;

public class Dish {

    public enum DishType {
        PIZZA, BURGER, DESSERT
    }

    private int id;
    private String name;
    private String description;
    private DishType type;
    private int weight;
    private int price;

    public Dish() {
    }

    public Dish(String name, String description, DishType type, int weight, int price) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.weight = weight;
        this.price = price;
    }

    public Dish(int id, String name, String description, DishType type, int weight, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.weight = weight;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DishType getType() {
        return type;
    }

    public void setType(DishType type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish that = (Dish) o;
        if (Integer.compare(that.id, this.id) != 0) {
            return false;
        }
        if (!(that.name != null ? that.name.equals(this.name) : this.name == null)) {
            return false;
        }
        if (!(that.description != null ? that.description.equals(this.description) : this.description == null)) {
            return false;
        }
        if (Integer.compare(that.weight, this.weight) != 0) {
            return false;
        }
        if (Integer.compare(that.price, this.price) != 0) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 829;
        result = prime * result + id;
        result = prime * result + name.length();
        result = prime * result + description.length();
        result = prime * result + type.toString().length();
        result = prime * result + weight;
        result = prime * result + price;
        return result;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }
}
