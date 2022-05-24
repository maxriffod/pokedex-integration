package com.modyo.pokemon.adapters.outbound.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class PokemonSpeciesResponse {
    @JsonAlias("evolution_chain")
    private NameUrlResponse evolutionChain;
    @JsonAlias("flavor_text_entries")
    private List<FlavorTextEntriesResponse> flavorTextEntries;
}
