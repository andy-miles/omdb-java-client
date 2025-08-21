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
package com.amilesend.omdb.client.model.type;

import com.amilesend.client.util.Validate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/** Defines the supported OMDb media types (i.e., "movie", "series", and "episode"). */
@Getter
@RequiredArgsConstructor
public enum MediaType {
    MOVIE("movie"),
    SERIES("series"),
    EPISODE("episode");

    /** The parameter name. */
    public static final String MEDIA_TYPE_OBJ_NAME = "Type";

    private static final Map<String, MediaType> VALUE_TO_MEDIA_TYPE = Map.of(
            "movie", MOVIE,
            "series", SERIES,
            "episode", EPISODE);

    /** The media type value. */
    private final String value;

    /**
     * Returns the associated {@code MediaType} for the given value.
     *
     * @param value the value
     * @return the media type reference
     */
    public static MediaType fromValue(final String value) {
        Validate.notBlank(value, "value must not be blank");
        return VALUE_TO_MEDIA_TYPE.get(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
