package jakub.krezolek.githubapi.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakub.krezolek.githubapi.dto.BranchesResponseDTO;
import jakub.krezolek.githubapi.dto.RepositoryResponseDTO;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


@Service
public class RequestService implements RequestServiceInterface {

    public void RequestRepositoriesByName( String name ){
        Gson gson = new GsonBuilder().create();

        try {
            URL url = new URL("https://api.github.com/users/"+name+"/repos");

            //create a reader to read the URL
            InputStreamReader reader = new InputStreamReader(url.openStream());

            RepositoryResponseDTO[] repositoryResponseDTOList = gson.fromJson(reader, RepositoryResponseDTO[].class);
            reader.close();

            for ( RepositoryResponseDTO repository: repositoryResponseDTOList ){
                System.out.println(repository.name+"\n\t"+repository.owner.login+"\n\t"+repository.branches_url+"\n");
                URL branches_url = new URL("https://api.github.com/repos/"+repository.owner.login+"/"+repository.name+"/branches");

                //create a reader to read the URL
                InputStreamReader branches_reader = new InputStreamReader(branches_url.openStream());

                BranchesResponseDTO[] branchesResponseDTOSList = gson.fromJson(branches_reader, BranchesResponseDTO[].class);
                branches_reader.close();
                for ( BranchesResponseDTO branch: branchesResponseDTOSList ){
                    System.out.println("\t\t"+branch.name+"\n\t\t"+branch.commit.sha);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
