package com.modyo.pokemon.adapters.outbound.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class EvolutionTo {

    private NameUrlResponse species;
    @JsonAlias("evolves_to")
    List<EvolutionTo> evolvesTo;
}
