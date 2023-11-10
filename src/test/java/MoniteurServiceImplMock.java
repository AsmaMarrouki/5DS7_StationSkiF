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
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class MoniteurServiceImplMock {

    private static Moniteur moniteur1;
    private static Moniteur moniteur2;

    @InjectMocks
    private MoniteurServiceImpl moniteurService;

    @Mock
    private MoniteurRepository moniteurRepository;



    @BeforeAll
    static void setUp() {
        Set<Cours> coursSet = new HashSet<>();
        coursSet.add(new Cours(1, 123L, TypeCours.COLLECTIF_ADULTE, Support.SKI, 100.0f, 1, 2, Collections.emptySet()));

        moniteur1 = Moniteur.builder().idMoniteur(1).numMoniteur(123L).nomM("nasri").prenomM("youssef").dateRecru(LocalDate.now()).prime(1000).coursSet(coursSet).build();
        moniteur2 = Moniteur.builder().idMoniteur(2).numMoniteur(456L).nomM("nasri").prenomM("yahya").dateRecru(LocalDate.now()).prime(1500).coursSet(coursSet).build();
    }

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllMoniteurs() {
        Set<Moniteur> moniteurs = new HashSet<>();
        moniteurs.add(moniteur1);
        moniteurs.add(moniteur2);

        when(moniteurRepository.findAll()).thenReturn(new ArrayList<>(moniteurs));

        Set<Moniteur> result = new HashSet<>(moniteurService.retrieveAllMoniteurs());

        verify(moniteurRepository, times(1)).findAll();
        assertEquals(moniteurs, result);
    }

    @Test
    void testAddMoniteur() {

        when(moniteurRepository.save(moniteur1)).thenReturn(moniteur1);

        Moniteur result = moniteurService.addMoniteur(moniteur1);

        verify(moniteurRepository, times(1)).save(moniteur1);
        assertEquals(moniteur1, result);
    }

    @Test
    void testDeleteMoniteur() {
        Integer moniteurId = 1;

        moniteurService.deleteMoniteur(moniteurId);

        verify(moniteurRepository, times(1)).deleteById(moniteurId);
    }

    @Test
    void testUpdateMoniteur() {
        when(moniteurRepository.save(moniteur1)).thenReturn(moniteur1);

        Moniteur result = moniteurService.updateMoniteur(moniteur1);

        verify(moniteurRepository, times(1)).save(moniteur1);
        assertEquals(moniteur1, result);
    }

    @Test
    void testRetrieveMoniteur() {
        Integer moniteurId = 1;
        Optional<Moniteur> optionalMoniteur = Optional.of(moniteur1);

        when(moniteurRepository.findById(moniteurId)).thenReturn(optionalMoniteur);

        Moniteur result = moniteurService.retrieveMoniteur(moniteurId);

        verify(moniteurRepository, times(1)).findById(moniteurId);
        assertEquals(moniteur1, result);
    }
}
