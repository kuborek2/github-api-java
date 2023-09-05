package jakub.krezolek.githubapi.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BranchesResponseJson {
    public String name;
    public Commit commit;
    @JsonProperty("protected")
    public boolean myprotected;

    @Getter
    public class Commit{
        public String sha;
        public String url;
    }
}

