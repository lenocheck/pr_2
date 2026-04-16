@Init("MyService")
public class MyService {
    @SetValue("тестик")
    private String message;

    @SetValue("55")
    private int code;

    @Override
    public String toString() {
        return "MyService{message='" + message + "', code=" + code + "}";
    }
}