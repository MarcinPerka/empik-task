package pl.empik.empiktask.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.empik.empiktask.model.UserRequestCounter;
import pl.empik.empiktask.repository.UserRequestCounterRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRequestCounterService {

    private final UserRequestCounterRepository userRequestCounterRepository;

    @Transactional
    public void createOrIncrementUserRequestCounter(final String login) {
        if (userRequestCounterRepository.existsById(login))
            incrementUserRequestCounter(login);
        else
            createUserRequestCounter(login);
    }

    private void createUserRequestCounter(final String login) {
        log.info("Creating new user request counter for login: {}", login);
        userRequestCounterRepository.save(UserRequestCounter.create(login));
    }

    private void incrementUserRequestCounter(final String login) {
        log.info("Incrementing user request counter for login: {}", login);
        userRequestCounterRepository.incrementRequestCounter(login);
    }
}
