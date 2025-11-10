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
package com.amilesend.omdb.client.model;

import com.amilesend.client.util.Validate;
import com.amilesend.omdb.client.model.type.Plot;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import okhttp3.HttpUrl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.amilesend.omdb.client.model.QueryParameterBasedRequest.appendIfNotBlank;
import static com.amilesend.omdb.client.model.QueryParameterBasedRequest.appendIfNotNull;

/**
 * Request to fetch media information based on the title.
 *
 * @see QueryParameterBasedRequest
 */
@SuperBuilder
@Data
public abstract class GetByTitleRequestBase implements QueryParameterBasedRequest {
    /** The title of the content to search for. */
    private final String title;

    /** The year of the movie release. */
    private final Integer year;
    /**
     * The plot format (i.e., short or full; default is short).
     *
     * @see Plot
     */
    private final Plot plot;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull final HttpUrl.Builder urlBuilder) {
        Validate.notBlank(title, "title must not be blank");

        appendIfNotBlank(urlBuilder, "t", title);
        appendIfNotNull(urlBuilder, "y", year);
        appendIfNotNull(urlBuilder, "plot", plot);

        return urlBuilder.addQueryParameter("r", URLEncoder.encode(JSON_RESPONSE_TYPE, StandardCharsets.UTF_8));
    }
}
