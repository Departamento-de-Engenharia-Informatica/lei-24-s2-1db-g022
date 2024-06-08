package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public interface ICollaborator {
    String getName();
    List<Skill> getSkillList();

    boolean hasEqualsEmail(ICollaborator collaborator);

    boolean hasEqualsPhoneNumber(ICollaborator collaborator);

    boolean hasEqualsTaxNumber(ICollaborator collaborator);

    Document getDocument();

    ICollaborator clone();
}
