package exampleprilognew.ru.client_server_oneactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentInterface, RepInfoFragment.RepInfoFragmentInterface {

    public static final String OBJ = "obj";
    public static final String NAME_REPO = "nameRepo";

    private RepInfoFragment repInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = new MainFragment();
        showFragment(mainFragment);

    }

    @Override
    public void showFragmentAndTransferObjRepo(Repo repoObj) {

        if (findViewById(R.id.fragmentContainer) != null) {

          repInfoFragment = new RepInfoFragment();

            showFragment(transferObjRepo(repInfoFragment,repoObj));
        }
    }

    @Override
    public void showFragmentAndTransferNameRepo(String nameRepo) {

        if (findViewById(R.id.fragmentContainer) != null){

            if(repInfoFragment.isMarker()){

                CommitsFragment commitsFragment=new CommitsFragment();

                showFragment(transferNameRepo(commitsFragment,nameRepo));

            }else{

                ContributorsFragment contributorsFragment=new ContributorsFragment();

                showFragment(transferNameRepo(contributorsFragment,nameRepo));
            }
        }
    }


    public Fragment transferNameRepo(Fragment fragment,String nameRepo){

        Bundle argsNameForFrag = new Bundle();
        argsNameForFrag.putString(NAME_REPO,nameRepo);
        fragment.setArguments(argsNameForFrag);

        return fragment;
    }


    public Fragment transferObjRepo(Fragment fragment,Repo repoObj){

        Bundle argsObjForFrag = new Bundle();
        argsObjForFrag.putSerializable(OBJ,repoObj);
        fragment.setArguments(argsObjForFrag);

        return fragment;
    }


    public void showFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
