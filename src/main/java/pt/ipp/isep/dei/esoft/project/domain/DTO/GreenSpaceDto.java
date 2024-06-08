package pt.ipp.isep.dei.esoft.project.domain.DTO;

public class GreenSpaceDto {

    private String greenSpaceName;
    private int area;

    public GreenSpaceDto(String greenSpaceName, int area) {

        this.greenSpaceName = greenSpaceName;
        this.area = area;
    }

    public String getGreenSpaceName() {

        return greenSpaceName;
    }

    public int getArea() {
        return area;
    }
}
