package com.modyo.pokemon.domain.service;

import com.modyo.pokemon.domain.model.*;

import java.util.List;

public interface PokemonService {

    List<Pokemon> getAll(Integer limit, Integer offset);

    PokemonDetail getPokemonDetailByName(String name);
}
