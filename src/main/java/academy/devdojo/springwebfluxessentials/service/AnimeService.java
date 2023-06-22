package academy.devdojo.springwebfluxessentials.service;

import academy.devdojo.springwebfluxessentials.domain.Anime;
import academy.devdojo.springwebfluxessentials.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;
    public Flux<Anime> findAll() {
        return animeRepository.findAll();
    }

    public Mono<Anime> findById(int id){
        return animeRepository.findById(id)
                .switchIfEmpty(monoResponseStatusNotFoundException());
    }

    public <T> Mono<T> monoResponseStatusNotFoundException(){
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found."));
    }

    public Mono<Anime> save(Anime anime) {
        return animeRepository.save(anime);
    }

    public Mono<Void> update(Anime anime){
        return findById(anime.getId())
                .doOnError(error -> {
                    System.out.println("Erro durante a atualização: " + error.getMessage());
                    error.printStackTrace();
                })
                .map(animeFound -> anime.withId(animeFound.getId()))
                .flatMap(animeRepository::save)
                .thenEmpty(Mono.empty());
    }
}
