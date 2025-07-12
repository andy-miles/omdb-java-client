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

import com.amilesend.omdb.client.model.type.MediaBase;
import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Describes a TV episode.
 *
 * @see MediaBase
 */
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Episode extends MediaBase {
    /** The season number. */
    private final String season;
    /** The associated episode number. */
    private final String episode;
    /** The IMDB TV series identifier. */
    @SerializedName("seriesID")
    @Getter
    private final String seriesId;

    /**
     * Gets the season number.
     *
     * @return the season number
     */
    public Integer getSeason() {
        return Optional.ofNullable(season)
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
    }

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
}
