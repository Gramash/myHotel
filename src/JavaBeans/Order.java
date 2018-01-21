package JavaBeans;


import java.sql.Date;

public class Order {
    private int roomNo;
    private Date checkIn;
    private Date checkOut;
    private int sleeps;
    private double price;
    private boolean paid;
    private UserAccount user;

    public Order(int roomNo, int sleeps, Date checkIn, Date checkOut, double price) {
        this.roomNo = roomNo;
        this.sleeps = sleeps;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.price = price;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public Order(int roomNo, int sleeps, Date checkIn, Date checkOut, double price, boolean paid, UserAccount user) {
        this.roomNo = roomNo;
        this.sleeps = sleeps;
        this.checkIn = checkIn;
        this.checkOut = checkOut;

        this.price = price;
        this.paid = paid;
        this.user = user;

    }


    public Order() {
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
