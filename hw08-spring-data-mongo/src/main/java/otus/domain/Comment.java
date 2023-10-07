package otus.domain;

import lombok.*;
import java.util.Date;

import static java.lang.String.format;

@Data
@Builder
public class Comment {
    private Date commentDate;
    private String commentText;

    @Override
    public String toString() {
        return format("""
                Comment information
                comment date:[%s]
                comment text:[%s]
                """, commentDate, commentText);
    }
}