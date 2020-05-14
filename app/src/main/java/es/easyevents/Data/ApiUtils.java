package es.easyevents.Data;

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://10.0.2.2:7777";

    public static MailInterface getAPIService() {

        return RetrofitClient.getInstance(BASE_URL).create(MailInterface.class);
    }
}
