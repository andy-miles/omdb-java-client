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

import com.amilesend.client.util.StringUtils;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/** Describes a list of TV episodes for a TV series season. */
@Builder
@EqualsAndHashCode
@ToString
public class Season {
    /** The TV series name. */
    @Getter
    private final String title;
    /** The season number. */
    private final String season;
    /** The total number of seasons. */
    @SerializedName("totalSeasons")
    private final String totalSeasons;
    /** The list of episodes for the season. */
    private final List<SeasonEpisode> episodes;

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
     * Gets the total number of seasons.
     *
     * @return the total number of seasons
     */
    public Integer getTotalSeasons() {
        return Optional.ofNullable(totalSeasons)
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
    }

    /**
     * Gets the list of episodes for the season.
     *
     * @return the list of episodes
     */
    public List<SeasonEpisode> getEpisodes() {
        return Collections.unmodifiableList(episodes);
    }
}
