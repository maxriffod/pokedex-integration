package com.modyo.pokemon.domain.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class Pokemon {

    private String urlProfileImage;
    private List<String> types;
    private int weight;
    private List<String> abilities;
    private String urlDetail;

}
