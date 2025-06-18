package com.groovee.api.domain.artist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArtistUpdateDTO {
    private String name;
    private String bio;
    private String genre;
    private String source;
    private String externalId;
}
