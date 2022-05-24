package com.modyo.pokemon.adapters.inbound.api.controller;

import com.modyo.pokemon.domain.exception.RestExceptionHandler;
import com.modyo.pokemon.domain.model.*;
import com.modyo.pokemon.domain.service.PokemonService;
import com.modyo.pokemon.util.Mocks;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PokemonControllerTest {
    private MockMvc mockMvc;

    @Mock
    private PokemonService pokemonService;

    @InjectMocks
    private PokemonController pokemonController;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders
                .standaloneSetup(pokemonController)
                .setControllerAdvice(RestExceptionHandler.class)
                .alwaysDo(print())
                .build();
    }

    @Test
    void getPokemon_OK() throws Exception {
        final List<Pokemon> mockPokemonList = Mocks.getPokemonList();
        BDDMockito.given(pokemonService.getAll(anyInt(), anyInt())).willReturn(mockPokemonList);

        mockMvc.perform(get("/pokemon")
                        .param("limit", "10")
                        .param("offset", "0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].urlDetail").value(mockPokemonList.get(0).getUrlDetail()))
                .andExpect(jsonPath("$[0].types").isArray())
                .andExpect(jsonPath("$[0].urlProfileImage").value(mockPokemonList.get(0).getUrlProfileImage()))
                .andExpect(jsonPath("$[0].weight").value(mockPokemonList.get(0).getWeight()))
                .andExpect(jsonPath("$[0].abilities").isArray());


    }

    @Test
    void getPokemonDetail_OK() throws Exception {
        final PokemonDetail mockPokemonDetail = Mocks.getPokemonDetail();
        BDDMockito.given(pokemonService.getPokemonDetailByName(anyString())).willReturn(mockPokemonDetail);

        mockMvc.perform(get("/pokemon/name").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.basicPokemonInformation.urlProfileImage").isNotEmpty())
                .andExpect(jsonPath("$.basicPokemonInformation.weight").value(mockPokemonDetail.getBasicPokemonInformation().getWeight()))
                .andExpect(jsonPath("$.basicPokemonInformation.abilities").isArray())
                .andExpect(jsonPath("$.basicPokemonInformation.types").isArray())
                .andExpect(jsonPath("$.description").value(mockPokemonDetail.getDescription()))
                .andExpect(jsonPath("$.evolutions").isArray());


    }
}