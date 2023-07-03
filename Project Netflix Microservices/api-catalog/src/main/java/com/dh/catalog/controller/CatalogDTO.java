package com.dh.catalog.controller;
import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import lombok.*;
import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatalogDTO {
    private List<MovieServiceClient.MovieDto> movies;
    private List<SerieServiceClient.SerieDTO> series;
}
