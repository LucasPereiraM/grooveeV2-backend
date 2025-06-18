package com.groovee.api.domain.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewUpdateDTO {
    private String entityType;
    private Long entityId;
    private Integer rating;
    private String comment;
}
