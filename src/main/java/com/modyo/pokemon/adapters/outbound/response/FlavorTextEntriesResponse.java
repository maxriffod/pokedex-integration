package com.modyo.pokemon.adapters.outbound.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

@Getter
@Setter
public class FlavorTextEntriesResponse {
    @JsonAlias("flavor_text")
    private String flavorText;
    private NameUrlResponse language;

}
