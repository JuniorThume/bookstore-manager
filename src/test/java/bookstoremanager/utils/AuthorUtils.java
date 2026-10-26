package bookstoremanager.utils;

import bookstoremanager.dto.AuthorDTO;
import bookstoremanager.entity.Author;
import com.github.javafaker.Faker;

public class AuthorUtils {
    private static final Faker faker = new Faker();


    public static AuthorDTO createFakeAuthorDTO() {
        return AuthorDTO
                .builder()
                .id(faker.number().randomNumber())
                .age(faker.number().numberBetween(18, 100))
                .name(faker.artist().name())
                .build();
    }

    public static Author createFakeAuthor() {
        return Author
                .builder()
                .id(faker.number().randomNumber())
                .age(faker.number().numberBetween(18, 100))
                .name(faker.artist().name())
                .build();
    }
}
