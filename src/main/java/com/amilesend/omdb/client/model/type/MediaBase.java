/*
 * omdb-java-client - A client to access the OMDb API
 * Copyright © 2025-2026 Andy Miles (andy.miles@amilesend.com)
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
package com.amilesend.omdb.client.model.type;

import com.amilesend.client.util.StringUtils;
import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.amilesend.omdb.client.model.type.TypeHelper.STRING_LIST_DELIMITER;
import static com.amilesend.omdb.client.model.type.TypeHelper.parseCommonDelimitedList;

/** Defines common attributes for media (i.e., movie, TV series). */
@SuperBuilder
@EqualsAndHashCode
@ToString
public abstract class MediaBase {
    /** The movie title. */
    @Getter
    private final String title;
    /** The year of release. */
    @Getter
    private final String year;
    /** The associated rating. */
    @Getter
    private final String rated;
    /** The date of release. */
    @Getter
    private final LocalDate released;
    /** The runtime. */
    @Getter
    private final String runtime;
    /** The comma-delimited list of genres. */
    private final String genre;
    /** The comma-delimited list of directors. */
    private final String director;
    /** The comma-delimited list of writers. */
    private final String writer;
    /** The comma-delimited list of actors. */
    private final String actors;
    /** The plot description. */
    @Getter
    private final String plot;
    /** The comma-delimited list of languages. */
    private final String language;
    /** The country of origin. */
    private final String country;
    /** The description of received awards. */
    @Getter
    private final String awards;
    /** The URL of the movie poster image. */
    @Getter
    private final String poster;
    /**
     * The list of ratings.
     *
     * @see Rating
     */
    private final List<Rating> ratings;
    /** The metacritic score. */
    @Getter
    private final String metascore;
    /** The IMDB rating. */
    @SerializedName("imdbRating")
    private final String imdbRating;
    /** The number of votes associated with the IMDB rating. */
    @SerializedName("imdbVotes")
    private final String imdbVotes;
    /** The IMDB identifier. */
    @Getter
    @SerializedName("imdbID")
    private final String imdbId;

    /**
     * Gets the list of ratings.
     *
     * @return the list of ratings
     * @see Rating
     */
    public List<Rating> getRatings() {
        return Optional.ofNullable(ratings)
                .map(Collections::unmodifiableList)
                .orElseGet(() -> Collections.emptyList());
    }

    /**
     * Gets the IMDB rating.
     *
     * @return the rating
     */
    public Double getImdbRating() {
        return Optional.ofNullable(imdbRating)
                .filter(StringUtils::isNotBlank)
                .map(Double::parseDouble)
                .orElse(null);
    }

    /**
     * Gets the IMDB votes associated with the rating.
     *
     * @return the number of votes
     */
    public Integer getImdbVotes() {
        return Optional.ofNullable(imdbVotes)
                .filter(StringUtils::isNotBlank)
                .map(v -> v.replaceAll(STRING_LIST_DELIMITER, StringUtils.EMPTY))
                .map(Integer::parseInt)
                .orElse(null);
    }

    /**
     * Gets the list of genres.
     *
     * @return the list of genres
     */
    public List<String> getGenres() {
        return parseCommonDelimitedList(genre);
    }

    /**
     * Gets the list of directors.
     *
     * @return the list of directors
     */
    public List<String> getDirectors() {
        return parseCommonDelimitedList(director);
    }

    /**
     * Gets the list of credited writers.
     *
     * @return the list of writers
     */
    public List<String> getWriters() {
        return parseCommonDelimitedList(writer);
    }

    /**
     * Gets the list credited actors.
     *
     * @return the list of actors
     */
    public List<String> getActors() {
        return parseCommonDelimitedList(actors);
    }

    /**
     * Gets the list of languages.
     *
     * @return the list of languages
     */
    public List<String> getLanguages() {
        return parseCommonDelimitedList(language);
    }

    /**
     * Gets the list of countries.
     *
     * @return the list of countries
     */
    public List<String> getCountries() {
        return parseCommonDelimitedList(country);
    }
}
