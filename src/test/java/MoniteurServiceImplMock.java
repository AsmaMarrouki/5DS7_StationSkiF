import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

import com.example.stationski.entities.Cours;
import com.example.stationski.entities.Moniteur;
import com.example.stationski.entities.Support;
import com.example.stationski.entities.TypeCours;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.MoniteurRepository;
import com.example.stationski.services.MoniteurServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class MoniteurServiceImplMock {

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
    public void testRetrieveAllMoniteurs() {
        Set<Cours> coursSet = new HashSet<>();
        coursSet.add(new Cours(1, 123L, TypeCours.COLLECTIF_ADULTE, Support.SKI, 100.0f, 1, 2, Collections.emptySet()));

        Moniteur moniteur1 = new Moniteur(1, 123L, "John", "Doe", LocalDate.now(), 1000, coursSet);
        Moniteur moniteur2 = new Moniteur(2, 456L, "Jane", "Smith", LocalDate.now(), 1500, coursSet);
        Set<Moniteur> moniteurs = new HashSet<>();
        moniteurs.add(moniteur1);
        moniteurs.add(moniteur2);

        // Define the behavior of the mock repository
        when(moniteurRepository.findAll()).thenReturn(new ArrayList<>(moniteurs));

        // Perform the test
        Set<Moniteur> result = new HashSet<>(moniteurService.retrieveAllMoniteurs());

        // Verify that the repository method was called and the result matches the expected set
        verify(moniteurRepository, times(1)).findAll();
        assertEquals(moniteurs, result);
    }

    @Test
    public void testAddMoniteur() {
        // Create a sample Moniteur
        Set<Cours> coursSet = new HashSet<>();
        coursSet.add(new Cours(1, 123L, TypeCours.COLLECTIF_ADULTE, Support.SKI, 100.0f, 1, 2, Collections.emptySet()));
        Moniteur moniteur = new Moniteur(null, 123L, "John", "Doe", LocalDate.now(), 1000, coursSet);

        // Define the behavior of the mock repository
        when(moniteurRepository.save(moniteur)).thenReturn(moniteur);

        // Perform the test
        Moniteur result = moniteurService.addMoniteur(moniteur);

        // Verify that the repository method was called and the result matches the expected Moniteur
        verify(moniteurRepository, times(1)).save(moniteur);
        assertEquals(moniteur, result);
    }

    @Test
    public void testRetrieveMoniteur() {
        // Define a Moniteur ID to be retrieved
        Integer moniteurId = 1;

        // Create a sample Moniteur
        Set<Cours> coursSet = new HashSet<>();
        coursSet.add(new Cours(1, 123L, TypeCours.COLLECTIF_ADULTE, Support.SKI, 100.0f, 1, 2, Collections.emptySet()));
        Moniteur moniteur = new Moniteur(moniteurId, 123L, "John", "Doe", LocalDate.now(), 1000, coursSet);
        Optional<Moniteur> optionalMoniteur = Optional.of(moniteur);

        // Define the behavior of the mock repository
        when(moniteurRepository.findById(moniteurId)).thenReturn(optionalMoniteur);

        // Perform the test
        Moniteur result = moniteurService.retrieveMoniteur(moniteurId);

        // Verify that the repository method was called and the result matches the expected Moniteur
        verify(moniteurRepository, times(1)).findById(moniteurId);
        assertEquals(moniteur, result);
    }

}


