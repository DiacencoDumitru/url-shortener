package com.diacencodumitru.url_shortener.service.impl;

import com.diacencodumitru.url_shortener.domain.dto.url.UrlRequestDTO;
import com.diacencodumitru.url_shortener.domain.dto.url.UrlResponseDTO;
import com.diacencodumitru.url_shortener.entities.UrlEntity;
import com.diacencodumitru.url_shortener.exceptions.UrlNotFoundException;
import com.diacencodumitru.url_shortener.repository.UrlRepository;
import com.diacencodumitru.url_shortener.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    @Override
    public UrlResponseDTO shortenUrl(UrlRequestDTO data, HttpServletRequest request) {
        String id;

        do {
            // Generate a random alphanumeric string with a length between 5 and 10 characters
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));

        // Save the URL in the database with an expiration time of 1 minute
        urlRepository.save(new UrlEntity(id, data.url(), LocalDateTime.now().plusMinutes(1)));

        // {POST} url/shorten-url (shorten-url меняем на короткий redirectUrl)
        String redirectUrl = request.getRequestURL().toString().replace("shorten-url", id);

        return new UrlResponseDTO(data.url(), redirectUrl);
    }

    @Override
    public HttpHeaders redirect(String id) {
        UrlEntity url = urlRepository.findById(id).orElseThrow(() -> new UrlNotFoundException(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getUrl()));
        return headers;
    }
}
