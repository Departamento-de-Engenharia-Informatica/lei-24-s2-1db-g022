package pt.ipp.isep.dei.esoft.project.domain.model;

public interface GreenSpace {
    String getName();

    int getArea();

    Address getAddress();

    boolean hasName(String greenSpaceName);
}
