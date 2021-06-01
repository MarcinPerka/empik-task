package pl.empik.empiktask.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.Instant;

public record GitHubUser(Long id, String login, String name,
                         String type, Long followers,
                         @JsonAlias("avatar_url") String avatarUrl,
                         @JsonAlias("created_at") Instant createdAt,
                         @JsonAlias("public_repos") Long publicRepos) {
}