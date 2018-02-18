package MySQL.JavaBeans;

public class Application {
    private String applId;
    private int sleeps;
    private String checkIn;
    private String checkOut;
    private String email;
    private String name;
    private String clazz;
    private int roomNo;
    private double price;
    private String image;

    public Application(String applId, int sleeps, String clazz, String checkIn, String checkOut, String email, String name, int userId) {
        this.applId = applId;
        this.sleeps = sleeps;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.clazz = clazz;
    }
    public Application(String applId, int sleeps, String clazz, String checkIn,
                       String checkOut, int roomNo, double price, String image) {
        this.applId = applId;
        this.sleeps = sleeps;
        this.checkIn = checkIn;
        this.checkOut = checkOut;

        this.clazz = clazz;
        this.roomNo = roomNo;
        this.price = price;
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

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    private int userId;


    public String getApplId() {
        return applId;
    }

    public void setApplId(String applId) {
        this.applId = applId;
    }


    public int getSleeps() {
        return sleeps;
    }

    public void setSleeps(int sleeps) {
        this.sleeps = sleeps;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
