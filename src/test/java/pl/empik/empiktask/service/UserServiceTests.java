package pl.empik.empiktask.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.empik.empiktask.TestDataMother;
import pl.empik.empiktask.client.GitHubApiClient;
import pl.empik.empiktask.converter.GitHubUser2UserConverter;
import pl.empik.empiktask.exception.ResourceNotFoundException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests extends TestDataMother {

    @InjectMocks
    private UserService userService;

    @Mock
    private GitHubApiClient gitHubApiClient;

    @Mock
    private GitHubUser2UserConverter gitHubUser2UserConverter;

    @Test
    public void shouldReturnUserWithCalculations_whenUserHasFollowers() {
        when(gitHubApiClient.getUserByLogin(VALID_USER_LOGIN)).thenReturn(getGitHubUserWithFollowers());
        when(gitHubUser2UserConverter.convert(getGitHubUserWithFollowers())).thenReturn(getUserWithFollowers());

        var actual = userService.getUser(VALID_USER_LOGIN);
        Assertions.assertEquals(getUserWithFollowers(), actual);
    }

    @Test
    public void shouldReturnUserWithZeroAsCalculations_whenUserHasNotFollowers() {
        when(gitHubApiClient.getUserByLogin(VALID_USER_LOGIN)).thenReturn(getGitHubUserWithoutFollowers());
        when(gitHubUser2UserConverter.convert(getGitHubUserWithoutFollowers())).thenReturn(getUserWithoutFollowers());

        var actual = userService.getUser(VALID_USER_LOGIN);
        Assertions.assertEquals(getUserWithoutFollowers(), actual);
    }

    @Test
    public void shouldThrowException_whenUserNotExists() {
        when(gitHubApiClient.getUserByLogin(NOT_FOUND_USER_LOGIN)).thenThrow(new ResourceNotFoundException());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.getUser(NOT_FOUND_USER_LOGIN));
    }

}
