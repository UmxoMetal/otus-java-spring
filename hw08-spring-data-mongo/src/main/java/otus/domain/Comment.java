package otus.domain;

import lombok.*;
import java.util.Date;

@Data
@Builder
public class Comment {
    private Date commentDate;
    private String commentText;
}