package proyect.template.unit.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import proyect.template.infrastructure.controller.WidgetController;
import proyect.template.domain.ports.WidgetPortIn;
import proyect.template.domain.exception.WidgetNotFoundProblem;
import io.micrometer.tracing.Tracer;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WidgetController.class)
class WidgetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WidgetPortIn widgetPortIn;

    @MockBean
    private Tracer tracer;

    @Test
    void healthCheck_returns200() throws Exception {
        mockMvc.perform(get("/healthz"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ok"));
    }

    @Test
    void test500_returns500() throws Exception {
        mockMvc.perform(get("/test-500"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void createWidget_returns400_whenNameIsBlank() throws Exception {
        mockMvc.perform(post("/v1/widgets")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"fullname\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getWidgetById_returns404_whenNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        when(widgetPortIn.getById(id)).thenThrow(new WidgetNotFoundProblem(id));

        mockMvc.perform(get("/v1/widgets/" + id))
                .andExpect(status().isNotFound());
    }
}


