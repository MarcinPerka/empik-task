package pl.empik.empiktask.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class User {
    public Long id;
    public String login;
    public String name;
    public String type;
    public String avatarUrl;
    public Instant createdAt;
    public BigDecimal calculations;
}
