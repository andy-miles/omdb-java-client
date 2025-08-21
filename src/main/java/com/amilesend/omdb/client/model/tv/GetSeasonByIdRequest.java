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
package com.amilesend.omdb.client.model.tv;

import com.amilesend.client.util.Validate;
import com.amilesend.omdb.client.model.GetByIdRequestBase;
import com.amilesend.omdb.client.model.type.MediaType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import okhttp3.HttpUrl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * The request to fetch TV season information by its associates IMDB identifier.
 *
 * @see GetByIdRequestBase
 * @see GetSeasonBasedRequest
 */
@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetSeasonByIdRequest extends GetByIdRequestBase implements GetSeasonBasedRequest {
    /** The season number. */
    private final int season;

    @Override
    public HttpUrl.Builder populateQueryParameters(@NonNull final HttpUrl.Builder urlBuilder) {
        Validate.isTrue(season > 0, "season must be > 0");

        return super.populateQueryParameters(urlBuilder)
                .addQueryParameter("type", URLEncoder.encode(
                        MediaType.SERIES.getValue(),
                        StandardCharsets.UTF_8))
                .addQueryParameter("Season", URLEncoder.encode(String.valueOf(season), StandardCharsets.UTF_8));
    }
}
