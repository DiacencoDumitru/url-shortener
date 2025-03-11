# URL Shortener

This is a simple URL shortener application built with Java and Spring Boot.

## Features

- Shorten long URLs
- Redirect to the original URL using the shortened URL
- URLs expire after 1 minute

## Technologies Used

- Java
- Spring Boot
- MongoDB
- Maven
- Docker

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- MongoDB
- Docker

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/DiacencoDumitru/url-shortener.git
    cd url-shortener
    ```

2. Configure MongoDB in `src/main/resources/application.yml` if necessary.

3. Build the project:
    ```sh
    mvn clean install
    ```

4. Run the application:
    ```sh
    mvn spring-boot:run
    ```

### Using Docker

1. Build the Docker image:
    ```sh
    docker build -t url-shortener .
    ```

2. Run the Docker container:
    ```sh
    docker run -p 8080:8080 url-shortener
    ```

### Usage

1. To shorten a URL, send a POST request to `/url/shorten-url` with the following JSON body:
    ```json
    {
        "url": "https://example.com"
    }
    ```

2. The response will contain the original URL and the shortened URL.

3. To redirect to the original URL, use the shortened URL in your browser.

## License

This project is licensed under the MIT License.