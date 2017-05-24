package exampleprilognew.ru.client_server_oneactivity;

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

public class CommitsFragment extends Fragment {
    private RecyclerView recyclerView;


    private  String nameRepo;

    public CommitsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =
                inflater.inflate(R.layout.commitsfragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.commitsrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        loadDataIntoCommitsFragment();

    }


    private void loadDataIntoCommitsFragment() {

        MainActivity mainActivity=(MainActivity) getActivity();

        nameRepo=getArguments().getString(mainActivity.NAME_REPO);


       SquareReposApplication app = (SquareReposApplication) getContext().getApplicationContext();
        GitHubService gitHubService = app.getGitHubService();


        gitHubService.commits("square",nameRepo).enqueue(new Callback<List<Commit>>() {
            @Override
            public void onResponse(Call<List<Commit>> call, Response<List<Commit>> response) {
                if (response.isSuccessful()) {
                    List<Commit> arrayList = response.body();

                    recyclerView.setAdapter(new CommitsAdapter(arrayList));
                }else {

                    Toast.makeText(getContext(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Commit>> call, Throwable t) {

                Toast.makeText(getContext(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
