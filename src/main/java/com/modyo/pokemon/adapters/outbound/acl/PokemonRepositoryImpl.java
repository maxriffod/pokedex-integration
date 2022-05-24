package com.modyo.pokemon.adapters.outbound.acl;

import com.modyo.pokemon.adapters.outbound.acl.translate.PokemonTranslate;
import com.modyo.pokemon.adapters.outbound.response.*;
import com.modyo.pokemon.domain.model.Pokemon;
import com.modyo.pokemon.domain.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PokemonRepositoryImpl implements PokemonRepository {

    private final PokemonApi pokemonApi;
    private final PokemonTranslate pokemonTranslate;

    @Override
    public List<Pokemon> getAll(Integer limit, Integer offset) {

        PokemonListResponse pokemonListResponse = pokemonApi.getAll(limit, offset);
        List<PokemonResponse> pokemonResponses = new ArrayList<>();
        pokemonListResponse.getResults().stream().forEach(
                pokemonNameResponse ->
                        pokemonResponses.add(pokemonApi.getPokemonByName(pokemonNameResponse.getName()))
        );
        return pokemonTranslate.pokemonsTranslate(pokemonResponses);
    }

    @Override
    public Pokemon getPokemonByName(String name) {
        PokemonResponse pokemonResponse = pokemonApi.getPokemonByName(name);
        return pokemonTranslate.getBasicPokemonTranslate(pokemonResponse);
    }

    @Override
    public PokemonSpeciesResponse getPokemonSpeciesByName(String name) {
        return pokemonApi.getPokemonSpeciesByName(name);
    }

    @Override
    public EvolutionChain getEvolutionChain(String id) {
        return pokemonApi.getEvolutionChain(id);
    }
}
