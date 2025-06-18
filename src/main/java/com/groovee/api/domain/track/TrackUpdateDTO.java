package com.groovee.api.domain.track;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class TrackUpdateDTO {
    private String title;
    private UUID albumId;
    private Integer duration;
    private String source;
    private String externalId;
}
