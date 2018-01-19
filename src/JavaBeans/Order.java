package JavaBeans;


import java.sql.Date;

public class Order {
    int roomNo;
    Date checkIn;
    Date checkOut;
    int sleeps;
    double price;


    public Order(int roomNo, int sleeps, Date checkIn, Date checkOut, double price){
        this.roomNo = roomNo;
        this.sleeps = sleeps;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.price = price;
    }

    public Order(){}

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


}
