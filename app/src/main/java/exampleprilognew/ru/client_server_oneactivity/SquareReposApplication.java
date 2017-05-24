package exampleprilognew.ru.client_server_oneactivity;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Максим on 15.05.2017.
 */


public class SquareReposApplication extends Application {

    private GitHubService gitHubService;

    @Override
    public void onCreate() {
        super.onCreate();
        gitHubService = createGitHubService();
    }

    private GitHubService createGitHubService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GitHubService.class);
    }

    public GitHubService getGitHubService() {
        return gitHubService;
    }

}