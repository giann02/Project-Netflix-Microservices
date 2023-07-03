package com.dh.catalog.client;
import lombok.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
@FeignClient(name="api-movie")
public interface MovieServiceClient {
	@GetMapping("/api/v1/movies/{genre}")
	List<MovieDto> getMovieByGenre(@PathVariable (value = "genre") String genre);
	@PostMapping("/api/v1/movies/save")
	MovieDto saveMovie(@RequestBody MovieDto movie);
	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	class MovieDto{
		private Long id;

		private String name;

		private String genre;

		private String urlStream;
	}

}
