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

import com.amilesend.omdb.connection.Connection;
import com.amilesend.omdb.model.QueryParameterBasedRequest;
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
import com.amilesend.omdb.model.type.SearchResponse;
import com.amilesend.omdb.parse.parser.BasicParser;
import lombok.NonNull;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.apache.commons.lang3.Validate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/** The Open Movie Database client. */
public class OMDb {
    /** The underlying connection. */
    private final Connection connection;
    /** The consumer-provided API key. */
    private final String apiKey;

    /**
     * Creates a new {@code OMDb} with the provided API key.
     *
     * @param apiKey the API key
     */
    public OMDb(final String apiKey) {
        this(apiKey, null);
    }

    /**
     * Creates a new {@code OMDb} with the provided connection and API key.
     *
     * @param connection the underlying connection
     * @param apiKey the API key
     */
    public OMDb(final String apiKey, final Connection connection) {
        Validate.notBlank(apiKey, "apiKey must not be blank. You can obtain one via " +
                "https://www.omdbapi.com/apikey.aspx");

        this.connection = Optional.ofNullable(connection).orElseGet(Connection::newDefaultInstance);
        this.apiKey = apiKey;
    }

    /////////////
    // GET APIs
    /////////////

    /**
     * Gets a movie for a request by either its title or IMDB identifier.
     *
     * @param request the request
     * @return the movie
     * @see Movie
     * @see GetMovieByIdRequest
     * @see GetMovieByTitleRequest
     */
    public Movie getMovie(@NonNull final GetMovieBasedRequest request) {
        return getResource(request, Movie.class);
    }

    /**
     * Gets a TV series for a request by either its title or IMDB identifier.
     *
     * @param request the request
     * @return the series
     * @see Series
     * @see GetSeriesByIdRequest
     * @see GetSeriesByTitleRequest
     */
    public Series getSeries(@NonNull final GetSeriesBasedRequest request) {
        return getResource(request, Series.class);
    }

    /**
     * Gets a TV season for a request by either its title or IMDB identifier.
     *
     * @param request the request
     * @return the season
     * @see Season
     * @see GetSeasonByIdRequest
     * @see GetSeasonByTitleRequest
     */
    public Season getSeason(@NonNull final GetSeasonBasedRequest request) {
        return getResource(request, Season.class);
    }

    /**
     * Gets a TV episode for a request by either its series title or IMDB identifier.
     *
     * @param request the request
     * @return the episode
     * @see Episode
     * @see GetEpisodeByIdRequest
     * @see GetEpisodeByTitleRequest
     */
    public Episode getEpisode(@NonNull final GetEpisodeBasedRequest request) {
        return getResource(request, Episode.class);
    }

    ////////////////
    // Search APIs
    ////////////////

    /**
     * Searches for movies.
     *
     * @param request the request
     * @return the response containing the list of movies
     */
    public SearchResponse search(@NonNull final SearchMovieRequest request) {
        return getResource(request, SearchResponse.class);
    }

    /**
     * Searches for TV series.
     *
     * @param request the request
     * @return the response containing the list of TV series.
     */
    public SearchResponse search(@NonNull final SearchSeriesRequest request) {
        return getResource(request, SearchResponse.class);
    }

    private <T> T getResource(final QueryParameterBasedRequest request, final Class<T> returnType) {
        final HttpUrl url = request.populateQueryParameters(newUrlBuilder()).build();
        final Request httpRequest = connection.newRequestBuilder().url(url).build();

        return connection.execute(httpRequest, new BasicParser<>(returnType));
    }

    private HttpUrl.Builder newUrlBuilder() {
        return HttpUrl.parse(connection.getBaseUrl())
                .newBuilder()
                .addQueryParameter("apikey", URLEncoder.encode(String.valueOf(apiKey), StandardCharsets.UTF_8))
                .addQueryParameter("r", "json");
    }
}
