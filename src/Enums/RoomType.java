package Enums;

public enum RoomType {
    SINGLE ("Single Room",50.00),
    DOUBLE ("Double Room",75.00),
    TRIPLE ("Triple Room",100.00);

    private final String description;
    private final double price;

    RoomType(String description, double basePrice) {
        this.description = description;
        this.price = basePrice;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

}
