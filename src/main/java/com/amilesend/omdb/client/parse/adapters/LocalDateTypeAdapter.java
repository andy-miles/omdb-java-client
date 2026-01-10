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
package com.amilesend.omdb.client.parse.adapters;

import com.amilesend.client.util.StringUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.amilesend.omdb.client.parse.adapters.StringTypeAdaptor.NOT_AVAILABLE;

/** GSON adapter to format and serializes {@link LocalDate} objects. */
@Slf4j
public class LocalDateTypeAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    /** Date formatter for movie information. */
    public static final DateTimeFormatter MOVIE_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    /** Date formatter for TV episode information. */
    public static final DateTimeFormatter EPISODES_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public JsonElement serialize(
            final LocalDate date,
            final Type typeOfSrc,
            final JsonSerializationContext context) {
        // Default to movie format--not used by the client
        return new JsonPrimitive(date.format(MOVIE_DATE_FORMATTER));
    }

    @Override
    public LocalDate deserialize(
            final JsonElement jsonElement,
            final Type typeOfT,
            final JsonDeserializationContext context) throws JsonParseException {
        if (jsonElement.isJsonNull()) {
            return null;
        }

        final String timeAsString = jsonElement.getAsString();
        if (StringUtils.isBlank(timeAsString) || NOT_AVAILABLE.equals(timeAsString)) {
            return null;
        }

        try {
            return LocalDate.parse(timeAsString, MOVIE_DATE_FORMATTER);
        } catch (final DateTimeParseException ex) {
            log.debug("Date format does not match pattern: \"dd MMM yyyy\"", ex);
        }

        try {
            return LocalDate.parse(timeAsString, EPISODES_DATE_FORMATTER);
        } catch (final DateTimeParseException ex) {
            throw new JsonParseException("Date format does not match pattern: \"dd MMM yyyy\" or \"yyyy-MM-dd\"", ex);
        }
    }
}
