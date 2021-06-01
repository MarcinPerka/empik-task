package pl.empik.empiktask.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.empik.empiktask.model.GitHubUser;

@Component
@Slf4j
@RequiredArgsConstructor
public class GitHubApiClient {

    private final RestTemplate restTemplate;

    private final String BASE_URL = "https://api.github.com/users/";

    private final String GET_USER_BY_LOGIN = BASE_URL + "{login}";

    public GitHubUser getUserByLogin(final String login) {
        try {
            return restTemplate.getForObject(GET_USER_BY_LOGIN, GitHubUser.class, login);
        } catch (Exception e) {
            log.error("Error occurred: {}", e.getMessage());
            throw e;
        }
    }

}
