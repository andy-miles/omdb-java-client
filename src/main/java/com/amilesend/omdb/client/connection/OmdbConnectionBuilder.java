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
package com.amilesend.omdb.client.connection;

import com.amilesend.client.connection.ConnectionBuilder;
import com.amilesend.omdb.client.parse.GsonFactory;

/**
 * Builder used to construct new {@link OmdbConnection} objects.
 *
 * @see OmdbConnection
 */
public class OmdbConnectionBuilder extends ConnectionBuilder<OmdbConnectionBuilder, GsonFactory, OmdbConnection> {
    @Override
    public OmdbConnection build() {
        validateAttributes();

        return OmdbConnection.builder()
                .httpClient(getHttpClient())
                .gsonFactory(getGsonFactory())
                .authManager(getAuthManager())
                .baseUrl(getBaseUrl())
                .userAgent(getUserAgent())
                .isGzipContentEncodingEnabled(isGzipContentEncodingEnabled())
                .retryStrategy(getRetryStrategy())
                .build();
    }
}
