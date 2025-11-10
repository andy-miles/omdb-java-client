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

import com.amilesend.omdb.client.model.type.SearchResponse;
import com.amilesend.omdb.client.model.type.SearchResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.amilesend.omdb.client.data.DataHelper.newSeriesSearchResponse;
import static com.amilesend.omdb.client.data.DataHelper.newSeriesSearchResults;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchResponseTest {
    @Test
    public void searchResponse_withResults_shouldReturnList() {
        final List<SearchResult> expected = newSeriesSearchResults();

        final List<SearchResult> actual = newSeriesSearchResponse().getSearchResults();

        assertEquals(expected, actual);
    }

    @Test
    public void searchResponse_withNoResults_shouldReturnEmptyList() {
        final List<SearchResult> actual =
                SearchResponse.builder().search(null).totalResults("0").build().getSearchResults();

        assertTrue(actual.isEmpty());
    }
}
