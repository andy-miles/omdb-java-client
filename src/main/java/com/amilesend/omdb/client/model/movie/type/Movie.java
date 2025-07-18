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
package com.amilesend.omdb.client.model.movie.type;

import com.amilesend.omdb.client.model.type.MediaBase;
import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Describes a movie.
 *
 * @see MediaBase
 */
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Movie extends MediaBase {
    /** The publication date of the DVD/Bluray release. */
    @Getter
    @SerializedName("DVD")
    private final LocalDate dvd;
    /** The box office revenue. */
    @Getter
    private final String boxOffice;
    /** The production. */
    @Getter
    private final String production;
    /** The website URL. */
    @Getter
    private final String website;
}
