package constants;

public class Constants {


    public static   final int PORT = 3000;

    public static final String  HOST_DATABASE ="jdbc:postgresql://localhost:5432/";
    public static final String  NAME_DATABASE ="postgres";
    public static final String  USER_DATABASE ="postgres";
    public static final String  PASSWORD_DATABASE ="123456";

    public static final String USERS_TABLE = "users";
    public static final String ID="id";
    public static final String NAME_USER = "name";
    public static final String LAST_NAME_USER = "lastName";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLL = "roll";


    public static final String DISCOUNTS_TABLE = "discounts";
    public static final String DISCOUNT_ID = "id";
    public static final String DISCOUNT_NAME = "name";
    public static final String DISCOUNT_SURNAME = "surname";
    public static final String DISCOUNT_NUMBER = "dnumber";
    public static final String DISCOUNT_SHOP = "shopName";

    public static final String SHOP_TABLE = "shops";
    public static final String SHOP_ID="id";
    public static final String SHOP_NAME = "name";
    public static final String SHOP_ADDRESS = "shopAddress";

    public static final String SHOPDISC_TABLE = "shopDisc";
    public static final String SHOPDISC_ID="id";
    public static final String SHOPDISC_NAME = "shopDiscName";
    public static final String DISC_LIST = "discList";

    public static final String ADMIN_TABLE = "admins";
    public static final String ADMIN_ID = "id";
    public static final String ADMIN_LOGIN = "login";
    public static final String ADMIN_PASSWORD = "password";



}
