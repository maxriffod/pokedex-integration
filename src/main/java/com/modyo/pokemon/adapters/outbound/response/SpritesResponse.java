package com.modyo.pokemon.adapters.outbound.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

@Getter
@Setter
public class SpritesResponse {
    @JsonAlias({"back_default"})
    private String backDefault;
}
