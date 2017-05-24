package exampleprilognew.ru.client_server_oneactivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;



public class CommitsAdapter extends RecyclerView.Adapter<CommitsAdapter.CommitViewHolder> {

    private final List<Commit> commits;



    public CommitsAdapter(List<Commit> commits) {
        this.commits = commits;

    }

    @Override
    public CommitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from( parent.getContext());

        View view=inflater.inflate(R.layout.commits_item,parent,false);

        return  new CommitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommitViewHolder holder, int position) {

        Commit commit= commits.get(position);

        holder.bindTo(commit);

    }

    @Override
    public int getItemCount() {
        return commits.size();
    }

    class CommitViewHolder extends RecyclerView.ViewHolder{


        private final TextView tvCommit;


        public CommitViewHolder(View itemView){
            super(itemView);

            tvCommit =(TextView)itemView.findViewById(R.id.tvCommit);

        }

        public void bindTo(Commit commit) {


            tvCommit.setText(commit.getCommitInfo().getMessage());


        }
    }

}
