package pl.empik.empiktask.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.empik.empiktask.TestDataMother;

public class GitHubUser2UserConverterTests extends TestDataMother {

    private final GitHubUser2UserConverter gitHubUser2UserConverter = new GitHubUser2UserConverter();

    @Test
    public void shouldConvertGitHubUser2UserAndDoCalculations_whenFollowersAreBiggerThanZero() {
        var actual = gitHubUser2UserConverter.convert(getGitHubUserWithFollowers());
        Assertions.assertEquals(getUserWithFollowers(), actual);
    }

    @Test
    public void shouldConvertGitHubUser2UserAndReturnZeroAsCalculation_whenFollowersAreEqualZero() {
        var actual = gitHubUser2UserConverter.convert(getGitHubUserWithoutFollowers());
        Assertions.assertEquals(getUserWithoutFollowers(), actual);
    }
}
