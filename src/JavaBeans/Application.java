package JavaBeans;

public class Application {
    private String applId;
    private int sleeps;
    private String checkIn;
    private String checkOut;
    private String email;
    private String name;
    private int userId;


    public Application(String applId, int sleeps, String checkIn, String checkOut, String email, String name, int userId) {
        this.applId = applId;
        this.sleeps = sleeps;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.email = email;
        this.name = name;
        this.userId = userId;
    }

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
