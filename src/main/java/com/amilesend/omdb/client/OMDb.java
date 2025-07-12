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
package com.amilesend.omdb.client;

import com.amilesend.client.connection.auth.NoOpAuthManager;
import com.amilesend.client.parse.parser.BasicParser;
import com.amilesend.omdb.client.connection.OmdbConnection;
import com.amilesend.omdb.client.connection.OmdbConnectionBuilder;
import com.amilesend.omdb.client.model.QueryParameterBasedRequest;
import com.amilesend.omdb.client.model.movie.GetMovieBasedRequest;
import com.amilesend.omdb.client.model.movie.GetMovieByIdRequest;
import com.amilesend.omdb.client.model.movie.GetMovieByTitleRequest;
import com.amilesend.omdb.client.model.movie.SearchMovieRequest;
import com.amilesend.omdb.client.model.movie.type.Movie;
import com.amilesend.omdb.client.model.tv.GetEpisodeBasedRequest;
import com.amilesend.omdb.client.model.tv.GetEpisodeByIdRequest;
import com.amilesend.omdb.client.model.tv.GetEpisodeByTitleRequest;
import com.amilesend.omdb.client.model.tv.GetSeasonBasedRequest;
import com.amilesend.omdb.client.model.tv.GetSeasonByIdRequest;
import com.amilesend.omdb.client.model.tv.GetSeasonByTitleRequest;
import com.amilesend.omdb.client.model.tv.GetSeriesBasedRequest;
import com.amilesend.omdb.client.model.tv.GetSeriesByIdRequest;
import com.amilesend.omdb.client.model.tv.GetSeriesByTitleRequest;
import com.amilesend.omdb.client.model.tv.SearchSeriesRequest;
import com.amilesend.omdb.client.model.tv.type.Episode;
import com.amilesend.omdb.client.model.tv.type.Season;
import com.amilesend.omdb.client.model.tv.type.Series;
import com.amilesend.omdb.client.model.type.SearchResponse;
import com.amilesend.omdb.client.parse.GsonFactory;
import lombok.NonNull;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.lang3.Validate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/** The Open Movie Database client. */
public class OMDb {
    public static final String USER_AGENT = "OMDbJavaClient/2.0";
    public static final String API_URL = "http://www.omdbapi.com/";

    /** The underlying connection. */
    private final OmdbConnection connection;
    /** The consumer-provided API key. */
    private final String apiKey;

    /**
     * Creates a new {@code OMDb} with the provided API key.
     *
     * @param apiKey the API key
     */
    public OMDb(final String apiKey) {
        this(apiKey, USER_AGENT, null);
    }

    /**
     * Creates a nwe {@code OMDb} with the provided API key and user agent.
     *
     * @param apiKey the API key
     * @param userAgent the user agent
     */
    public OMDb(final String apiKey, final String userAgent) {
        this(apiKey, userAgent, null);
    }

    /**
     * Creates a new {@code OMDb} with the provided connection and API key.
     *
     * @param apiKey the API key
     * @param userAgent the user agent
     * @param connection the underlying connection
     */
    public OMDb(final String apiKey, final String userAgent, final OmdbConnection connection) {
        Validate.notBlank(apiKey, "apiKey must not be blank. You can obtain one via " +
                "https://www.omdbapi.com/apikey.aspx");
        Validate.notBlank(userAgent, "userAgent must not be blank");

        this.connection = Optional.ofNullable(connection).orElseGet(() -> newConnection(userAgent));
        this.apiKey = apiKey;
    }

    private static OmdbConnection newConnection(final String userAgent) {
        return new OmdbConnectionBuilder()
                .baseUrl(API_URL)
                .userAgent(userAgent)
                .gsonFactory(new GsonFactory())
                .authManager(new NoOpAuthManager())
                .httpClient(new OkHttpClient.Builder().build())
                .isGzipContentEncodingEnabled(false)
                .build();
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
