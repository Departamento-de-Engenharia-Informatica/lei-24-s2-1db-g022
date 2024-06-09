package pt.ipp.isep.dei.esoft.project.domain.DTO;

import pt.ipp.isep.dei.esoft.project.domain.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamMapper {

    public TeamMapper() {
    }

    public List<TeamDto> toDTO(List<Team> list) {

        List<TeamDto> teamDtoList = new ArrayList<>();

        for (Team t : list) {
            teamDtoList.add(toDto(t));
        }

        return teamDtoList;
    }

    private TeamDto toDto(Team obj) {

        return new TeamDto(obj.getId(), obj.getCollaboratorList());
    }

}
