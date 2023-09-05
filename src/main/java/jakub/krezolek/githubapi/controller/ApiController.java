package jakub.krezolek.githubapi.controller;

import jakub.krezolek.githubapi.dto.RepositoriesDTO;
import jakub.krezolek.githubapi.service.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/github")
public class ApiController {

    private final RequestService requestService;

    public ApiController(RequestService requestService) {
        this.requestService = requestService;
    }

    @CrossOrigin
    @GetMapping(value = "/repos/{name}",
            produces = "application/json")
    public ResponseEntity<RepositoriesDTO> getAllNonForkRepositories(@PathVariable String name){
        Optional<RepositoriesDTO> repositoriesDTOOptional = requestService.RequestRepositoriesByName(name);
        if( repositoriesDTOOptional.isEmpty() ) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(repositoriesDTOOptional.get());
    }

}
