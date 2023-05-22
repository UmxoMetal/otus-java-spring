package ru.otus.domain;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static ru.otus.dao.impl.BookDaoImpl.*;

@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = BOOK_AUTHORS_GRAPH, attributeNodes = {
                @NamedAttributeNode("bookAuthors")
        }),
        @NamedEntityGraph(name = BOOK_GENRES_GRAPH, attributeNodes = {
                @NamedAttributeNode("bookGenres")
        }),
        @NamedEntityGraph(name = BOOK_COMMENTS_GRAPH, attributeNodes = {
                @NamedAttributeNode("bookComments")
        })
})
@Getter
@Setter
@Builder
@Entity
@Table(name = "BOOK")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SequenceGenerator(name = "BOOK_SEQ", initialValue = 10, allocationSize = 1)
public class Book {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "BOOK_SEQ")
    private Long booId;

    @Column(name = "BOOK_NAME", nullable = false, unique = true)
    private String bookName;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(targetEntity = Author.class, fetch = LAZY, cascade = MERGE)
    @JoinTable(name = "BOOK_TO_AUTHOR", joinColumns = @JoinColumn(name = "BOO_ID"), inverseJoinColumns = @JoinColumn(name = "AUT_ID"))
    private List<Author> bookAuthors = new ArrayList<>();

    @ManyToMany(targetEntity = Genre.class, fetch = LAZY, cascade = MERGE)
    @JoinTable(name = "BOOK_TO_GENRE", joinColumns = @JoinColumn(name = "BOO_ID"), inverseJoinColumns = @JoinColumn(name = "GEN_ID"))
    private List<Genre> bookGenres = new ArrayList<>();

    @OneToMany(targetEntity = Comment.class, fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "BOO_ID")
    private List<Comment> bookComments = new ArrayList<>();

    public void addComment(Comment comment) {
        bookComments.add(comment);
        comment.setBook(this);
    }

    public void removeComment(Comment comment) {
        bookComments.remove(comment);
        comment.setBook(this);
    }
}