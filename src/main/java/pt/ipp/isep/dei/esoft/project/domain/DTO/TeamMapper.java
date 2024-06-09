package pt.ipp.isep.dei.esoft.project.domain.DTO;

import pt.ipp.isep.dei.esoft.project.domain.model.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Maps Team objects to TeamDto objects.
 *
 * @author Group22
 *
 */
public class TeamMapper {

    /**
     * Constructs a TeamMapper object.
     */
    public TeamMapper() {
    }

    /**
     * Converts a list of Team objects to a list of TeamDto objects.
     *
     * @param list The list of Team objects to be converted.
     * @return A list of TeamDto objects.
     */
    public List<TeamDto> toDTO(List<Team> list) {
        List<TeamDto> teamDtoList = new ArrayList<>();

        for (Team t : list) {
            teamDtoList.add(toDto(t));
        }

        return teamDtoList;
    }

    /**
     * Converts a Team object to a TeamDto object.
     *
     * @param obj The Team object to be converted.
     * @return A TeamDto object.
     */
    private TeamDto toDto(Team obj) {
        return new TeamDto(obj.getId(), obj.getCollaboratorList());
    }
}
