package co.com.banco.personas.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PersonaResponse {
    private String nombre;
    private String identificacion;
    private String telefono;
}
