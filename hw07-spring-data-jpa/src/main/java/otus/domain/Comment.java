package otus.domain;

import lombok.*;

import javax.persistence.*;

import static java.lang.String.format;
import static javax.persistence.GenerationType.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "COMMENT")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SequenceGenerator(name = "COMMENT_SEQ", initialValue = 10, allocationSize = 1)
public class Comment {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "COMMENT_SEQ")
    private Long comId;

    @Column(name = "COMMENT_TEXT")
    private String commentText;

    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "BOO_ID")
    private Book book;

    @Override
    public String toString() {
        return format("""
                Comment information
                comment id:[%d]
                comment text:[%s]
                """, comId, commentText);
    }
}