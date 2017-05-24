package exampleprilognew.ru.client_server_oneactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Максим on 14.05.2017.
 */



public class MainFragment  extends Fragment implements MainAdapter.RepoClickListener{

    private RecyclerView recyclerView;
    private MainFragmentInterface listener;


    public interface MainFragmentInterface{

        void showFragmentAndTransferObjRepo(final Repo repoObj);

    }

    public MainFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =
                inflater.inflate(R.layout.mainfragment, container, false);

return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=(RecyclerView)view.findViewById(R.id.mainrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        loadPost();

    }

    private void loadPost(){

       SquareReposApplication app = (SquareReposApplication) getContext().getApplicationContext();
       GitHubService gitHubService = app.getGitHubService();

        gitHubService.repos("square").enqueue(new Callback<List<Repo>>() {
                                  @Override
                                  public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

                                      if (response.isSuccessful()) {

                                          List<Repo> arrayList = response.body();


                                          recyclerView.setAdapter(new MainAdapter(arrayList, MainFragment.this));

                                      }else {

                                          Toast.makeText(getContext(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
                                      }
                                  }

                                  @Override
                                  public void onFailure(Call<List<Repo>> call, Throwable t) {
                                      Toast.makeText(getContext(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
                                  }
                              }
        );
    }

    @Override
    public void onRepoClick(Repo repo) {

        listener.showFragmentAndTransferObjRepo(repo);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener=(MainFragmentInterface) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();

        listener=null;

    }

}
