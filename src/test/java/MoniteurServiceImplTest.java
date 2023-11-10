import com.example.stationski.StationSkiApplication;
import com.example.stationski.entities.Cours;
import com.example.stationski.entities.Moniteur;
import com.example.stationski.entities.Support;
import com.example.stationski.entities.TypeCours;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.MoniteurRepository;
import com.example.stationski.services.MoniteurServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = StationSkiApplication.class)
@SpringJUnitConfig
public class MoniteurServiceImplTest {

    @Autowired
    private MoniteurServiceImpl moniteurService;

    @Autowired
    private MoniteurRepository moniteurRepository;

    @Autowired
    private CoursRepository coursRepository;

    @Test
    public void testAddMoniteur() {
        Set<Cours> coursSet = new HashSet<>();
        coursSet.add(new Cours(1, 123L, TypeCours.COLLECTIF_ADULTE, Support.SKI, 100.0f, 1, 2, Collections.emptySet()));
        Moniteur moniteur = new Moniteur(1, 123L, "John", "Doe", LocalDate.now(), 1000, coursSet);

        // Perform the test
        Moniteur result = moniteurService.addMoniteur(moniteur);


        // Check if the Moniteur has a non-null ID before accessing it
        if (result != null) {
            assertNotNull(result.getIdMoniteur(), "ID should not be null after insertion");
            assertEquals(moniteur.getNomM(), result.getNomM(), "Names should match");
            assertEquals(moniteur.getPrenomM(), result.getPrenomM(), "Surnames should match");
        }

        // Cleanup: Delete the inserted Moniteur from the database
        if (result != null) {
            moniteurService.deleteMoniteur(result.getIdMoniteur());
        }
    }

}
