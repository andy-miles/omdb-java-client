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
package com.amilesend.omdb.data;

import com.amilesend.omdb.model.movie.type.Movie;
import com.amilesend.omdb.model.tv.type.Episode;
import com.amilesend.omdb.model.tv.type.Season;
import com.amilesend.omdb.model.tv.type.SeasonEpisode;
import com.amilesend.omdb.model.tv.type.Series;
import com.amilesend.omdb.model.type.MediaType;
import com.amilesend.omdb.model.type.Rating;
import com.amilesend.omdb.model.type.SearchResponse;
import com.amilesend.omdb.model.type.SearchResult;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.List;

@UtilityClass
public class DataHelper {
    public static Movie newMovie() {
        return Movie.builder()
                .title("Gladiator")
                .year("2000")
                .rated("R")
                .released(LocalDate.of(2000, 5, 5))
                .runtime("155 min")
                .genre("Action, Adventure, Drama")
                .director("Ridley Scott")
                .writer("David Franzoni, John Logan, William Nicholson")
                .actors("Russell Crowe, Joaquin Phoenix, Connie Nielsen")
                .plot("A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery.")
                .language("English")
                .country("United States, United Kingdom, Malta, Morocco")
                .awards("Won 5 Oscars. 60 wins & 104 nominations total")
                .poster("https://m.media-amazon.com/images/M/MV5BYWQ4YmNjYjEtOWE1Zi00Y2U4LWI4NTAtMTU0MjkxNWQ1ZmJiXkEyXkFqcGc@._V1_SX300.jpg")
                .ratings(newMovieRatings())
                .metascore("67")
                .imdbRating("8.5")
                .imdbVotes("1,728,425")
                .imdbId("tt0172495")
                .dvd(null)
                .boxOffice("$187,705,427")
                .production(null)
                .website(null)
                .build();
    }

    public static List<Rating> newMovieRatings() {
        return List.of(
                new Rating("Internet Movie Database", "8.5/10"),
                new Rating("Rotten Tomatoes", "80%"),
                new Rating("Metacritic", "67/100"));
    }

    public static Series newSeries() {
        return Series.builder()
                .title("Game of Thrones")
                .year("2011–2019")
                .rated("TV-MA")
                .released(LocalDate.of(2011, 4, 17))
                .runtime("57 min")
                .genre("Action, Adventure, Drama")
                .director(null)
                .writer("David Benioff, D.B. Weiss")
                .actors("Emilia Clarke, Peter Dinklage, Kit Harington")
                .plot("Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia.")
                .language("English")
                .country("United States, United Kingdom")
                .awards("Won 59 Primetime Emmys. 398 wins & 655 nominations total")
                .poster("https://m.media-amazon.com/images/M/MV5BMTNhMDJmNmYtNDQ5OS00ODdlLWE0ZDAtZTgyYTIwNDY3OTU3XkEyXkFqcGc@._V1_SX300.jpg")
                .ratings(List.of(new Rating("Internet Movie Database", "9.2/10")))
                .metascore(null)
                .imdbRating("9.2")
                .imdbVotes("2,409,483")
                .imdbId("tt0944947")
                .totalSeasons("8")
                .build();
    }

    public static Season newSeason() {
        return Season.builder()
                .title("Game of Thrones")
                .season("1")
                .totalSeasons("8")
                .episodes(List.of(
                        SeasonEpisode.builder()
                                .title("Unaired Original Pilot")
                                .released(null)
                                .episode("0")
                                .imdbRating(null)
                                .imdbId("tt31321401")
                                .build(),
                        SeasonEpisode.builder()
                                .title("Winter Is Coming")
                                .released(LocalDate.of(2011, 4, 17))
                                .episode("1")
                                .imdbRating("8.9")
                                .imdbId("tt1480055")
                                .build(),
                        SeasonEpisode.builder()
                                .title("The Kingsroad")
                                .released(LocalDate.of(2011, 4, 24))
                                .episode("2")
                                .imdbRating("8.6")
                                .imdbId("tt1668746")
                                .build()))
                .build();
    }

    public static Episode newEpisode() {
        return Episode.builder()
                .title("Winter Is Coming")
                .year("2011")
                .rated("TV-MA")
                .released(LocalDate.of(2011, 4, 17))
                .season("1")
                .episode("1")
                .runtime("62 min")
                .genre("Action, Adventure, Drama")
                .director("Timothy Van Patten")
                .writer("David Benioff, D.B. Weiss, George R.R. Martin")
                .actors("Sean Bean, Mark Addy, Nikolaj Coster-Waldau")
                .plot("Lord Eddard Stark is concerned by news of a deserter from the Night's Watch; King Robert I Baratheon and the Lannisters arrive at Winterfell; the exiled Prince Viserys Targaryen forges a powerful new alliance.")
                .language("English")
                .country("United States")
                .awards(null)
                .poster("https://m.media-amazon.com/images/M/MV5BZDU5ZGEzODYtYWVlNC00NjYyLWJjOWYtYmNhZTc2MzUwYTliXkEyXkFqcGc@._V1_SX300.jpg")
                .ratings(List.of(new Rating("Internet Movie Database", "8.9/10")))
                .metascore(null)
                .imdbRating("8.9")
                .imdbVotes("59069")
                .imdbId("tt1480055")
                .seriesId("tt0944947")
                .build();
    }

