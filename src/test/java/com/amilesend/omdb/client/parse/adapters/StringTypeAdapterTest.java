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
package com.amilesend.omdb.client.parse.adapters;

import com.amilesend.client.util.StringUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Type;

import static com.amilesend.omdb.client.parse.adapters.StringTypeAdaptor.NOT_AVAILABLE;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class StringTypeAdapterTest {
    @Mock
    private Type mockType;
    @Mock
    private JsonDeserializationContext mockContext;

    final StringTypeAdaptor adaptorUnderTest = new StringTypeAdaptor();

    @Test
    public void deserialize_withNullElement_shouldReturnNull() {
        assertNull(adaptorUnderTest.deserialize(JsonNull.INSTANCE, mockType, mockContext));
    }

    @Test
    public void deserialize_withEmptyElement_shouldReturnNull() {
        assertNull(adaptorUnderTest.deserialize(new JsonPrimitive(StringUtils.EMPTY), mockType, mockContext));
    }

    @Test
    public void deserialize_withNaElement_shouldReturnNull() {
        assertNull(adaptorUnderTest.deserialize(new JsonPrimitive(NOT_AVAILABLE), mockType, mockContext));
    }
}
