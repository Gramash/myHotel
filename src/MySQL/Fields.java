package MySQL;

public final class Fields {

    //products table
    public static final String ROOM_NO = "roomNo";
    public static final String SLEEPS = "sleeps";
    public static final String CLASS = "class";
    public static final String PRICE = "price";
    public static final String IMAGE = "image";
    public static final String IS_TAKEN = "isTaken";
    public static final String AVAILABLE = "available";

    //user table
    public static final String USER_ID = "user_id";
    public static final String LOGIN = "login";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String ROLE = "role";

    //Orders table *duplicates excluded*
    public static final String PRODUCT_ID = "product_id";
    public static final String CHECK_IN = "checkIn";
    public static final String CHECK_OUT = "checkOut";
    public static final String PAID = "paid";
    public static final String TS = "ts";

    //applications table *duplicates excluded*
    public static final String STRING = "application_id";
    public static final String COMPLETED = "completed";

}