    public static SearchResponse newMovieSearchResponse() {
        return SearchResponse.builder()
                .search(List.of(
                        SearchResult.builder()
                                .title("Batman Begins")
                                .year("2005")
                                .imdbDb("tt0372784")
                                .type(MediaType.MOVIE)
                                .poster("https://m.media-amazon.com/images/M/MV5BODIyMDdhNTgtNDlmOC00MjUxLWE2NDItODA5MTdkNzY3ZTdhXkEyXkFqcGc@._V1_SX300.jpg")
                                .build(),
                        SearchResult.builder()
                                .title("The Batman")
                                .year("2022")
                                .imdbDb("tt1877830")
                                .type(MediaType.MOVIE)
                                .poster("https://m.media-amazon.com/images/M/MV5BMmU5NGJlMzAtMGNmOC00YjJjLTgyMzUtNjAyYmE4Njg5YWMyXkEyXkFqcGc@._V1_SX300.jpg")
                                .build(),
                        SearchResult.builder()
                                .title("Batman v Superman: Dawn of Justice")
                                .year("2016")
                                .imdbDb("tt2975590")
                                .type(MediaType.MOVIE)
                                .poster("https://m.media-amazon.com/images/M/MV5BZTJkYjdmYjYtOGMyNC00ZGU1LThkY2ItYTc1OTVlMmE2YWY1XkEyXkFqcGc@._V1_SX300.jpg")
                                .build(),
                        SearchResult.builder()
                                .title("Batman")
                                .year("1989")
                                .imdbDb("tt0096895")
                                .type(MediaType.MOVIE)
                                .poster("https://m.media-amazon.com/images/M/MV5BYzZmZWViM2EtNzhlMi00NzBlLWE0MWEtZDFjMjk3YjIyNTBhXkEyXkFqcGc@._V1_SX300.jpg")
                                .build()))
                .totalResults("487")
                .build();
    }

    public static SearchResponse newSeriesSearchResponse() {
        return SearchResponse.builder()
                .search(newSeriesSearchResults())
                .totalResults("5")
                .build();
    }

    public static List<SearchResult> newSeriesSearchResults() {
        return List.of(
                SearchResult.builder()
                        .title("Game of Thrones")
                        .year("2011–2019")
                        .imdbDb("tt0944947")
                        .type(MediaType.SERIES)
                        .poster("https://m.media-amazon.com/images/M/MV5BMTNhMDJmNmYtNDQ5OS00ODdlLWE0ZDAtZTgyYTIwNDY3OTU3XkEyXkFqcGc@._V1_SX300.jpg")
                        .build(),
                SearchResult.builder()
                        .title("Stupid for Game of Thrones")
                        .year("2012")
                        .imdbDb("tt2143796")
                        .type(MediaType.SERIES)
                        .poster(null)
                        .build(),
                SearchResult.builder()
                        .title("Vennori: Lets Play - Game of Thrones")
                        .year("2014–")
                        .imdbDb("tt5779388")
                        .type(MediaType.SERIES)
                        .poster("https://m.media-amazon.com/images/M/MV5BMDllZTA2ZTItZDQ4YS00ZDk0LThlZDctZGVlZDY5MmExM2Y1XkEyXkFqcGdeQXVyMzUwNDgxNTY@._V1_SX300.jpg")
                        .build(),
                SearchResult.builder()
                        .title("GoT Thrones? A Game of Thrones Podcast")
                        .year("2015–2019")
                        .imdbDb("tt14371206")
                        .type(MediaType.SERIES)
                        .poster(null)
                        .build(),
                SearchResult.builder()
                        .title("World's Deadliest: Game of Thrones")
                        .year("2014–")
                        .imdbDb("tt4845438")
                        .type(MediaType.SERIES)
                        .poster("https://m.media-amazon.com/images/M/MV5BNzM3YWM1NTItODI5YS00ODYyLTkyNjAtMDQxZTJkMDY5ODA3XkEyXkFqcGdeQXVyMTI0MzYwMjEw._V1_SX300.jpg")
                        .build());
    }

    @UtilityClass
    public static class Responses {
        public static final SerializedResource MOVIE = new SerializedResource("/Movie.json");
        public static final SerializedResource SERIES = new SerializedResource("/Series.json");
        public static final SerializedResource SEASON = new SerializedResource("/Season.json");
        public static final SerializedResource EPISODE = new SerializedResource("/Episode.json");
        public static final SerializedResource MOVIE_SEARCH =
                new SerializedResource("/MovieSearchResponse.json");
        public static final SerializedResource SERIES_SEARCH =
                new SerializedResource("/SeriesSearchResponse.json");
    }
}
