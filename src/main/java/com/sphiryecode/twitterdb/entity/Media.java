package com.sphiryecode.twitterdb.entity;

import lombok.Data;

import javax.persistence.*;

/** La entidad Media se encarga de dar un contexto de uso al
 * archivo enviado a la base de datos.
 *
 * Admite exclusivamente imagenes, videos y gifs.*/
@Entity
@Data
public class Media {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    public enum Type {
        PROFILE_PHOTO, PROFILE_BANNER, TWEET_MEDIA
    }

    @Enumerated(EnumType.STRING)
    private Media.Type type;

    @OneToOne
    private File file;

}
