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
package com.amilesend.omdb.model.type;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/** Helper class to help parse response attributes. */
@UtilityClass
public class TypeHelper {
    /** The delimiter used to represent a list of string values. */
    public static String STRING_LIST_DELIMITER = ",";

    /**
     * Parses a comma-delimited list of string values and returns a list of strings.
     *
     * @param commonDelimitedList the comma-delimited list of values
     * @return the list of values
     */
    public static List<String> parseCommonDelimitedList(final String commonDelimitedList) {
        if (StringUtils.isBlank(commonDelimitedList)) {
            return Collections.emptyList();
        }

        return Arrays.stream(commonDelimitedList.split(STRING_LIST_DELIMITER))
                .map(StringUtils::trim)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toUnmodifiableList());
    }
}
