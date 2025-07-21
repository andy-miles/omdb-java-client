<a name="readme-top"></a>
<!-- Template Credit: Othneil Drew (https://github.com/othneildrew),
                      https://github.com/othneildrew/Best-README-Template/tree/master -->
<!-- PROJECT SHIELDS -->
<div align="center">

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

</div>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://www.omdbapi.com/">
    <h1>The Open Movie Database</h1>
  </a>
  <h3 align="center">omdb-java-client</h3>

  <p align="center">
    A Java client to access The Open Movie Database (OMDb)
    <br />
    <a href="https://www.amilesend.com/omdb-java-client-"><strong>Maven Project Info</strong></a>
    -
    <a href="https://www.amilesend.com/omdb-java-client-/apidocs/index.html"><strong>Javadoc</strong></a>
    <br />
    <a href="https://github.com/andy-miles/omdb-java-client/issues">Report Bug</a>
    -
    <a href="https://github.com/andy-miles/omdb-java-client/issues">Request Feature</a>
  </p>
</div>


<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#usage">Usage</a>
      <ul>
        <li><a href="#getting-started">Getting Started</a></li>
        <li><a href="#recipes">Recipes</a></li>
      </ul>
    </li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
# About The Project

A client for Java programmatic access to [The Open Movie Database API](https://www.omdbapi.com/).

<div align="right">(<a href="#readme-top">back to top</a>)</div>

<a name="usage"></a>
# Usage
<a name="getting-started"></a>
## Getting Started

1. Per the [API documentation](https://www.omdbapi.com/apikey.aspx), you will need to obtain an API key order to make requests to the service.
2. Include this package as a dependency in your project. Note: This package is published to both
   GitHub and Maven Central repositories.

   ```xml
   <dependency>
       <groupId>com.amilesend</groupId>
       <artifactId>omdb-java-client</artifactId>
       <version>2.0</version>
   </dependency>
   ```
3. Instantiate the client with your API key

   ```java
   OMDb client = new OMDb("MyApiKey", "MyUserAgent/1.0");
   // Access APIs
   SearchResponse movieSearchResults =
           client.search(SearchMovieRequest.builder()
                   .title("Batman")
                   .build());
   ```

<div align="right">(<a href="#readme-top">back to top</a>)</div>

<a name="recipes"></a>
## Recipes
### Searching for content
```java
SearchResponse movieSearchResults =
        client.search(SearchMovieRequest.builder()
                .title("Batman")
                .page(2)
                .build());

SearchResposne tvSeriesSearchResults =
        client.search(SearchSeriesRequest.builder()
                .title("Batman")
                .build());
```

### Retrieving content by title
```java
OMDb client = new OMDb("MyApiKey");

Movie movie =
        client.getMovie(GetMovieByTitleRequest.builder()
                .title("Gladiator")
                .year(2000)
                .build());

Series tvSeries =
        client.getSeries(GetSeriesByTitleRequest.builder()
                .title("Game of Thrones")
                .build());

Season tvSeason =
        client.getSeason(GetSeasonByTitleRequest.builder()
                .title("Game of Thrones")
                .season(1)
                .build());

Episode tvEpisode =
        client.getEpisode(GetEpisodeByTitleRequest.builder()
                .title("Game of Thrones")
                .season(1)
                .episode(2)
                .build());
```

### Retrieving content by IMDB ID

```java
OMDb client = new OMDb("MyApiKey");

Movie movie =
        client.getMovie(GetMovieByIdRequest.builder()
                .imdbId("tt0172495")
                .plot(Plot.FULL)
                .build());

Series tvSeries =
        client.getSeries(GetSeriesByIdRequest.builder()
                .imdbId("tt0439100")
                .build());

Season tvSeason =
        client.getSeason(GetSeasonByIdRequest.builder()
                .imdbId("tt0944947")
                .season(1)
                .build());

Episode tvEpisode =
        client.getEpisode(GetEpisodeByIdRequest.builder()
                .imdbId("tt0944947")
                .season(1)
                .episode(2)
                .build());
```

### Customizing the HTTP client configuration

<details>
<summary>OkHttpClientBuilder example</summary>

If your use-case requires configuring the underlying <code>OkHttpClient</code> instance (e.g., configuring your own
SSL cert verification, proxy, and/or connection timeouts), you can configure the client with the provided
[OkHttpClientBuilder](https://github.com/andy-miles/omdb-java-client/blob/main/src/main/java/com/amilesend/omdb/client/connection/http/OkHttpClientBuilder.java),
or alternatively with [OkHttp's builder](https://square.github.io/okhttp/4.x/okhttp/okhttp3/-ok-http-client/).

```java
OkHttpClient httpClient = OkHttpClientBuilder.builder()
        .trustManager(myX509TrustManager) // Custom trust manager for self/internally signed SSL/TLS certs
        .hostnameVerifier(myHostnameVerifier) // Custom hostname verification for SSL/TLS endpoints
        .proxy(myProxy, myProxyUsername, myProxyPassword) // Proxy config
        .connectTimeout(8000L) // connection timeout in milliseconds
        .readTimeout(5000L) // read timeout in milliseconds
        .writeTimeout(5000L) // write timeout in milliseconds
        .build();
Connection connection = Connection.builder()
        .httpClient(httpClient)
        .gsonFactory(GsonFactory.getInstance())
        .build();

OMDb client = new OMDb("MyApiKey", connection);
```

</details>


<div align="right">(<a href="#readme-top">back to top</a>)</div>

<!-- CONTRIBUTING -->
## Contributing

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/MyFeature`)
3. Commit your Changes (`git commit -m 'Add my feature'`)
4. Push to the Branch (`git push origin feature/MyFeature`)
5. Open a Pull Request

<div align="right">(<a href="#readme-top">back to top</a>)</div>

<!-- LICENSE -->
## License

Distributed under the GPLv3 license. See [LICENSE](https://github.com/andy-miles/omdb-java-client/blob/main/LICENSE) for more information.

<div align="right">(<a href="#readme-top">back to top</a>)</div>


<!-- CONTACT -->
## Contact

Andy Miles - andy.miles (at) amilesend.com

Project Link: [https://github.com/andy-miles/omdb-java-client](https://github.com/andy-miles/omdb-java-client)

<div align="right">(<a href="#readme-top">back to top</a>)</div>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/andy-miles/omdb-java-client.svg?style=for-the-badge
[contributors-url]: https://github.com/andy-miles/omdb-java-client/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/andy-miles/omdb-java-client.svg?style=for-the-badge
[forks-url]: https://github.com/andy-miles/omdb-java-client/network/members
[stars-shield]: https://img.shields.io/github/stars/andy-miles/omdb-java-client.svg?style=for-the-badge
[stars-url]: https://github.com/andy-miles/omdb-java-client/stargazers
[issues-shield]: https://img.shields.io/github/issues/andy-miles/omdb-java-client.svg?style=for-the-badge
[issues-url]: https://github.com/andy-miles/omdb-java-client/issues
[license-shield]: https://img.shields.io/github/license/andy-miles/omdb-java-client.svg?style=for-the-badge
[license-url]: https://github.com/andy-miles/omdb-java-client/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/andy-miles
[product-screenshot]: images/screenshot.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
