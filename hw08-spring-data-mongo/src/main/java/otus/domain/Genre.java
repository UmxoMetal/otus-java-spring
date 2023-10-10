package otus.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static java.lang.String.format;

@Data
@Builder
@Document
public class Genre {
    @Id
    private String genreId;
    @NotNull
    private String genreName;

    @Override
    public String toString() {
        return format("""
                Genre information
                genre id:[%s]
                genre name:[%s]
                """, genreId, genreName);
    }
}