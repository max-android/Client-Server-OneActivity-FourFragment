package exampleprilognew.ru.client_server_oneactivity;

/**
 * Created by Максим on 14.05.2017.
 */


import java.io.Serializable;

public class Repo implements Serializable {
    private String name;
    private String description;
    private int forks_count;
    private int stargazers_count;

    public Repo(String name, String description, int forks_count, int stargazers_count) {
        this.name = name;
        this.description = description;
        this.forks_count = forks_count;
        this.stargazers_count = stargazers_count;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getForksCount() {
        return forks_count;
    }

    public int getStarsCount() {
        return stargazers_count;
    }
}