package ru.otus.domain;

import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;
import static javax.persistence.GenerationType.*;

@Data
@Builder
@Entity
@Table(name = "AUTHOR")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SequenceGenerator(name = "AUTHOR_SEQ", initialValue = 10, allocationSize = 1)
public class Author {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "AUTHOR_SEQ")
    private Long autId;

    @Column(name = "AUTHOR_FIO")
    private String fio;

    @ManyToMany(mappedBy = "bookAuthors")
    private Set<Book> books = new HashSet<>();

    @Override
    public String toString() {
        return format("""
                Author information
                author id:[%d]
                author fio:[%s]
                """, autId, fio);
    }
}