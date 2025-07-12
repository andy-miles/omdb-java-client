/*
 * omdb-java-client - A client to access the OMDB API
 * Copyright © 2025 Andy Miles (andy.miles@amilesend.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.amilesend.omdb.client.model.tv.type;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Optional;

/** Describes a TV episode. */
@Builder
@EqualsAndHashCode
@ToString
public class SeasonEpisode {
    /** The episode title. */
    @Getter
    private final String title;
    /** The air date. */
    @Getter
    private final LocalDate released;
    /** The episode number. */
    private final String episode;
    /** The IMDB rating. */
    @SerializedName("imdbRating")
    private final String imdbRating;
    /** The IMDB identifier. */
    @Getter
    @SerializedName("imdbID")
    private final String imdbId;

    /**
     * Gets the episode number.
     *
     * @return the episode number
     */
    public Integer getEpisode() {
        return Optional.ofNullable(episode)
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
    }

    /**
     * Gets the IMDB rating.
     *
     * @return the IMDB rating
     */
    public Double getImdbRating() {
        return Optional.ofNullable(imdbRating)
                .filter(StringUtils::isNotBlank)
                .map(Double::parseDouble)
                .orElse(null);
    }
}
