package JavaBeans;


import java.sql.Date;

public class Order {
    private int roomNo;
    private Date checkIn;
    private Date checkOut;
    private int sleeps;
    private double price;
    private Date paid;
    private String clazz;

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Date getPaid() {
        return paid;
    }

    public void setPaid(Date paid) {
        this.paid = paid;
    }

    private UserAccount user;

    public Order(int roomNo, int sleeps, Date checkIn, Date checkOut, double price, Date paid, String clazz) {
        this.roomNo = roomNo;
        this.sleeps = sleeps;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.price = price;
        this.paid = paid;
        this.clazz = clazz;
    }

    public Order(){

    }

    public Order(int roomNo, int sleeps, Date checkIn, Date checkOut, double price, Date paid, String clazz, UserAccount user) {
        this.roomNo = roomNo;
        this.sleeps = sleeps;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.price = price;
        this.paid = paid;
        this.user = user;
        this.clazz = clazz;


    }


    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }


    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getSleeps() {
        return sleeps;
    }

    public void setSleeps(int sleeps) {
        this.sleeps = sleeps;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Order{" +
                "roomNo=" + roomNo +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", sleeps=" + sleeps +
                ", price=" + price +
                ", paid=" + paid +
                ", user=" + user +
                '}';
    }
}
