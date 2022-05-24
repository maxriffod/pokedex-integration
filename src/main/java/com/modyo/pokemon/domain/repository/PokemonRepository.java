package com.modyo.pokemon.domain.repository;

import com.modyo.pokemon.adapters.outbound.response.*;
import com.modyo.pokemon.domain.model.Pokemon;

import java.util.List;

public interface PokemonRepository {

    List<Pokemon> getAll(Integer limit, Integer offset);

    Pokemon getPokemonByName(String name);

    PokemonSpeciesResponse getPokemonSpeciesByName(String name);

    EvolutionChain getEvolutionChain(String id);

}
