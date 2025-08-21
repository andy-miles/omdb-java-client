package com.amilesend.omdb.client.connection;

import com.amilesend.client.connection.Connection;
import com.amilesend.client.connection.ConnectionException;
import com.amilesend.client.connection.RequestException;
import com.amilesend.client.connection.ResponseException;
import com.amilesend.client.connection.ResponseParseException;
import com.amilesend.client.parse.parser.GsonParser;
import com.amilesend.client.util.VisibleForTesting;
import com.amilesend.omdb.client.model.FailureResponse;
import com.amilesend.omdb.client.parse.GsonFactory;
import com.google.gson.JsonParseException;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import okhttp3.Request;
import okhttp3.Response;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * The Open Movie Database Connection.
 *
 * @see Connection
 */
@SuperBuilder
public class OmdbConnection extends Connection<GsonFactory> {
    @Override
    public <T> T execute(@NonNull final Request request, @NonNull final GsonParser<T> parser)
            throws ConnectionException {
        try {
            try (final Response response = getHttpClient().newCall(request).execute()) {
                validateResponseCode(response);

                final byte[] body = readBody(response.body().byteStream());
                validateResponseBody(body);
                return parser.parse(getGsonFactory().getInstance(this), body);
            }
        } catch (final IOException ex) {
            throw new RequestException("Unable to execute request: " + ex.getMessage(), ex);
        } catch (final JsonParseException ex) {
            throw new ResponseParseException("Error parsing response: " + ex.getMessage(), ex);
        }
    }

    @VisibleForTesting
    byte[] readBody(final InputStream compressedBody) throws IOException {
        try (final ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            compressedBody.transferTo(outputStream);
            return outputStream.toByteArray();
        }
    }

    /**
     * Helper method to validate the response since OMDb does not follow standard HTTP response codes.
     *
     * @param bodyContents the response body
     * @throws IOException if unable to parse the response body
     */
    @VisibleForTesting
    void validateResponseBody(final byte[] bodyContents) throws IOException {
        try (final InputStreamReader contentReader = new InputStreamReader(new ByteArrayInputStream(bodyContents))) {
            final FailureResponse failureResponse = getGsonFactory()
                    .getInstance(this)
                    .fromJson(contentReader, FailureResponse.class);
            final Boolean responseValue = failureResponse.getResponse();
            if (Objects.isNull(responseValue) || !responseValue.booleanValue()) {
                throw new ResponseException(failureResponse.getError());
            }
        }
    }
}
