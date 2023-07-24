package otus.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;
import static javax.persistence.GenerationType.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "GENRE")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SequenceGenerator(name = "GENRE_SEQ", initialValue = 10, allocationSize = 1)
public class Genre {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "GENRE_SEQ")
    private Long genId;

    @Column(name = "GENRE_NAME")
    private String genreName;

    @ManyToMany(mappedBy = "bookGenres")
    private Set<Book> books = new HashSet<>();

    @Override
    public String toString() {
        return format("""
                Genre information
                genre id:[%d]
                genre name:[%s]
                """, genId, genreName);
    }
}