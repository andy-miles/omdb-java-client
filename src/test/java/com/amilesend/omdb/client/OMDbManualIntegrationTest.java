/*
 * omdb-java-client - A client to access the OMDb API
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
package com.amilesend.omdb.client;

import com.amilesend.omdb.client.model.movie.GetMovieByIdRequest;
import com.amilesend.omdb.client.model.movie.GetMovieByTitleRequest;
import com.amilesend.omdb.client.model.movie.SearchMovieRequest;
import com.amilesend.omdb.client.model.movie.type.Movie;
import com.amilesend.omdb.client.model.tv.GetEpisodeByIdRequest;
import com.amilesend.omdb.client.model.tv.GetEpisodeByTitleRequest;
import com.amilesend.omdb.client.model.tv.GetSeasonByIdRequest;
import com.amilesend.omdb.client.model.tv.GetSeasonByTitleRequest;
import com.amilesend.omdb.client.model.tv.GetSeriesByIdRequest;
import com.amilesend.omdb.client.model.tv.GetSeriesByTitleRequest;
import com.amilesend.omdb.client.model.tv.SearchSeriesRequest;
import com.amilesend.omdb.client.model.tv.type.Episode;
import com.amilesend.omdb.client.model.tv.type.Season;
import com.amilesend.omdb.client.model.tv.type.Series;
import com.amilesend.omdb.client.model.type.Plot;
import com.amilesend.omdb.client.model.type.SearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Slf4j
@Disabled
public class OMDbManualIntegrationTest {
    // Define your own API key here
    private static final String API_KEY = "";

    private final OMDb client = new OMDb(API_KEY);

    @Test
    public void getMovieByTitle_shouldReturnResponse() {
        final Movie actual = client.getMovie(GetMovieByTitleRequest.builder()
                .title("Gladiator")
                .build());
        log.info("Movie: {}", actual);
    }

    @Test
    public void getMovieById_shouldReturnResponse() {
        final Movie actual = client.getMovie(GetMovieByIdRequest.builder().imdbId("tt0172495").plot(Plot.FULL).build());
        log.info("Movie: {}", actual);
    }

    @Test
    public void getSeriesByTitle_shouldReturnResponse() {
        final Series actual = client.getSeries(GetSeriesByTitleRequest.builder().title("Weeds").build());
        log.info("Series: {}", actual);
    }

    @Test
    public void getSeriesById_shouldReturnResponse() {
        final Series actual = client.getSeries(GetSeriesByIdRequest.builder().imdbId("tt0439100").build());
        log.info("Series: {}", actual);
    }

    @Test
    public void getSeasonByTitle_shouldReturnResponse() {
        final Season actual = client.getSeason(GetSeasonByTitleRequest.builder().title("Game of Thrones").season(1).build());
        log.info("Season: {}", actual);
    }

    @Test
    public void getSeasonById_shouldReturnResponse() {
        final Season actual = client.getSeason(GetSeasonByIdRequest.builder().imdbId("tt0944947").season(1).build());
        log.info("Season: {}", actual);
    }

    @Test
    public void getEpisodeByTitle_shouldReturnResponse() {
        final Episode actual = client.getEpisode(GetEpisodeByTitleRequest.builder()
                .title("Game of Thrones")
                .season(1)
                .episode(2)
                .build());
        log.info("Episode: {}", actual);
    }

    @Test
    public void getEpisodeById_shouldReturnResponse() {
        final Episode actual = client.getEpisode(GetEpisodeByIdRequest.builder()
                .imdbId("tt0944947")
                .season(1)
                .episode(2)
                .build());
        log.info("Episode: {}", actual);
    }

    @Test
    public void searchMovies_shouldReturnResponse() {
        final SearchResponse actual = client.search(SearchMovieRequest.builder().title("Batman").build());
        log.info("Search Movies: {}", actual);
        final SearchResponse actual2 = client.search(SearchMovieRequest.builder().title("Batman").page(2).build());
        log.info("Search Movies Page 2: {}", actual2);
    }

    @Test
    public void searchSeries_shouldReturnResponse() {
        final SearchResponse actual = client.search(SearchSeriesRequest.builder().title("Batman").build());
        log.info("Search series: {}", actual);
    }
}
