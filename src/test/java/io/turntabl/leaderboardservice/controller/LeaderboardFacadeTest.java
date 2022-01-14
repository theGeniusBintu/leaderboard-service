package io.turntabl.leaderboardservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.leaderboardservice.controller.response.ProfileDto;
import io.turntabl.leaderboardservice.converter.ProfileToProfileDtoConverter;
import io.turntabl.leaderboardservice.service.LeaderboardRepositoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LeaderboardFacadeTest {

    @Autowired
    private Mock mockMVC;

    @Autowired
    private ObjectMapper objectMapper;

@MockBean
    private LeaderboardRepositoryService leaderboardRepositoryService;

@MockBean
    private ProfileToProfileDtoConverter profileToProfileDtoConverter;

private LeaderboardFacade underTest;


@Test
    void getLeaderboard(){

    ProfileDto profileDto = ProfileDto.builder()
            .username("lameiraatt")
            .name("Ana Lameira")
            .build();
    List<ProfileDto> expectedResult = List.of(profileDto);

    when(leaderboardRepositoryService.getProfiles().stream().map(profileToProfileDtoConverter::convert).collect(toList())).thenReturn(expectedResult);


    assertTrue(expectedResult.contains(profileDto));
}
}
