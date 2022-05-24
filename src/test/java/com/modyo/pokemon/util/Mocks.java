package com.modyo.pokemon.util;

import com.modyo.pokemon.adapters.outbound.response.*;
import com.modyo.pokemon.domain.model.*;

import java.util.*;

public class Mocks {

    public static List<Pokemon> getPokemonList() {
        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(getPokemon());
        return pokemonList;
    }

    public static Pokemon getPokemon() {
        return Pokemon.builder()
                .weight(100)
                .abilities(List.of("ability1", "ability2"))
                .types(List.of("type1", "type2"))
                .urlProfileImage("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png")
                .urlDetail("url")
                .build();
    }

    public static PokemonDetail getPokemonDetail() {
        return PokemonDetail.builder()
                .basicPokemonInformation(getPokemon())
                .description("description")
                .evolutions(List.of("evolution1", "evolution2"))
                .build();
    }

    public static NameUrlResponse getNameUrlResponse(String url, String name) {
        NameUrlResponse nameUrlResponse = new NameUrlResponse();
        nameUrlResponse.setName(name);
        nameUrlResponse.setUrl(url);
        return nameUrlResponse;
    }


    public static PokemonSpeciesResponse getPokemonSpeciesResponse() {

        PokemonSpeciesResponse pokemonSpeciesResponse = new PokemonSpeciesResponse();
        pokemonSpeciesResponse.setEvolutionChain(getNameUrlResponse("http://pokemon/1", "name"));
        FlavorTextEntriesResponse flavorTextEntriesResponse = new FlavorTextEntriesResponse();
        flavorTextEntriesResponse.setLanguage(getNameUrlResponse("url", "en"));
        flavorTextEntriesResponse.setFlavorText("description");
        List<FlavorTextEntriesResponse> flavorTextEntriesResponses = new ArrayList<>();
        flavorTextEntriesResponses.add(flavorTextEntriesResponse);
        pokemonSpeciesResponse.setFlavorTextEntries(flavorTextEntriesResponses);
        return pokemonSpeciesResponse;
    }

    public static EvolutionTo getEvolutionTo() {
        EvolutionTo evolutionTo = new EvolutionTo();
        evolutionTo.setSpecies(getNameUrlResponse("url", "evolution1"));
        EvolutionTo evolutionToFinal = new EvolutionTo();
        evolutionToFinal.setSpecies(getNameUrlResponse("url", "name"));
        evolutionTo.setEvolvesTo(Collections.emptyList());
        return evolutionTo;
    }

    public static EvolutionChain getEvolutionChain() {
        EvolutionChain evolutionChain = new EvolutionChain();
        evolutionChain.setChain(getEvolutionTo());
        return evolutionChain;
    }

    public static PokemonListResponse getPokemonListResponse() {
        PokemonListResponse pokemonListResponse = new PokemonListResponse();
        pokemonListResponse.setCount(1);
        pokemonListResponse.setNext("next");
        pokemonListResponse.setPrevious("previous");
        pokemonListResponse.setResults(List.of(getNameUrlResponse("url", "name")));
        return pokemonListResponse;
    }

    public static AbilitiesResponse getAbilitiesResponse() {
        AbilitiesResponse abilitiesResponse = new AbilitiesResponse();
        abilitiesResponse.setAbility(getNameUrlResponse("url", "abilityName"));
        return abilitiesResponse;
    }

    public static SpritesResponse getSpritesResponse() {
        SpritesResponse spritesResponse = new SpritesResponse();
        spritesResponse.setBackDefault("back_default");
        return spritesResponse;
    }

    public static List<TypesResponse> getTypesResponse() {
        List<TypesResponse> typesResponse = new ArrayList<>();
        TypesResponse typeResponse = new TypesResponse();
        typeResponse.setType(getNameUrlResponse("url", "type1"));
        typesResponse.add(typeResponse);
        return typesResponse;
    }

    public static PokemonResponse getPokemonResponse() {
        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setAbilities(List.of(getAbilitiesResponse()));
        pokemonResponse.setWeight(100);
        pokemonResponse.setId(1);
        pokemonResponse.setSprites(getSpritesResponse());
        pokemonResponse.setTypes(getTypesResponse());
        pokemonResponse.setName("name");
        return pokemonResponse;
    }
}
