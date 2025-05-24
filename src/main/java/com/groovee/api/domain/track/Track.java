package com.groovee.api.domain.track;

import com.groovee.api.domain.album.Album;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "track")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Track {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false)
    private Album albumId;

    private Integer duration;
    private String source;

    @Column(name = "external_id")
    private String externalId;

}
