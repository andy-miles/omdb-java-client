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
package com.amilesend.omdb.parse;

import com.amilesend.omdb.connection.Connection;
import com.amilesend.omdb.model.type.MediaType;
import com.amilesend.omdb.parse.adapters.LocalDateTypeAdapter;
import com.amilesend.omdb.parse.adapters.MediaTypeTypeAdapter;
import com.amilesend.omdb.parse.adapters.StringTypeAdaptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.gsonfire.GsonFireBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/** Factory that vends new pre-configured {@link Gson} instances. */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class GsonFactory {
    private static final GsonFactory INSTANCE = new GsonFactory();

    /**
     * Gets the singleton {@code GsonFactory} instance.
     *
     * @return the factory instance
     */
    public static GsonFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Gets a new {@link Gson} instance that is configured for use by {@link Connection}.
     *
     * @return the pre-configured Gson instance
     */
    public Gson newInstanceForConnection() {
        return newGsonBuilder().create();
    }

    /**
     * Gets a new {@link Gson} instance that is configured for use by {@link Connection} that provides pretty-printed
     * formatted JSON (i.e., useful for testing and/or debugging).
     *
     * @return the pre-configured Gson instance
     */
    public Gson newInstanceForPrettyPrinting() {
        return newGsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    private GsonBuilder newGsonBuilder() {
        return new GsonFireBuilder()
                .createGsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .registerTypeAdapter(String.class, new StringTypeAdaptor())
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .registerTypeAdapter(MediaType.class, new MediaTypeTypeAdapter());
    }
}
