package co.com.banco.personas.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class ClienteResponse {
    private UUID id;
    private boolean estado;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PersonaResponse personaResponse;
}
