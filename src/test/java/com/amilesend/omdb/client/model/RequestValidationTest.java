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
package com.amilesend.omdb.client.model;

import com.amilesend.client.util.StringUtils;
import com.amilesend.omdb.client.model.tv.GetEpisodeByIdRequest;
import com.amilesend.omdb.client.model.tv.GetEpisodeByTitleRequest;
import com.amilesend.omdb.client.model.tv.GetSeasonByIdRequest;
import com.amilesend.omdb.client.model.tv.GetSeasonByTitleRequest;
import okhttp3.HttpUrl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RequestValidationTest {
    @Mock
    private HttpUrl.Builder mockBuilder;

    @Test
    public void populateQueryParameters_withGetSeasonByTitleRequest_shouldThrowException() {
        assertAll(
                () -> assertThrows(NullPointerException.class,
                        () -> GetSeasonByTitleRequest.builder()
                                .title("Title")
                                .season(1)
                                .build()
                                .populateQueryParameters(null)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> GetSeasonByTitleRequest.builder()
                                .title(StringUtils.EMPTY)
                                .season(1)
                                .build()
                                .populateQueryParameters(mockBuilder)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> GetSeasonByTitleRequest.builder()
                                .title("Title")
                                .season(0)
                                .build()
                                .populateQueryParameters(mockBuilder)));
    }

    @Test
    public void populateQueryParameters_withGetSeasonByIdRequest_shouldThrowExceptions() {
        assertAll(
                () -> assertThrows(NullPointerException.class,
                        () -> GetSeasonByIdRequest.builder()
                                .imdbId("ID")
                                .season(1)
                                .build()
                                .populateQueryParameters(null)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> GetSeasonByIdRequest.builder()
                                .imdbId(StringUtils.EMPTY)
                                .season(1)
                                .build()
                                .populateQueryParameters(mockBuilder)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> GetSeasonByIdRequest.builder()
                                .imdbId("ID")
                                .season(0)
                                .build()
                                .populateQueryParameters(mockBuilder)));
    }

    @Test
    public void populateQueryParameters_withGetEpisodeByTitleRequest_shouldThrowExceptions() {
        assertAll(
                () -> assertThrows(NullPointerException.class,
                        () -> GetEpisodeByTitleRequest.builder()
                                .title("Title")
                                .season(1)
                                .episode(1)
                                .build()
                                .populateQueryParameters(null)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> GetEpisodeByTitleRequest.builder()
                                .title(StringUtils.EMPTY)
                                .season(1)
                                .episode(1)
                                .build()
                                .populateQueryParameters(mockBuilder)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> GetEpisodeByTitleRequest.builder()
                                .title("Title")
                                .season(0)
                                .episode(1)
                                .build()
                                .populateQueryParameters(mockBuilder)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> GetEpisodeByTitleRequest.builder()
                                .title("Title")
                                .season(1)
                                .episode(-1)
                                .build()
                                .populateQueryParameters(mockBuilder)));
    }

    @Test
    public void populateQueryParameters_withGetEpisodeByIdRequest_shouldThrowExceptions() {
        assertAll(
                () -> assertThrows(NullPointerException.class,
                        () -> GetEpisodeByIdRequest.builder()
                                .imdbId("ID")
                                .season(1)
                                .episode(1)
                                .build()
                                .populateQueryParameters(null)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> GetEpisodeByIdRequest.builder()
                                .imdbId(StringUtils.EMPTY)
                                .season(1)
                                .episode(1)
                                .build()
                                .populateQueryParameters(mockBuilder)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> GetEpisodeByIdRequest.builder()
                                .imdbId("ID")
                                .season(0)
                                .episode(1)
                                .build()
                                .populateQueryParameters(mockBuilder)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> GetEpisodeByIdRequest.builder()
                                .imdbId("ID")
                                .season(1)
                                .episode(-1)
                                .build()
                                .populateQueryParameters(mockBuilder)));
    }
}
