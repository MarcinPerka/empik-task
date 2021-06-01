package pl.empik.empiktask.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import pl.empik.empiktask.TestDataMother;
import pl.empik.empiktask.repository.UserRequestCounterRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRequestCounterServiceTests extends TestDataMother {

    @Mock
    private UserRequestCounterRepository userRequestCounterRepository;

    @InjectMocks
    private UserRequestCounterService userRequestCounterService;

    @Test
    public void shouldSaveUserRequestCounter_whenThereIsNoExistingCounterForGivenLogin() {
        when(userRequestCounterRepository.existsById(any())).thenReturn(false);
        when(userRequestCounterRepository.getById(any())).thenReturn(getUserRequestCounter());

        userRequestCounterService.createOrIncrementUserRequestCounter(VALID_USER_LOGIN);
        verify(userRequestCounterRepository, times(1)).save(any());
        verify(userRequestCounterRepository, times(0)).incrementRequestCounter(any());
        Assertions.assertEquals(1, userRequestCounterRepository.getById(VALID_USER_LOGIN).getRequestCount());
        Assertions.assertEquals(VALID_USER_LOGIN, userRequestCounterRepository.getById(VALID_USER_LOGIN).getLogin());
    }

    @Test
    public void shouldIncrementUserRequestCounter_whenCounterAlreadyExistsForGivenLogin() {
        when(userRequestCounterRepository.existsById(any())).thenReturn(true);
        var userRequestCounter = getUserRequestCounter();
        when(userRequestCounterRepository.getById(any())).thenReturn(userRequestCounter);
        userRequestCounterService.createOrIncrementUserRequestCounter(VALID_USER_LOGIN);
        ReflectionTestUtils.setField(userRequestCounter, "requestCount", 2);

        verify(userRequestCounterRepository, times(0)).save(any());
        verify(userRequestCounterRepository, times(1)).incrementRequestCounter(any());
        Assertions.assertEquals(2, userRequestCounterRepository.getById(VALID_USER_LOGIN).getRequestCount());
    }
}
