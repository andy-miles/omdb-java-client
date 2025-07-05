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
package com.amilesend.omdb;

import com.amilesend.omdb.model.movie.GetMovieBasedRequest;
import com.amilesend.omdb.model.movie.GetMovieByIdRequest;
import com.amilesend.omdb.model.movie.GetMovieByTitleRequest;
import com.amilesend.omdb.model.movie.SearchMovieRequest;
import com.amilesend.omdb.model.movie.type.Movie;
import com.amilesend.omdb.model.tv.GetEpisodeBasedRequest;
import com.amilesend.omdb.model.tv.GetEpisodeByIdRequest;
import com.amilesend.omdb.model.tv.GetEpisodeByTitleRequest;
import com.amilesend.omdb.model.tv.GetSeasonBasedRequest;
import com.amilesend.omdb.model.tv.GetSeasonByIdRequest;
import com.amilesend.omdb.model.tv.GetSeasonByTitleRequest;
import com.amilesend.omdb.model.tv.GetSeriesBasedRequest;
import com.amilesend.omdb.model.tv.GetSeriesByIdRequest;
import com.amilesend.omdb.model.tv.GetSeriesByTitleRequest;
import com.amilesend.omdb.model.tv.SearchSeriesRequest;
import com.amilesend.omdb.model.tv.type.Episode;
import com.amilesend.omdb.model.tv.type.Season;
import com.amilesend.omdb.model.tv.type.Series;
import com.amilesend.omdb.model.type.Plot;
import com.amilesend.omdb.model.type.SearchResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.amilesend.omdb.data.DataHelper.Responses.EPISODE;
import static com.amilesend.omdb.data.DataHelper.Responses.MOVIE;
import static com.amilesend.omdb.data.DataHelper.Responses.MOVIE_SEARCH;
import static com.amilesend.omdb.data.DataHelper.Responses.SEASON;
import static com.amilesend.omdb.data.DataHelper.Responses.SERIES;
import static com.amilesend.omdb.data.DataHelper.Responses.SERIES_SEARCH;
import static com.amilesend.omdb.data.DataHelper.newEpisode;
import static com.amilesend.omdb.data.DataHelper.newMovie;
import static com.amilesend.omdb.data.DataHelper.newMovieSearchResponse;
import static com.amilesend.omdb.data.DataHelper.newSeason;
import static com.amilesend.omdb.data.DataHelper.newSeries;
import static com.amilesend.omdb.data.DataHelper.newSeriesSearchResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OMDbFunctionalTest extends FunctionalTestBase {
    private OMDb clientUnderTest;

    @BeforeEach
    public void setUpClient() {
        clientUnderTest = getClient();
    }

    /////////////
    // getMovie
    /////////////

    @Test
    public void getMovieById_withValidRequest_shouldReturnResponse() {
        getMovie_withValidRequest_shouldReturnResponse(GetMovieByIdRequest.builder()
                .imdbId("tt0172495")
                .plot(Plot.SHORT)
                .build());
    }

    @Test
    public void getMovieByTitle_withValidRequest_shouldReturnResponse() {
        getMovie_withValidRequest_shouldReturnResponse(GetMovieByTitleRequest.builder()
                .title("Gladiator")
                .plot(Plot.FULL)
                .build());
    }

    private void getMovie_withValidRequest_shouldReturnResponse(final GetMovieBasedRequest request) {
        setUpMockResponse(SUCCESS_STATUS_CODE, MOVIE);
        final Movie expected = newMovie();

        final Movie actual = clientUnderTest.getMovie(request);

        assertEquals(expected, actual);
    }

    //////////////
    // getSeries
    //////////////

    @Test
    public void getSeriesById_withValidRequest_shouldReturnResponse() {
        getSeries_withValidRequest_shouldReturnResponse(GetSeriesByIdRequest.builder().imdbId("tt0944947").build());
    }

    @Test
    public void getSeriesByTitle_withValidRequest_shouldReturnResponse() {
        getSeries_withValidRequest_shouldReturnResponse(GetSeriesByTitleRequest.builder()
                .title("Game of Thrones")
                .build());
    }

    private void getSeries_withValidRequest_shouldReturnResponse(final GetSeriesBasedRequest request) {
        setUpMockResponse(SUCCESS_STATUS_CODE, SERIES);
        final Series expected = newSeries();

        final Series actual = clientUnderTest.getSeries(request);

        assertEquals(expected, actual);
    }

    //////////////
    // getSeason
    //////////////

    @Test
    public void getSeasonById_withValidRequest_shouldReturnResponse() {
        getSeason_withValidRequest_shouldReturnResponse(GetSeasonByIdRequest.builder()
                .imdbId("tt0944947")
                .season(1).build());
    }

    @Test
    public void getSeasonByTitle_withValidRequest_shouldReturnResponse() {
        getSeason_withValidRequest_shouldReturnResponse(GetSeasonByTitleRequest.builder().
                title("Game of Thrones")
                .season(1)
                .build());
    }

    private void getSeason_withValidRequest_shouldReturnResponse(final GetSeasonBasedRequest request) {
        setUpMockResponse(SUCCESS_STATUS_CODE, SEASON);
        final Season expected = newSeason();

        final Season actual = clientUnderTest.getSeason(request);

        assertEquals(expected, actual);
    }

    ///////////////
    // getEpisode
    ///////////////

    @Test
    public void getEpisodeById_withValidRequest_shouldReturnResponse() {
        getEpisode_withValidRequest_shouldReturnResponse(GetEpisodeByIdRequest.builder()
                .imdbId("tt0944947")
                .season(1)
                .episode(1)
                .build());
    }

    @Test
    public void getEpisodeByTitle_withValidRequest_shouldReturnResponse() {
        getEpisode_withValidRequest_shouldReturnResponse(GetEpisodeByTitleRequest.builder()
                .title("Game of Thrones")
                .season(1)
                .episode(1)
                .build());
    }

    private void getEpisode_withValidRequest_shouldReturnResponse(final GetEpisodeBasedRequest request) {
        setUpMockResponse(SUCCESS_STATUS_CODE, EPISODE);
        final Episode expected = newEpisode();

        final Episode actual = clientUnderTest.getEpisode(request);

        assertEquals(expected, actual);
    }

    ///////////
    // search
    ///////////

    @Test
    public void searchMovies_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, MOVIE_SEARCH);
        final SearchResponse expected = newMovieSearchResponse();

        final SearchResponse actual = clientUnderTest.search(SearchMovieRequest.builder().title("Batman").build());

        assertEquals(expected, actual);
    }

    @Test
    public void searchSeries_withValidRequest_shouldReturnResponse() {
        setUpMockResponse(SUCCESS_STATUS_CODE, SERIES_SEARCH);
        final SearchResponse expected = newSeriesSearchResponse();

        final SearchResponse actual =
                clientUnderTest.search(SearchSeriesRequest.builder().title("Game of Thrones").build());

        assertEquals(expected, actual);
    }
}
