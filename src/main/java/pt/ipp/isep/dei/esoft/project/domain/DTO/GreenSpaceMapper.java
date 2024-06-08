package pt.ipp.isep.dei.esoft.project.domain.DTO;

import pt.ipp.isep.dei.esoft.project.domain.model.GreenSpace;

import java.util.ArrayList;
import java.util.List;

public class GreenSpaceMapper {

    public GreenSpaceMapper() {
    }

    public List<GreenSpaceDto> toDTO(List<GreenSpace> list) {

        List<GreenSpaceDto> greenSpaceListDto = new ArrayList<>();

        for (GreenSpace g : list) {

            greenSpaceListDto.add(toDto(g));
        }

        return greenSpaceListDto;
    }

    public GreenSpaceDto toDto(GreenSpace obj) {

        return new GreenSpaceDto(obj.getName());
    }
}
