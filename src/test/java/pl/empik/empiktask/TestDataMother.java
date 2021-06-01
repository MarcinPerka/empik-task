package pl.empik.empiktask;

import pl.empik.empiktask.model.GitHubUser;
import pl.empik.empiktask.model.User;
import pl.empik.empiktask.model.UserRequestCounter;

import java.math.BigDecimal;
import java.time.Instant;

public class TestDataMother {

    public final String GITHUB_API_PATH = "https://api.github.com/users/{login}";

    public final String API_PREFIX = "/users/";

    public final String VALID_USER_API_PATH = API_PREFIX + "octocat";

    public final String NOT_FOUND_USER_API_PATH = API_PREFIX + "notfounduser";

    public final String BLANK_USER_API_PATH = API_PREFIX + "";

    public final String MOCK_ERROR_API_PATH = API_PREFIX + "test";

    public final String MOCK_ERROR_USER_LOGIN = "test";

    public final String NOT_FOUND_USER_LOGIN = "notfounduser";

    public final String BLANK_USER_LOGIN = "";

    public final String VALID_USER_LOGIN = "octocat";

    public UserRequestCounter getUserRequestCounter() {
        return UserRequestCounter.create(VALID_USER_LOGIN);
    }

    public User getUserWithFollowers() {
        return User.builder()
                .id(123L)
                .login(VALID_USER_LOGIN)
                .type("User")
                .name(VALID_USER_LOGIN)
                .createdAt(Instant.MAX)
                .avatarUrl("someUrl")
                .calculations(new BigDecimal("10.0000"))
                .build();
    }

    public GitHubUser getGitHubUserWithFollowers() {
        return new GitHubUser(123L, VALID_USER_LOGIN, VALID_USER_LOGIN, "User",
                6L, "someUrl", Instant.MAX, 8L);
    }

    public GitHubUser getGitHubUserWithoutFollowers() {
        return new GitHubUser(123L, VALID_USER_LOGIN, VALID_USER_LOGIN, "User",
                0L, "someUrl", Instant.MAX, 8L);
    }

    public User getUserWithoutFollowers() {
        return User.builder()
                .id(123L)
                .login(VALID_USER_LOGIN)
                .type("User")
                .name(VALID_USER_LOGIN)
                .createdAt(Instant.MAX)
                .avatarUrl("someUrl")
                .calculations(BigDecimal.ZERO)
                .build();
    }

}
