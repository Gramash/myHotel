package JavaBeans;

public class Product {
    private int roomNo;
    private double price;
    private int sleeps;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSleeps() {
        return sleeps;
    }

    public void setSleeps(int sleeps) {
        this.sleeps = sleeps;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("room # ==> ").append(roomNo).append(", ")
                .append("sleeps ==> ").append(sleeps).append(", ")
                .append("price ==> ").append(price);
        return result.toString();
    }
}
