package exampleprilognew.ru.client_server_oneactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Максим on 14.05.2017.
 */

public class RepInfoFragment extends Fragment {

    private Button btnCommit;
    private Button btnContributors;
    private TextView tvRepo;
    private TextView tvDescription;


    private RepInfoFragmentInterface listener;

    private boolean marker;

    public boolean isMarker() {
        return marker;
    }

    public interface RepInfoFragmentInterface{

        void showFragmentAndTransferNameRepo(final String nameRepo);

    }

    public RepInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =
                inflater.inflate(R.layout.repfragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        btnCommit=(Button)view.findViewById(R.id.commits);
        btnContributors=(Button)view.findViewById(R.id.contributors);

        btnCommit.setOnClickListener(commitListener);
        btnContributors.setOnClickListener(contributorsListener);

        tvRepo=(TextView)view.findViewById(R.id.tvRepo);
        tvDescription=(TextView)view.findViewById(R.id.tvDescription);

        MainActivity mainActivity=(MainActivity) getActivity();
        Repo repo =(Repo) getArguments().getSerializable(mainActivity.OBJ);

        tvRepo.setText(repo.getName());
        tvDescription.setText(repo.getDescription());

    }

    private View.OnClickListener commitListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            marker=true;
            listener.showFragmentAndTransferNameRepo(tvRepo.getText().toString());

        }
    } ;

    private View.OnClickListener contributorsListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            marker=false;
            listener.showFragmentAndTransferNameRepo(tvRepo.getText().toString());

        }
    } ;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener=(RepInfoFragmentInterface) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();

        listener=null;

    }

}