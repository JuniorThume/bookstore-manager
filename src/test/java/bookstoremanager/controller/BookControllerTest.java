package bookstoremanager.controller;

import bookstoremanager.dto.BookDTO;
import bookstoremanager.dto.MessageResponseDTO;
import bookstoremanager.service.BookService;
import bookstoremanager.utils.BookUtils;
import bookstoremanager.utils.JsonConvertionUtils;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.JacksonJsonView;

import javax.print.attribute.standard.Media;
import java.awt.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    public static final String BOOK_API_URL_PATH = "/api/v1/books";

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new JacksonJsonView()).build();
    }

    @Test
    void whenPOSTisCalledThenABookShouldBeCreated() throws Exception {
        BookDTO bookDTO = BookUtils.createFakeBookDTO();
        MessageResponseDTO expectedMessageResponse = MessageResponseDTO
                .builder()
                .message("Book created with ID " + bookDTO.getId())
                .build();

        Mockito
                .when(bookService.create(bookDTO)).thenReturn(expectedMessageResponse);

        mockMvc.perform(MockMvcRequestBuilders
                .post(BOOK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConvertionUtils.asJsonString(bookDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is(expectedMessageResponse.getMessage())));
    }

    @Test
    void whenPOSTWithInvalidISBNisCalledThenABadRequestShouldBeReturned() throws Exception {
        BookDTO bookDTO = BookUtils.createFakeBookDTO();
        bookDTO.setIsbn("Invalid ISBN");

        mockMvc.perform(MockMvcRequestBuilders
                .post(BOOK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConvertionUtils.asJsonString(bookDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
