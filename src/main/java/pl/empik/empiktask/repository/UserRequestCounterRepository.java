package pl.empik.empiktask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.empik.empiktask.model.UserRequestCounter;

@Repository
public interface UserRequestCounterRepository extends JpaRepository<UserRequestCounter, String> {

    @Modifying
    @Query(value = "UPDATE UserRequestCounter urs SET urs.requestCount = urs.requestCount + 1 WHERE urs.login = :login")
    void incrementRequestCounter(final String login);
}
