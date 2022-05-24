package com.modyo.pokemon.adapters.outbound.acl;

import com.modyo.pokemon.adapters.outbound.acl.translate.PokemonTranslate;
import com.modyo.pokemon.adapters.outbound.response.*;
import com.modyo.pokemon.domain.model.Pokemon;
import com.modyo.pokemon.util.Mocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PokemonRepositoryImplTest {

    @Mock
    private PokemonApi pokemonApi;

    @InjectMocks
    private PokemonRepositoryImpl pokemonRepository;

    @Spy
    private PokemonTranslate pokemonTranslate;

    @Test
    void getAll() {
        final PokemonListResponse pokemonListResponse = Mocks.getPokemonListResponse();
        final PokemonResponse pokemonResponse = Mocks.getPokemonResponse();
        given(pokemonApi.getAll(anyInt(), anyInt())).willReturn(pokemonListResponse);
        given(pokemonApi.getPokemonByName(anyString())).willReturn(pokemonResponse);

        List<Pokemon> result = pokemonRepository.getAll(1, 1);
        then(result.get(0).getAbilities()).isNotEmpty();
        then(result.get(0).getUrlDetail()).isNotBlank();
        then(result.get(0).getWeight()).isGreaterThan(0);
        then(result.get(0).getUrlProfileImage()).isNotBlank();


    }

    @Test
    void getPokemonByName() {
        final PokemonResponse pokemonResponse = Mocks.getPokemonResponse();
        given(pokemonApi.getPokemonByName(anyString())).willReturn(pokemonResponse);
        Pokemon result = pokemonRepository.getPokemonByName("pikachu");
        then(result.getAbilities()).isNotEmpty();
        then(result.getUrlDetail()).isNotBlank();
        then(result.getWeight()).isGreaterThan(0);
        then(result.getUrlProfileImage()).isNotBlank();
    }

    @Test
    void getPokemonSpeciesByName() {
        final PokemonSpeciesResponse pokemonSpeciesResponse = Mocks.getPokemonSpeciesResponse();
        given(pokemonApi.getPokemonSpeciesByName(anyString())).willReturn(pokemonSpeciesResponse);
        PokemonSpeciesResponse result = pokemonRepository.getPokemonSpeciesByName("pikachu");

        then(result).isNotNull();
        then(result.getFlavorTextEntries()).isNotEmpty();
        then(result.getEvolutionChain()).isNotNull();


    }

    @Test
    void getEvolutionChain() {
        final EvolutionChain evolutionChain = Mocks.getEvolutionChain();
        given(pokemonApi.getEvolutionChain(anyString())).willReturn(evolutionChain);
        EvolutionChain result = pokemonRepository.getEvolutionChain("1");
        then(result).isNotNull();
        then(result.getChain()).isNotNull();

    }
}