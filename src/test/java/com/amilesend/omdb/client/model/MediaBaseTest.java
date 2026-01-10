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
package com.amilesend.omdb.client.model;

import com.amilesend.client.util.StringUtils;
import com.amilesend.omdb.client.model.movie.type.Movie;
import com.amilesend.omdb.client.model.tv.type.Series;
import com.amilesend.omdb.client.model.type.Rating;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.amilesend.omdb.client.data.DataHelper.newMovie;
import static com.amilesend.omdb.client.data.DataHelper.newMovieRatings;
import static com.amilesend.omdb.client.data.DataHelper.newSeries;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MediaBaseTest {
    @Test
    public void getGenres_withValidMovie_shouldReturnList() {
        final List<String> expected = List.of("Action", "Adventure", "Drama");

        final List<String> actual = newMovie().getGenres();

        assertEquals(expected, actual);
    }

    @Test
    public void getDirectors_withValidMovie_shouldReturnList() {
        final List<String> expected = List.of("Ridley Scott");

        final List<String> actual = newMovie().getDirectors();

        assertEquals(expected, actual);
    }

    @Test
    public void getWriters_withValidSeries_shouldReturnList() {
        final List<String> expected = List.of("David Benioff", "D.B. Weiss");

        final List<String> actual = newSeries().getWriters();

        assertEquals(expected, actual);
    }

    @Test
    public void getWriters_withEmptyWriters_shouldReturnEmptyList() {
        final List<String> actual = Series.builder().writer(StringUtils.EMPTY).build().getWriters();

        assertTrue(actual.isEmpty());
    }

    @Test
    public void getLanguages_withValidMovie_shouldReturnList() {
        final List<String> expected = List.of("English");

        final List<String> actual = newMovie().getLanguages();

        assertEquals(expected, actual);
    }

    @Test
    public void getCountries_withValidSeries_shouldReturnList() {
        final List<String> expected = List.of("United States", "United Kingdom");

        final List<String> actual = newSeries().getCountries();

        assertEquals(expected, actual);
    }

    @Test
    public void getRatings_withValidMovie_shouldReturnList() {
        final List<Rating> expected = newMovieRatings();

        final List<Rating> actual = newMovie().getRatings();

        assertEquals(expected, actual);
    }

    @Test
    public void getRatings_withMovieWithNoRatings_shouldReturnEmptyList() {
        final List<Rating> actual = Movie.builder().build().getRatings();

        assertTrue(actual.isEmpty());
    }
}
