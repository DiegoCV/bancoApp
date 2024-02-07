package co.com.banco.personas.usecase.cliente;

import co.com.banco.personas.model.cliente.Cliente;
import co.com.banco.personas.model.cliente.gateways.ClienteRepository;
import co.com.banco.personas.model.persona.Persona;
import co.com.banco.personas.usecase.persona.PersonaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class ClienteUseCaseImplTest {

    @InjectMocks
    @Spy
    private ClienteUseCaseImpl clienteUseCaseImpl;

    @Mock
    private PersonaUseCase personaUseCase;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearCliente() {
        UUID uuid = UUID.randomUUID();
        Cliente cliente = Cliente.builder().id(uuid).contrasenna("123").estado(true).build();
        Persona persona = Persona.builder().build();
        Cliente clienteResponse = Cliente.builder().id(uuid).build();

        Mockito.doReturn(persona).when(personaUseCase).crear(any());
        Mockito.doReturn(clienteResponse).when(clienteRepository).save(any());

        Cliente clienteTest = clienteUseCaseImpl.crearCliente(cliente);
        assertEquals(uuid, clienteTest.getId());

    }

    @Test
    void actualizarCliente() {
        UUID uuid = UUID.randomUUID();
        Cliente cliente = Cliente.builder().id(uuid).contrasenna("123").estado(false).build();
        Cliente clienteResponse = Cliente.builder().id(uuid).build();

        Mockito.doReturn(clienteResponse).when(clienteRepository).save(any());
        Mockito.doReturn(clienteResponse).when(clienteRepository).findById(uuid);

        Cliente clienteTest = clienteUseCaseImpl.actualizarCliente(cliente);
        assertEquals(uuid, clienteTest.getId());
    }



}