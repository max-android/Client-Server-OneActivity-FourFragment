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

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ContributorsFragment extends Fragment {
    private RecyclerView recyclerView;

    private  String nameRepo;


    public ContributorsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =
                inflater.inflate(R.layout.contributorsfragment, container, false);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.contribrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        loadDataIntoContributorsFragment();

    }

    private void loadDataIntoContributorsFragment() {

        MainActivity mainActivity=(MainActivity) getActivity();

        nameRepo=getArguments().getString(mainActivity.NAME_REPO);

        SquareReposApplication app = (SquareReposApplication) getContext().getApplicationContext();
        GitHubService gitHubService = app.getGitHubService();


        gitHubService.contributors("square",nameRepo).enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                if (response.isSuccessful()) {
                    List<Contributor> arrayList = response.body();

                    recyclerView.setAdapter(new ContributorsAdapter(arrayList, Glide.with(ContributorsFragment.this)));
                }else {
                    Toast.makeText(getContext(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {

                Toast.makeText(getContext(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
