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
package com.amilesend.omdb.client.model.type;

import com.amilesend.client.util.StringUtils;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The response containing the search results.
 *
 * @see SearchResult
 */
@Builder
@EqualsAndHashCode
@ToString
public class SearchResponse {
    /** The search results. */
    final List<SearchResult> search;
    @SerializedName("totalResults")
    final String totalResults;

    /**
     * Gets the search results.
     *
     * @return the search results
     * @see SearchResult
     */
    public List<SearchResult> getSearchResults() {
        return Collections.unmodifiableList(Optional.ofNullable(search).orElse(Collections.emptyList()));
    }

    /**
     * Gets the number of total results.
     *
     * @return the total results
     */
    public Integer getTotalResults() {
        return Optional.ofNullable(totalResults)
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
    }
}
