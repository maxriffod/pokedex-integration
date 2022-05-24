package com.modyo.pokemon.domain.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class PokemonDetail {

    
    private Pokemon basicPokemonInformation;
    private String description;
    private List<String> evolutions;

}
