package pl.empik.empiktask.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import pl.empik.empiktask.TestDataMother;
import pl.empik.empiktask.exception.ResourceNotFoundException;
import pl.empik.empiktask.model.GitHubUser;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GitHubApiClientTests extends TestDataMother {

    @InjectMocks
    private GitHubApiClient gitHubApiClient;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void shouldReturnValidUser_whenUserExists() {
        when(restTemplate.getForObject(GITHUB_API_PATH, GitHubUser.class, VALID_USER_LOGIN)).thenReturn(getGitHubUserWithFollowers());

        var user = gitHubApiClient.getUserByLogin(VALID_USER_LOGIN);

        Assertions.assertEquals(getGitHubUserWithFollowers(), user);
    }

    @Test
    public void shouldThrowResourceNotFoundException_whenUserNotExists() {
        when(restTemplate.getForObject(GITHUB_API_PATH, GitHubUser.class, NOT_FOUND_USER_LOGIN)).thenThrow(new ResourceNotFoundException());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> gitHubApiClient.getUserByLogin(NOT_FOUND_USER_LOGIN));
    }

    @Test
    public void shouldThrowResourceNotFoundException_whenUserLoginIsBlank() {
        when(restTemplate.getForObject(GITHUB_API_PATH, GitHubUser.class, BLANK_USER_LOGIN)).thenThrow(new ResourceNotFoundException());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> gitHubApiClient.getUserByLogin(BLANK_USER_LOGIN));
    }

}
