import com.example.stationski.entities.Moniteur;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.MoniteurRepository;
import com.example.stationski.services.MoniteurServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class MoniteurServiceImplTest {

    @InjectMocks
    private MoniteurServiceImpl moniteurService;

    @Mock
    private MoniteurRepository moniteurRepository;

    @Mock
    private CoursRepository coursRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testDeleteMoniteur() {
        // Define a Moniteur ID to be deleted
        Integer moniteurId = 1;

        // Define the behavior of the mock repository
        doAnswer((Answer<Void>) invocation -> {
            // Custom behavior for deleteById method
            return null;
        }).when(moniteurRepository).deleteById(moniteurId);

        // Perform the test
        moniteurService.deleteMoniteur(moniteurId);

        // Verify that the repository method was called to delete the Moniteur
        verify(moniteurRepository, times(1)).deleteById(moniteurId);
    }

    @Test
    public void testUpdateMoniteur() {
        // Create a sample Moniteur
        Moniteur moniteur = new Moniteur(/* Add appropriate parameters for Moniteur */);

        // Define the behavior of the mock repository
        when(moniteurRepository.save(moniteur)).thenReturn(moniteur);

        // Perform the test
        Moniteur result = moniteurService.updateMoniteur(moniteur);

        // Verify that the repository method was called and the result matches the expected Moniteur
        verify(moniteurRepository, times(1)).save(moniteur);
        Assertions.assertEquals(moniteur, result);
    }


}
