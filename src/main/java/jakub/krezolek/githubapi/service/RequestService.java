package jakub.krezolek.githubapi.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakub.krezolek.githubapi.dto.RepositoriesDTO;
import jakub.krezolek.githubapi.json.BranchesResponseJson;
import jakub.krezolek.githubapi.json.RepositoryResponseJson;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;


@Service
public class RequestService {

    public Optional<RepositoriesDTO> RequestRepositoriesByName(String name ){
        Gson gson = new GsonBuilder().create();

        try {
            URL url = new URL("https://api.github.com/users/"+name+"/repos");

            //create a reader to read the URL
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if( urlConnection.getResponseCode() == 404 )
                return Optional.empty();
            InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());

            RepositoryResponseJson[] repositoryResponseJsonList = gson.fromJson(reader, RepositoryResponseJson[].class);
            reader.close();

            ArrayList<RepositoriesDTO.Repository> repositories = new ArrayList<>();

            for ( RepositoryResponseJson repository: repositoryResponseJsonList ){
                URL branches_url = new URL("https://api.github.com/repos/"+repository.owner.login+"/"+repository.name+"/branches");

                //create a reader to read the URL
                InputStreamReader branches_reader = new InputStreamReader(branches_url.openStream());

                BranchesResponseJson[] branchesResponseJsonList = gson.fromJson(branches_reader, BranchesResponseJson[].class);
                branches_reader.close();

                ArrayList<RepositoriesDTO.Repository.Branch> branches = new ArrayList<>();

                for ( BranchesResponseJson branch: branchesResponseJsonList ){
                    branches.add(
                            RepositoriesDTO.Repository.Branch.builder()
                                    .name(branch.name)
                                    .last_sha(branch.commit.sha)
                                    .build()
                    );
                }

                repositories.add(
                        RepositoriesDTO.Repository.builder()
                                .name(repository.name)
                                .owner_name(repository.owner.login)
                                .branches(branches)
                                .build()
                );

            }

            return Optional.of(RepositoriesDTO.builder()
                    .repositories(repositories)
                    .build());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
