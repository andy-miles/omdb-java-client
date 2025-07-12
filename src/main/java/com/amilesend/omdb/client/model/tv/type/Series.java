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
package com.amilesend.omdb.client.model.tv.type;


import com.amilesend.omdb.client.model.type.MediaBase;
import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Describes a TV series.
 *
 * @see MediaBase
 */
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Series extends MediaBase {
    /** The total number of seasons. */
    @SerializedName("totalSeasons")
    private final String totalSeasons;

    /**
     * Gets the total number of seasons.
     *
     * @return the number of seasons
     */
    public Integer getTotalSeasons() {
        return Optional.ofNullable(totalSeasons)
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .orElse(null);
    }
}
