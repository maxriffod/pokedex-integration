package com.modyo.pokemon.adapters.outbound.acl.translate;

import com.modyo.pokemon.adapters.inbound.api.controller.PokemonController;
import com.modyo.pokemon.adapters.outbound.response.*;
import com.modyo.pokemon.domain.model.Pokemon;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PokemonTranslate {

    public List<Pokemon> pokemonsTranslate(List<PokemonResponse> pokemonResponses) {

        List<Pokemon> pokemons = new ArrayList<>();
        pokemonResponses.forEach(pokemonResponse -> {
            pokemons.add(getBasicPokemonTranslate(pokemonResponse));
        });
        return pokemons;
    }

    public Pokemon getBasicPokemonTranslate(PokemonResponse response) {

        return Pokemon
                .builder()
                .urlProfileImage(response.getSprites().getBackDefault())
                .weight(response.getWeight())
                .types(response.getTypes().stream()
                        .map(TypesResponse::getType)
                        .map(NameUrlResponse::getName)
                        .collect(Collectors.toList()))
                .abilities(response.getAbilities()
                        .stream()
                        .map(AbilitiesResponse::getAbility)
                        .map(NameUrlResponse::getName)
                        .collect(Collectors.toList()))
                .urlDetail(linkTo(methodOn(PokemonController.class)
                        .getPokemonDetailByName(response.getName()))
                        .withSelfRel().getHref())
                .build();
    }


}
