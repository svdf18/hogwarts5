package dk.kea.dat3js.hogwarts5.ghosts;

import dk.kea.dat3js.hogwarts5.house.House;
import dk.kea.dat3js.hogwarts5.house.HouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GhostController.class)
class GhostControllerTest {

    @MockBean
    private HouseService houseService; // Change to HouseService

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllGhosts() throws Exception {
        when(houseService.findById("Gryffindor")).thenReturn(Optional.of(new House(1, "Gryffindor", "Godric Gryffindor", new String[]{"Red", "Gold"})));
        when(houseService.findById("Slytherin")).thenReturn(Optional.of(new House(2, "Slytherin", "Salazar Slytherin", new String[]{"Green", "Silver"})));
        when(houseService.findById("Ravenclaw")).thenReturn(Optional.of(new House(3, "Ravenclaw", "Rowena Ravenclaw", new String[]{"Blue", "Bronze"})));
        when(houseService.findById("Hufflepuff")).thenReturn(Optional.of(new House(4, "Hufflepuff", "Helga Hufflepuff", new String[]{"Yellow", "Black"})));

        mockMvc.perform(get("/ghosts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].name").value("Nearly Headless Nick"))
                .andExpect(jsonPath("$[1].name").value("The Bloody Baron"))
                .andExpect(jsonPath("$[2].name").value("The Grey Lady"))
                .andExpect(jsonPath("$[3].name").value("The Fat Friar"));
    }

    @Test
    void getGhostByName() throws Exception {
        when(houseService.findById("Gryffindor")).thenReturn(Optional.of(new House(1, "Gryffindor", "Godric Gryffindor", new String[]{"Red", "Gold"})));

        mockMvc.perform(get("/ghosts/Nearly Headless Nick"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Nearly Headless Nick"))
                .andExpect(jsonPath("$.realName").value("Sir Nicholas de Mimsy-Porpington"))
                .andExpect(jsonPath("$.house").value("Gryffindor"));
    }
}
