package JavaBeans;

public class Product {
    private int roomNo;
    private double price;
    private int sleeps;
    private String image;
    private boolean taken;
    private boolean available;

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    private String clazz;

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

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
        return "Product{" +
                "roomNo=" + roomNo +
                ", price=" + price +
                ", sleeps=" + sleeps +
                ", image='" + image + '\'' +
                ", taken=" + taken +
                ", available=" + available +
                ", clazz='" + clazz + '\'' +
                '}' + "\n";
    }
}
