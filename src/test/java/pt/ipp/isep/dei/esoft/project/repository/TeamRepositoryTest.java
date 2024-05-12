package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeamRepositoryTest {
    /**
     * Tests if the createTeam method successfully creates a Team.
     */
    @Test
    void TestCreateTeam() {
        TeamRepository teamRepository = new TeamRepository();

        List<Collaborator> collaboratorList = new ArrayList<>();
        collaboratorList.add(new Collaborator("luigy", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988676", "luigy@luigy.pt", new Address("streeName", "1234-123", 1), new Document("Passport", 123321123), new Job("Jardineiro")));
        boolean result = teamRepository.registerProposalTeam(new Team(collaboratorList));

        assertTrue(result);
    }
}
