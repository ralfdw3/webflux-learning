package academy.devdojo.springwebfluxessentials.controller;

import academy.devdojo.springwebfluxessentials.domain.Anime;
import academy.devdojo.springwebfluxessentials.repository.AnimeRepository;
import academy.devdojo.springwebfluxessentials.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("animes")
@Slf4j
public class AnimeController {
    private final AnimeService animeService;

    @GetMapping
    public Flux<Anime> listAll(){
        return animeService.findAll();
    }

    @GetMapping(path = "{id}")
    public Mono<Anime> listById(@PathVariable int id){
        return animeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Anime> save(@Valid @RequestBody Anime anime){
        return animeService.save(anime);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> update(@Valid @RequestBody Anime anime){
        return animeService.update(anime);
    }

}
