package com.hokago_memories.domain.song;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public record Patterns(

        @AttributeOverrides({
                @AttributeOverride(name = "NM.level", column = @Column(name = "b4_nm_level")),
                @AttributeOverride(name = "NM.floor", column = @Column(name = "b4_nm_floor")),
                @AttributeOverride(name = "NM.rating", column = @Column(name = "b4_nm_rating")),
                @AttributeOverride(name = "HD.level", column = @Column(name = "b4_hd_level")),
                @AttributeOverride(name = "HD.floor", column = @Column(name = "b4_hd_floor")),
                @AttributeOverride(name = "HD.rating", column = @Column(name = "b4_hd_rating")),
                @AttributeOverride(name = "MX.level", column = @Column(name = "b4_mx_level")),
                @AttributeOverride(name = "MX.floor", column = @Column(name = "b4_mx_floor")),
                @AttributeOverride(name = "MX.rating", column = @Column(name = "b4_mx_rating")),
                @AttributeOverride(name = "SC.level", column = @Column(name = "b4_sc_level")),
                @AttributeOverride(name = "SC.floor", column = @Column(name = "b4_sc_floor")),
                @AttributeOverride(name = "SC.rating", column = @Column(name = "b4_sc_rating")),
        })
        @Embedded
        @JsonProperty("4B") PatternButton b4,

        @AttributeOverrides({
                @AttributeOverride(name = "NM.level", column = @Column(name = "b5_nm_level")),
                @AttributeOverride(name = "NM.floor", column = @Column(name = "b5_nm_floor")),
                @AttributeOverride(name = "NM.rating", column = @Column(name = "b5_nm_rating")),
                @AttributeOverride(name = "HD.level", column = @Column(name = "b5_hd_level")),
                @AttributeOverride(name = "HD.floor", column = @Column(name = "b5_hd_floor")),
                @AttributeOverride(name = "HD.rating", column = @Column(name = "b5_hd_rating")),
                @AttributeOverride(name = "MX.level", column = @Column(name = "b5_mx_level")),
                @AttributeOverride(name = "MX.floor", column = @Column(name = "b5_mx_floor")),
                @AttributeOverride(name = "MX.rating", column = @Column(name = "b5_mx_rating")),
                @AttributeOverride(name = "SC.level", column = @Column(name = "b5_sc_level")),
                @AttributeOverride(name = "SC.floor", column = @Column(name = "b5_sc_floor")),
                @AttributeOverride(name = "SC.rating", column = @Column(name = "b5_sc_rating")),
        })
        @Embedded
        @JsonProperty("5B") PatternButton b5,

        @AttributeOverrides({
                @AttributeOverride(name = "NM.level", column = @Column(name = "b6_nm_level")),
                @AttributeOverride(name = "NM.floor", column = @Column(name = "b6_nm_floor")),
                @AttributeOverride(name = "NM.rating", column = @Column(name = "b6_nm_rating")),
                @AttributeOverride(name = "HD.level", column = @Column(name = "b6_hd_level")),
                @AttributeOverride(name = "HD.floor", column = @Column(name = "b6_hd_floor")),
                @AttributeOverride(name = "HD.rating", column = @Column(name = "b6_hd_rating")),
                @AttributeOverride(name = "MX.level", column = @Column(name = "b6_mx_level")),
                @AttributeOverride(name = "MX.floor", column = @Column(name = "b6_mx_floor")),
                @AttributeOverride(name = "MX.rating", column = @Column(name = "b6_mx_rating")),
                @AttributeOverride(name = "SC.level", column = @Column(name = "b6_sc_level")),
                @AttributeOverride(name = "SC.floor", column = @Column(name = "b6_sc_floor")),
                @AttributeOverride(name = "SC.rating", column = @Column(name = "b6_sc_rating")),
        })
        @Embedded
        @JsonProperty("6B") PatternButton b6,

        @AttributeOverrides({
                @AttributeOverride(name = "NM.level", column = @Column(name = "b8_nm_level")),
                @AttributeOverride(name = "NM.floor", column = @Column(name = "b8_nm_floor")),
                @AttributeOverride(name = "NM.rating", column = @Column(name = "b8_nm_rating")),
                @AttributeOverride(name = "HD.level", column = @Column(name = "b8_hd_level")),
                @AttributeOverride(name = "HD.floor", column = @Column(name = "b8_hd_floor")),
                @AttributeOverride(name = "HD.rating", column = @Column(name = "b8_hd_rating")),
                @AttributeOverride(name = "MX.level", column = @Column(name = "b8_mx_level")),
                @AttributeOverride(name = "MX.floor", column = @Column(name = "b8_mx_floor")),
                @AttributeOverride(name = "MX.rating", column = @Column(name = "b8_mx_rating")),
                @AttributeOverride(name = "SC.level", column = @Column(name = "b8_sc_level")),
                @AttributeOverride(name = "SC.floor", column = @Column(name = "b8_sc_floor")),
                @AttributeOverride(name = "SC.rating", column = @Column(name = "b8_sc_rating")),
        })
        @Embedded
        @JsonProperty("8B") PatternButton b8
) {
}
