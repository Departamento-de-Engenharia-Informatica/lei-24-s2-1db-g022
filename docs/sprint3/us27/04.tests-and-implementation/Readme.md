# US27 - List all Green Spaces

## 4. Tests

**Test 1:** The list of green spaces must be sorted by size in descending order. At least two sorting algorithms should be available. - AC1.

	@Test(expected == result)
        GreenSpaceManager gsm = new GreenSpaceManager("gsname", Date.valueOf("1999-01-10"), Date.valueOf("2024-01-10"), "+351 914981073", "gsm@gsm.pt", new Address("streeName", "1234-123", 1), 675432501, "Passport", 123021022, new Job("Gestor"));

        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        GreenSpace garden1 = new Garden("Jardim Lisboa",5,"street Lisboa",11,"1234-143","Lisboa");
        gsm.getListGreenSpaces().addListBootstrapGreenSpaces(garden);
        gsm.getListGreenSpaces().addListBootstrapGreenSpaces(garden1);

        List<GreenSpace> result = new ArrayList<>();
        List<GreenSpace> expected = new ArrayList<>();

        expected.add(garden1);
        expected.add(garden);

        result = gsm.getListGreenSpaces().getSortedGreenspaces();

        assertEquals(expected,result);
	}

**Test 2:** Bubble Sort.

	@Test(expected == result)

        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        GreenSpace garden2 = new Garden("Jardim Lisboa",5,"street Lisboa",11,"1234-143","Lisboa");
        GreenSpace mediumPark = new MediumPark("Jardim Braga",10,"street Braga",12,"1235-135","Braga");
        GreenSpace largePark = new LargePark("Jardim Viseu",30,"street Viseu",12,"1237-145","Viseu");

        List<GreenSpace> expected = new ArrayList<>();

        expected.add(largePark);
        expected.add(mediumPark);
        expected.add(garden2);
        expected.add(garden);

        BubbleSort bubbleSort = new BubbleSort();

        List<GreenSpace> greenSpaceList = new ArrayList<>();

        greenSpaceList.add(garden);
        greenSpaceList.add(garden2);
        greenSpaceList.add(mediumPark);
        greenSpaceList.add(largePark);

        List<GreenSpace> result = bubbleSort.getSortedGreenSpaces(greenSpaceList);

        assertEquals(expected, result);
    }
	}

**Test 3:** Selection Sort.

	@Test(expected == result)

        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        GreenSpace garden2 = new Garden("Jardim Lisboa",5,"street Lisboa",11,"1234-143","Lisboa");
        GreenSpace mediumPark = new MediumPark("Jardim Braga",10,"street Braga",12,"1235-135","Braga");
        GreenSpace largePark = new LargePark("Jardim Viseu",30,"street Viseu",12,"1237-145","Viseu");

        List<GreenSpace> expected = new ArrayList<>();

        expected.add(largePark);
        expected.add(mediumPark);
        expected.add(garden2);
        expected.add(garden);

        SelectionSort selectionSort = new SelectionSort();

        List<GreenSpace> greenSpaceList = new ArrayList<>();

        greenSpaceList.add(garden);
        greenSpaceList.add(garden2);
        greenSpaceList.add(mediumPark);
        greenSpaceList.add(largePark);

        List<GreenSpace> result = selectionSort.getSortedGreenSpaces(greenSpaceList);

        assertEquals(expected, result);
	}

## 5. Construction (Implementation)

### Class ListGreenSpacesController

```java
    public List<GreenSpaceDto> getGreenSpaceManagerGSpaceSorted() {

    List<GreenSpace> greenSpaceList = new ArrayList<>();

    GreenSpaceManager greenSpaceManagerEmail = getGSMFromSession();
    Optional<ICollaborator> collaboratorOptional = getCollaboratorRepository().getCollaboratorByEmail(greenSpaceManagerEmail.getEmail());

    if (collaboratorOptional.isPresent()) {
        GreenSpaceManager gsm = (GreenSpaceManager) collaboratorOptional.get();
        greenSpaceList = gsm.getListGreenSpaces().getSortedGreenspaces();
    }

    GreenSpaceMapper gsm = new GreenSpaceMapper();

    return gsm.toDTO(greenSpaceList);
}
```

### Class CollaboratorRepository

```java
    public Optional<ICollaborator> getCollaboratorByEmail(String email) {
    for (ICollaborator collaborator : collaboratorList) {
        if (collaborator.hasEmail(email)) {
            return Optional.of(collaborator);
        }

    }
    return Optional.empty();
}
```
### Class GreenSpaceManager

```java
    public GreenSpaces getListGreenSpaces() {

    return this.greenSpaces;
}
```

### Class GreenSpaces

```java
    public List<GreenSpace> getSortedGreenspaces() {

    Properties properties = new Properties();

    try {

        InputStream in = new FileInputStream("src\\main\\resources\\config.properties");

        properties.load(in);
        in.close();

        String classPath = properties.getProperty("class.path");
        Class<?> clazz = Class.forName(classPath);
        Sortable st = (Sortable) clazz.newInstance();

        return st.getSortedGreenSpaces(this.greenSpacesList);


    } catch (Exception e) {

        throw new RuntimeException(e);
    }

}
```

### Interface Sortable

```java
List<GreenSpace> getSortedGreenSpaces(List<GreenSpace> greenSpaceList);
```

### Class BubbleSort

```java
    public List<GreenSpace> getSortedGreenSpaces(List<GreenSpace> greenSpaceList) {

    int n = greenSpaceList.size();

    for (int i = 0; i < n - 1; i++) {

        for (int j = 0; j < n - i - 1; j++) {

            if (!greenSpaceList.get(j).isAreaLarger(greenSpaceList.get(j + 1).getArea())) {

                Collections.swap(greenSpaceList, j, j + 1);
            }
        }
    }

    return greenSpaceList;
}
```

### Class SelectionSort

```java
    public List<GreenSpace> getSortedGreenSpaces(List<GreenSpace> greenSpaceList) {

    int n = greenSpaceList.size();

    for (int i = 0; i < n - 1; i++) {

        int max_idx = i;

        for (int j = i + 1; j < n; j++) {

            if (greenSpaceList.get(j).isAreaLarger(greenSpaceList.get(max_idx).getArea())) {

                max_idx = j;
            }
        }

        Collections.swap(greenSpaceList, i, max_idx);
    }

    return greenSpaceList;
}
```


## 6. Integration and Demo

* A new option on the GSM menu options was added.

## 7. Observations

n/a