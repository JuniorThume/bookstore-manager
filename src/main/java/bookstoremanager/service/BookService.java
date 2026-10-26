package bookstoremanager.service;

import bookstoremanager.dto.BookDTO;
import bookstoremanager.dto.MessageResponseDTO;
import bookstoremanager.entity.Book;
import bookstoremanager.mapper.BookMapper;
import bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BookService {

    private BookRepository bookRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public MessageResponseDTO create(BookDTO bookDTO) {
        Book bookToSave = bookMapper.toModel(bookDTO);

        Book savedBook = bookRepository.save(bookToSave);

        return MessageResponseDTO
                .builder()
                .message("Book created with ID " + savedBook.getId()).build();
    }
}
