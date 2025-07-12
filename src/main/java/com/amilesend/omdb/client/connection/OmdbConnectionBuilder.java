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
                .build();
    }
}
