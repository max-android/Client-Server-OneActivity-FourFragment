package exampleprilognew.ru.client_server_oneactivity;

/**
 * Created by Максим on 14.05.2017.
 */


public class Contributor  {
    private String login;
    private String avatar_url;

    public Contributor(String login, String avatar_url) {
        this.login = login;
        this.avatar_url = avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatar_url;
    }
}