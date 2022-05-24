package com.modyo.pokemon.adapters.outbound.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class PokemonListResponse {
    private int count;
    private String next;
    private String previous;
    private List<NameUrlResponse> results;
}
