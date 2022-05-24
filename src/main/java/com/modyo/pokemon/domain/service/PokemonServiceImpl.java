package com.modyo.pokemon.domain.service;

import com.modyo.pokemon.adapters.outbound.response.*;
import com.modyo.pokemon.domain.model.*;
import com.modyo.pokemon.domain.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.*;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    @Override
    public List<Pokemon> getAll(Integer limit, Integer offset) {
        return pokemonRepository.getAll(limit, offset);
    }

    @Override
    public PokemonDetail getPokemonDetailByName(String name) {
        Pokemon basicPokemon = pokemonRepository.getPokemonByName(name);
        PokemonSpeciesResponse pokemonSpecie = pokemonRepository.getPokemonSpeciesByName(name);
        String idEvolutionChain = getIdFromUrl(pokemonSpecie.getEvolutionChain().getUrl());
        EvolutionChain evolutionChain = pokemonRepository.getEvolutionChain(idEvolutionChain);
        List<String> evolutions = getEvolutionChain(evolutionChain);
        String descriptions = getDescriptions(pokemonSpecie.getFlavorTextEntries());
        return PokemonDetail.builder()
                .basicPokemonInformation(basicPokemon)
                .description(descriptions)
                .evolutions(evolutions)
                .build();
    }

    private String getDescriptions(List<FlavorTextEntriesResponse> flavorTextEntries) {
        if (flavorTextEntries.isEmpty()) {
            return "";
        }
        return flavorTextEntries.stream()
                .filter(flavorTextEntriesResponse -> flavorTextEntriesResponse.getLanguage().getName().equals("en"))
                .map(FlavorTextEntriesResponse::getFlavorText)
                .findFirst().orElse("").replaceAll("(\\f|\\n)", "");
    }

    private List<String> getEvolutionChain(EvolutionChain evolutionChain) {
        List<String> evolutions = new ArrayList<>();
        evolutions.add(evolutionChain.getChain().getSpecies().getName());
        evolutions.addAll(getEvolutionTo(evolutionChain.getChain().getEvolvesTo()).stream()
                .map(EvolutionTo::getSpecies)
                .map(NameUrlResponse::getName)
                .collect(Collectors.toList()));
        return evolutions;
    }

    private List<EvolutionTo> getEvolutionTo(List<EvolutionTo> evolutions) {
        // Get all evolutions
        return evolutions.stream().flatMap(i ->
                        Stream.concat(Stream.of(i),
                                getEvolutionTo(i.getEvolvesTo()).stream()))
                .collect(Collectors.toList());


    }

    private String getIdFromUrl(String url) {
        try {
            return Path.of(new URI(url).getPath()).getFileName().toString();
        } catch (URISyntaxException e) {
            log.error("Error getting id from url: {}", e.getMessage());
            return "";
        }

    }
}
