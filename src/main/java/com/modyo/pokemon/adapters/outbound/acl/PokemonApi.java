package com.modyo.pokemon.adapters.outbound.acl;


import com.modyo.pokemon.adapters.outbound.response.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "pokemon-api", url = "${api.pokemon}")
public interface PokemonApi {

    @GetMapping("/pokemon")
    @Cacheable
    PokemonListResponse getAll(@RequestParam Integer limit, @RequestParam Integer offset);

    @GetMapping("/pokemon/{name}")
    @Cacheable
    PokemonResponse getPokemonByName(@PathVariable String name);

    @GetMapping("/pokemon-species/{name}")
    @Cacheable
    PokemonSpeciesResponse getPokemonSpeciesByName(@PathVariable String name);

    @GetMapping("/evolution-chain/{id}")
    @Cacheable
    EvolutionChain getEvolutionChain(@PathVariable String id);

}

