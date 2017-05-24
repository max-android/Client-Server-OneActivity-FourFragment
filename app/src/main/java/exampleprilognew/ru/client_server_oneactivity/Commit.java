package exampleprilognew.ru.client_server_oneactivity;

/**
 * Created by Максим on 14.05.2017.
 */

public class Commit  {
    private CommitInfo commit;

    public Commit(CommitInfo commit) {this.commit = commit;}

    public CommitInfo getCommitInfo() {
        return commit;
    }

    public class CommitInfo {
        private String message;

        private CommitInfo(String message) {this.message = message;}

        public String getMessage() {
            return message;
        }
    }
}
