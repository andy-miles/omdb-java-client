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
package com.amilesend.omdb;

import com.amilesend.omdb.connection.Connection;
import com.amilesend.omdb.connection.http.OkHttpClientBuilder;
import com.amilesend.omdb.data.SerializedResource;
import com.amilesend.omdb.parse.GsonFactory;
import lombok.Getter;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.Buffer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class FunctionalTestBase {
    public static final int SUCCESS_STATUS_CODE = 200;

    private MockWebServer mockWebServer = new MockWebServer();
    private OkHttpClient httpClient;
    @Getter
    private Connection connection;
    @Getter
    private OMDb client;

    @SneakyThrows
    @BeforeEach
    public void setUp() {
        httpClient = new OkHttpClientBuilder().isForTest(true).build();
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        setUpOMDb();
    }

    @SneakyThrows
    @AfterEach
    public void cleanUp() {
        mockWebServer.shutdown();
    }

    protected void setUpMockResponse(final int responseCode) {
        setUpMockResponse(responseCode, (SerializedResource) null);
    }

    @SneakyThrows
    protected void setUpMockResponse(final int responseCode, final SerializedResource responseBodyResource) {
        if (responseBodyResource == null) {
            mockWebServer.enqueue(new MockResponse().setResponseCode(responseCode));
            return;
        }

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(responseCode)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBody(new Buffer().write(responseBodyResource.toBytes())));
    }

    protected String getMockWebServerUrl() {
        return String.format("http://%s:%d", mockWebServer.getHostName(), mockWebServer.getPort());
    }

    private void setUpOMDb() {
        connection = Connection.builder()
                .baseUrl(getMockWebServerUrl())
                .gsonFactory(GsonFactory.getInstance())
                .httpClient(httpClient)
                .build();
        client = new OMDb("apiKey", connection);
    }
}
