package com.diacencodumitru.url_shortener.service;

import com.diacencodumitru.url_shortener.domain.dto.url.UrlRequestDTO;
import com.diacencodumitru.url_shortener.domain.dto.url.UrlResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

public interface UrlService {

    UrlResponseDTO shortenUrl(UrlRequestDTO data, HttpServletRequest request);
    HttpHeaders redirect(String id);
}
