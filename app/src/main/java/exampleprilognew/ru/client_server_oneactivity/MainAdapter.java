package exampleprilognew.ru.client_server_oneactivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;



public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RepoViewHolder> {

    private final List<Repo> repos;
    private final RepoClickListener listener;


    public MainAdapter(List<Repo> repos, RepoClickListener listener) {
        this.repos=repos;
        this.listener=listener;

    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from( parent.getContext());

        View view=inflater.inflate(R.layout.square_item,parent,false);

        return  new RepoViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {

        Repo squreRepo=repos.get(position);

        holder.bindTo(squreRepo);

    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    class RepoViewHolder extends RecyclerView.ViewHolder{


        private final TextView tvRepo;
        private final TextView tvStars;
        private final TextView tvForks;

        private Repo repoObj;

        public RepoViewHolder(View itemView, final RepoClickListener listener){
            super(itemView);

            tvRepo=(TextView)itemView.findViewById(R.id.tvRepo);
            tvStars=(TextView)itemView.findViewById(R.id.tvStars);
            tvForks=(TextView)itemView.findViewById(R.id.tvForks);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRepoClick(repoObj);
                }
            });

        }


        public void bindTo(Repo repoObj) {
               this.repoObj=repoObj;

            tvRepo.setText(repoObj.getName());
            tvStars.setText(String.valueOf(repoObj.getStarsCount()));
            tvForks.setText(String.valueOf(repoObj.getForksCount()));


        }
    }


interface RepoClickListener {

    void onRepoClick(Repo repoObj);

}
}
