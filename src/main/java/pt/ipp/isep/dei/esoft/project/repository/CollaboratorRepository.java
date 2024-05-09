package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Document;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CollaboratorRepository {
    List<Collaborator> collaboratorList;

    public CollaboratorRepository(){collaboratorList = new ArrayList<>();}


    public Optional<Collaborator> registerCollaborator(String name, Date dateOfBirth, Date admissionDate, Address address, String phoneNumber, String email, String docType, int number, Job job) {
        Optional<Document> optionalDocument =Optional.empty();
        Optional<Collaborator> optionalCollaborator = Optional.empty();

        optionalDocument=registerDocument(docType,number);

        if(optionalDocument.isPresent()){
            Collaborator collaborator = new Collaborator(name,dateOfBirth,admissionDate,phoneNumber,email,address,optionalDocument.get(),job);
            if(addCollaborator(collaborator)){
                optionalCollaborator = Optional.of(collaborator);
            }
        }

        return optionalCollaborator;
    }

    private boolean addCollaborator(Collaborator collaborator) {
        boolean success= false;
        if(validateCollaborator(collaborator)){
            success = collaboratorList.add(collaborator.clone());
        }
        return success;
    }

    private boolean validateCollaborator(Collaborator collaborator) {
        return !collaboratorList.contains(collaborator);
    }

    private Optional<Document> registerDocument(String docType,int number){
        Optional<Document> newDocument = Optional.empty();
        newDocument = Optional.of(new Document(docType,number));
        return newDocument;
    }
}
