package com.senla.rent.controller;


import com.senla.rent.configs.LaunchTestConfiguration;
import com.senla.rent.configs.spring.SpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, LaunchTestConfiguration.class})
@EnableWebMvc
@WebAppConfiguration
public class AdminRentPointControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test

    public void testGetPoint() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/admin/rentpoint")
                        .param("page", "0")
                        .param("limit", "2")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].address").value("prospekt Kosmonavtov 2"))
                .andExpect(jsonPath("$[0].town.id").value(4));
    }
}