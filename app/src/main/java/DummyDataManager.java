import java.util.ArrayList;
import java.util.List;

public class DummyDataManager implements DataManager {

    public DummyDataManager() { }

    @Override
    public String login(String name, String password) {
        String token = "dummytoken";
        return token;
    }

    @Override
    public String register(String name, String nickname, String password) {
        String token = "dummytoken";
        return token;
    }

    @Override
    public List<ChatInfo> getContacts(String token) {
        List<ChatInfo> list = new ArrayList<>();
        return list;
    }

    @Override
    public boolean sendMessage(String token, String destination, String textMessage) {

        return true;
    }

    @Override
    public void getMessage() {

    }


}
