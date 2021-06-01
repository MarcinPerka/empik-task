package pl.empik.empiktask.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserRequestCounter {

    @Id
    @Column(name = "LOGIN", nullable = false)
    private String login;

    @Column(name = "REQUEST_COUNT", nullable = false)
    private long requestCount;

    public static UserRequestCounter create(final String login){
        return new UserRequestCounter(login, 1L);
    }

}
