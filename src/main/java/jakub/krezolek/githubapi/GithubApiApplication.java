package jakub.krezolek.githubapi;

import jakub.krezolek.githubapi.service.RequestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GithubApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubApiApplication.class, args);
		RequestService requestService = new RequestService();
		requestService.RequestRepositoriesByName("kuborek2");
	}

}
