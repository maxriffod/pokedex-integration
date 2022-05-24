/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.modyo.pokemon.adapters.inbound.api.controller;

import com.modyo.pokemon.domain.model.*;
import com.modyo.pokemon.domain.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/pokemon")
@Validated
public class PokemonController {

    private final PokemonService pokemonService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Pokemon> getAll(@RequestParam(required = false, defaultValue = "20") @Valid @Min(1) @Max(100) Integer limit,
                                @RequestParam(required = false, defaultValue = "0") @Valid @Min(0) Integer offset) {
        return pokemonService.getAll(limit, offset);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{name}")
    public PokemonDetail getPokemonDetailByName(@PathVariable @NotBlank String name) {
        return pokemonService.getPokemonDetailByName(name);

    }

}
