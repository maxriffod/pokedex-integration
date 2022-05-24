package com.modyo.pokemon.adapters.outbound.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class PokemonResponse {
    private List<AbilitiesResponse> abilities;
    private int weight;
    private int id;
    private SpritesResponse sprites;
    private List<TypesResponse> types;
    private String name;
}
