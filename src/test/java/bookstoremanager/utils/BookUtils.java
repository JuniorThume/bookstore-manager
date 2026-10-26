package bookstoremanager.utils;

import bookstoremanager.dto.BookDTO;
import bookstoremanager.entity.Book;
import com.github.javafaker.Faker;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;

import static bookstoremanager.utils.AuthorUtils.createFakeAuthor;

public class BookUtils {

    private static final Faker faker = new Faker();


    public static BookDTO createFakeBookDTO() {
        return BookDTO
                .builder()
                .id(faker.number().randomNumber())
                .name(faker.book().title())
                .pages(faker.number().numberBetween(40, 200))
                .chapters((faker.number().numberBetween(1,50)))
                .isbn("0958423859")
                .publisherName(faker.book().publisher())
                .author(createFakeAuthor())
                .build();
    }

    public static Book createFakeBook() {
        return Book
                .builder()
                .id(faker.number().randomNumber())
                .name(faker.book().title())
                .pages(faker.number().numberBetween(40, 200))
                .chapters((faker.number().numberBetween(1,50)))
                .isbn("0958423859")
                .publisherName(faker.book().publisher())
                .author(createFakeAuthor())
                .build();
    }
}
