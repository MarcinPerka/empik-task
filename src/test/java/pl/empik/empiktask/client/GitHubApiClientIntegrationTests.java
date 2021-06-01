package pl.empik.empiktask.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.empik.empiktask.TestDataMother;
import pl.empik.empiktask.exception.ResourceNotFoundException;

@SpringBootTest
public class GitHubApiClientIntegrationTests extends TestDataMother {

    @Autowired
    private GitHubApiClient gitHubApiClient;

    @Test
    public void shouldReturnValidUser_whenUserExists() {
        var user = gitHubApiClient.getUserByLogin(VALID_USER_LOGIN);

        Assertions.assertEquals(VALID_USER_LOGIN, user.login());
    }

    @Test
    public void shouldThrowResourceNotFoundException_whenUserNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> gitHubApiClient.getUserByLogin(NOT_FOUND_USER_LOGIN));
    }

    @Test
    public void shouldThrowResourceNotFoundException_whenUserLoginIsBlank() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> gitHubApiClient.getUserByLogin(BLANK_USER_LOGIN));
    }

}
