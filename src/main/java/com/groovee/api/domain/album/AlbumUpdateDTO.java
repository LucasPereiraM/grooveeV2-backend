package com.groovee.api.domain.album;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AlbumUpdateDTO {
    private UUID artistId;
    private String title;
    private String genre;
    private String source;
    private String externalId;
    private LocalDate releaseDate;
}
