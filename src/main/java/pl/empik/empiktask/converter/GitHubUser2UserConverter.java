package pl.empik.empiktask.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.empik.empiktask.model.GitHubUser;
import pl.empik.empiktask.model.User;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class GitHubUser2UserConverter implements Converter<GitHubUser, User> {

    @Override
    public User convert(GitHubUser gitHubUser) {
        return User.builder()
                .id(gitHubUser.id())
                .login(gitHubUser.login())
                .name(gitHubUser.name())
                .type(gitHubUser.type())
                .avatarUrl(gitHubUser.avatarUrl())
                .createdAt(gitHubUser.createdAt())
                .calculations(doCalculations(gitHubUser.followers(), gitHubUser.publicRepos()))
                .build();
    }

    private BigDecimal doCalculations(final Long followers, final Long publicRepos) {
        return followers != 0 ? BigDecimal.valueOf(6L).divide(BigDecimal.valueOf(followers), 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(2 + publicRepos)) : BigDecimal.ZERO;
    }
}
