package pl.empik.empiktask.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import pl.empik.empiktask.TestDataMother;
import pl.empik.empiktask.exception.ResourceNotFoundException;
import pl.empik.empiktask.exception.UnrecognizedExternalApiException;
import pl.empik.empiktask.service.UserRequestCounterService;
import pl.empik.empiktask.service.UserService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerIntegrationTests extends TestDataMother {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRequestCounterService userRequestCounterService;


    @Test
    public void shouldReturnOkStatus_whenRequestIsValid() throws Exception {
        mockMvc.perform(get(VALID_USER_API_PATH))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNotFoundStatus_whenUserNotExists() throws Exception {
        when(userService.getUser(NOT_FOUND_USER_LOGIN)).thenThrow(new ResourceNotFoundException());

        mockMvc.perform(get(NOT_FOUND_USER_API_PATH))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnNotFoundStatus_whenUserLoginIsBlank() throws Exception {
        when(userService.getUser(BLANK_USER_LOGIN)).thenThrow(new ResourceNotFoundException());

        mockMvc.perform(get(BLANK_USER_API_PATH))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnInternalServerErrorStatus_whenExternalApiThrownUnknownStatusCode() throws Exception {
        when(userService.getUser(MOCK_ERROR_USER_LOGIN)).thenThrow(new UnrecognizedExternalApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"));

        mockMvc.perform(get(MOCK_ERROR_API_PATH))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldReturnInternalServerErrorStatus_whenApplicationThrownUnknownError() throws Exception {
        when(userService.getUser(MOCK_ERROR_USER_LOGIN)).thenThrow(new OutOfMemoryError("Out of memory error"));

        mockMvc.perform(get(MOCK_ERROR_API_PATH))
                .andExpect(status().isInternalServerError());
    }


}
