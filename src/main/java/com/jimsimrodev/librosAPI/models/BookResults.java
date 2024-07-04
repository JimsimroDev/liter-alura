package com.jimsimrodev.librosAPI.models;

import ch.qos.logback.classic.pattern.LineSeparatorConverter;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.beans.JavaBean;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookResults(
        @JsonAlias("results")List<BookData> resultados
        ) {
}
