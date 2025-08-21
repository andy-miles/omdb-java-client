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
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

/** Describes a failure response type. */
@Builder
@EqualsAndHashCode
@ToString
public class FailureResponse {
    /**
     * The response success boolean state value. {@code false} represents a failed request; {@code true}, represents
     * a successful request.
     */
    private final String response;
    /** The error message. */
    @Getter
    private final String error;

    /**
     * Gets the response success boolean state value. {@code false} represents a failed request; {@code true},
     * represents a successful request.
     *
     * @return the successful response state value
     */
    public Boolean getResponse() {
        return Optional.ofNullable(response)
                .filter(StringUtils::isNotBlank)
                .map(String::toLowerCase)
                .map(Boolean::parseBoolean)
                .orElse(Boolean.FALSE);
    }
}
