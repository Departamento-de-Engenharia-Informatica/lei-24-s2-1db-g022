package pt.ipp.isep.dei.esoft.project.domain.DTO;

import pt.ipp.isep.dei.esoft.project.domain.model.GreenSpace;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class responsible for converting GreenSpace entities to GreenSpaceDto objects.
 *
 * @author Group22
 */
public class GreenSpaceMapper {

    /**
     * Default constructor for GreenSpaceMapper.
     */
    public GreenSpaceMapper() {
    }

    /**
     * Converts a list of GreenSpace entities to a list of GreenSpaceDto objects.
     *
     * @param list The list of GreenSpace entities to be converted.
     * @return A list of GreenSpaceDto objects.
     */
    public List<GreenSpaceDto> toDTO(List<GreenSpace> list) {

        List<GreenSpaceDto> greenSpaceListDto = new ArrayList<>();

        for (GreenSpace g : list) {

            greenSpaceListDto.add(toDto(g));
        }

        return greenSpaceListDto;
    }

    /**
     * Converts a single GreenSpace entity to a GreenSpaceDto object.
     *
     * @param obj The GreenSpace entity to be converted.
     * @return A GreenSpaceDto object.
     */
    public GreenSpaceDto toDto(GreenSpace obj) {

        return new GreenSpaceDto(obj.getName(), obj.getArea());
    }
}
