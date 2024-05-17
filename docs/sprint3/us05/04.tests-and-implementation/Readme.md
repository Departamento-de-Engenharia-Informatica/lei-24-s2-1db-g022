# US05 - Generate team proposal.

## 5. Construction (Implementation)

### Class GenerateTeamController

```java
    public List<Team> generateProposalTeams(int minSizeTeam,int maxSizeTeam,List<String> skillList){
        List<Collaborator> collaboratorsList=new ArrayList<>();
        List<Team> teamListProposal=new ArrayList<>();
        List<Skill> newSkillList=getAllSkillsByName(skillList);

        collaboratorsList=getCollaboratorsBySkill(newSkillList);

        if(!collaboratorsList.isEmpty()){
        // Generate Proposal Teams
        teamListProposal=generateProposalTeamList(minSizeTeam,maxSizeTeam,newSkillList,collaboratorsList);
        }
        return teamListProposal;
        }


public boolean registerProposalTeam(Team selectTeam){
        return getTeamRepository().registerProposalTeam(selectTeam);
        }
```

### Class TeamRepository

```java
    public List<Team> generateProposalTeam(int minSize,int maxSizeTeam,List<Skill> skillList,List<Collaborator> collaboratorList){
        List<Team> teamListProposal=new ArrayList<>();
        removeCollaboratorsHasTeam(collaboratorList);

        while(collaboratorList.size()>=minSize){
        List<Collaborator> idealCollaboratorList=new ArrayList<>();
        int i=0;

        // Populate idealCollaboratorList
        while(i<collaboratorList.size()&&idealCollaboratorList.size()<maxSizeTeam){
        Collaborator collaborator=collaboratorList.get(i);
        addIdealCollaboratorList(idealCollaboratorList,collaborator);
        i++;
        }

        // Create team proposal if idealCollaboratorList meets the criteria
        if(idealCollaboratorList.size()>=minSize){
        Team teamProposal=new Team(idealCollaboratorList);
        addListTeamProposal(teamListProposal,teamProposal);

        // Remove collaborators from collaboratorList
        removeCollaboratorList(collaboratorList,idealCollaboratorList);
        }
        }

        return teamListProposal;
        }

public boolean registerProposalTeam(Team selectTeam){
        return addTeam(selectTeam);
    }

```


## 6. Integration and Demo 

* A new option on the Admin and HRM menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a