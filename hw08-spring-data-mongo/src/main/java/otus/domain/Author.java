package otus.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static java.lang.String.format;

@Data
@Builder
@Document
public class Author {
    @Id
    private String autId;
    private String fio;

    @Override
    public String toString() {
        return format("""
                Author information
                author id:[%s]
                author fio:[%s]
                """, autId, fio);
    }
}