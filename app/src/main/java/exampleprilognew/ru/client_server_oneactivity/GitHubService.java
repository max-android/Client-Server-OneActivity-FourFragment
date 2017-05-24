package exampleprilognew.ru.client_server_oneactivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Максим on 14.05.2017.
 */


public interface GitHubService {

    @GET("users/{user}/repos")
    Call<List<Repo>> repos(@Path("user") String user);

    @GET("repos/{user}/{repo}/commits")
    Call<List<Commit>> commits(@Path("user") String user, @Path("repo") String repo);

    @GET("repos/{user}/{repo}/contributors")
    Call<List<Contributor>> contributors(@Path("user") String user, @Path("repo") String repo);
}