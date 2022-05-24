package com.modyo.pokemon.domain.service;

import com.modyo.pokemon.adapters.outbound.response.*;
import com.modyo.pokemon.domain.model.*;
import com.modyo.pokemon.domain.repository.PokemonRepository;
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
class PokemonServiceImplTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Test
    void getAllPokemon() {
        final List<Pokemon> mockPokemonList = Mocks.getPokemonList();
        given(pokemonRepository.getAll(anyInt(), anyInt())).willReturn(mockPokemonList);

        List<Pokemon> result = pokemonService.getAll(10, 0);
        then(result.get(0).getAbilities().get(0)).isEqualTo(mockPokemonList.get(0).getAbilities().get(0));
        then(result.get(0).getAbilities().get(1)).isEqualTo(mockPokemonList.get(0).getAbilities().get(1));

        then(result.get(0).getWeight()).isEqualTo(mockPokemonList.get(0).getWeight());
        then(result.get(0).getUrlDetail()).isEqualTo(mockPokemonList.get(0).getUrlDetail());
        then(result.get(0).getTypes().get(0)).isEqualTo(mockPokemonList.get(0).getTypes().get(0));
        then(result.get(0).getTypes().get(1)).isEqualTo(mockPokemonList.get(0).getTypes().get(1));
    }

    @Test
    void getDetailPokemon() {
        final PokemonDetail mockPokemonDetail = Mocks.getPokemonDetail();
        final PokemonSpeciesResponse mockPokemonSpeciesResponse = Mocks.getPokemonSpeciesResponse();
        final EvolutionChain mockEvolutionChain = Mocks.getEvolutionChain();

        final String POKEMON_NAME = "bulbasaur";
        given(pokemonRepository.getPokemonByName(anyString())).willReturn(mockPokemonDetail.getBasicPokemonInformation());
        given(pokemonRepository.getPokemonSpeciesByName(anyString())).willReturn(mockPokemonSpeciesResponse);
        given(pokemonRepository.getEvolutionChain(anyString())).willReturn(mockEvolutionChain);

        PokemonDetail result = pokemonService.getPokemonDetailByName(POKEMON_NAME);

        then(result.getBasicPokemonInformation().getAbilities().get(0))
                .isEqualTo(mockPokemonDetail.getBasicPokemonInformation().getAbilities().get(0));
        then(result.getBasicPokemonInformation().getAbilities().get(1))
                .isEqualTo(mockPokemonDetail.getBasicPokemonInformation().getAbilities().get(1));

        then(result.getBasicPokemonInformation().getWeight()).isEqualTo(mockPokemonDetail.getBasicPokemonInformation().getWeight());
        then(result.getBasicPokemonInformation().getUrlDetail()).isEqualTo(mockPokemonDetail.getBasicPokemonInformation().getUrlDetail());
        then(result.getBasicPokemonInformation().getTypes().get(0)).isEqualTo(mockPokemonDetail.getBasicPokemonInformation().getTypes().get(0));
        then(result.getBasicPokemonInformation().getTypes().get(1)).isEqualTo(mockPokemonDetail.getBasicPokemonInformation().getTypes().get(1));

        then(result.getDescription()).isEqualTo(mockPokemonDetail.getDescription());
        then(result.getEvolutions().get(0)).isEqualTo(mockPokemonDetail.getEvolutions().get(0));
        

    }
}