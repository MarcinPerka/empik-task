package pl.empik.empiktask.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.empik.empiktask.client.GitHubApiClient;
import pl.empik.empiktask.converter.GitHubUser2UserConverter;
import pl.empik.empiktask.model.User;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final GitHubApiClient gitHubApiClient;
    private final GitHubUser2UserConverter gitHubUser2UserConverter;

    public User getUser(final String login) {
        log.info("Getting user by login: {}", login);
        return gitHubUser2UserConverter.convert(gitHubApiClient.getUserByLogin(login));
    }

}
