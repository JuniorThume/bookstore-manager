package bookstoremanager.dto;

import bookstoremanager.entity.Author;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotNull
    private Integer chapters;

    @NotNull
    private Integer pages;

    @NotBlank
    @Size(max = 100)
    @Pattern(regexp = "^(?:ISBN(?:-10|-13)?:?\\s*)?(?:(?=[0-9X-]{10}$)[0-9]{9}[0-9X]|(?=[0-9-]{13}$)[0-9]{13})$",
            message = "ISBN format must be a valid format"
    )
    private String isbn;

    @NotBlank
    @Size(max = 200)
    private String publisherName;

    @Valid
    @NotNull
    private Author author;
}
