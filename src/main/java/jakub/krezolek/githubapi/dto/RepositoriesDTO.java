package jakub.krezolek.githubapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepositoriesDTO {

    ArrayList<Repository> repositories;
    @Getter
    @Builder
    public static class Repository {

        String owner_name;
        String name;
        ArrayList<Branch> branches;

        @Getter
        @Builder
        public static class Branch {

            String name;
            String last_sha;

        }

    }

}
