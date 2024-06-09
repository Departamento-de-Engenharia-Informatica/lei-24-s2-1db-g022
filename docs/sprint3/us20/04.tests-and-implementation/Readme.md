# US20 - Register a Green Space

## 4. Tests

**Test 1:** Hectares must be an integer greater than zero - AC2.

    @Test
    @DisplayName("Is Area Large")
    void ensureIsAreaLargeSuccessfully() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertTrue(garden.isAreaLarger(1));
    }

**Tests:** When creating a Green Space with an existing name or address, the system must reject such operation and the user must be able to modify the typed name or address - AC3.

    @Test
    @DisplayName("Has Same Name")
    void ensureHasSameNameSuccessfully() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertTrue(garden.hasName("Jardim Porto"));

    }

    @Test
    @DisplayName("Has Differents Names")
    void ensureHasSameNameInsuccessfully() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertFalse(garden.hasName("Jardim Lisboa"));

    }

    @Test
    @DisplayName("Equals")
    void testEqualsSameObject() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertEquals(garden, garden);
    }

    @Test
    @DisplayName("Equals Different")
    void testEqualsDifferentClass() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertNotEquals(garden, new Object());
    }

    @Test
    @DisplayName("Equals Null")
    void testEqualsNull() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertNotEquals(garden, null);
    }

    @Test
    @DisplayName("Differents Object")
    void testEqualsDifferentObject() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        GreenSpace garden1 = new Garden("Jardim Lisboa",3,"street Porto",12,"1234-123","Porto");

        assertNotEquals(garden, garden1);
    }

    @Test
    @DisplayName("Same Object")
    void testEqualsSameObjectSame() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        GreenSpace garden1 = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");

        assertEquals(garden, garden1);
    }


## 5. Construction (Implementation)

### Class RegisterGreenSpaceController

```java
    /**
 * Registers a new GreenSpace with the given details and associates it with the GreenSpaceManager
 * from the session if the collaborator exists.
 *
 * @param greenSpaceType the type of the green space
 * @param greenSpaceName the name of the green space
 * @param area           the area of the green space
 * @param streetName     the street name where the green space is located
 * @param doorNumber     the door number where the green space is located
 * @param postCodeNumber the post code number of the green space's location
 * @param localization   the localization of the green space
 * @return an Optional containing the registered GreenSpace if successful, or an empty Optional otherwise
 */
public Optional<GreenSpace> registerGreenSpace(String greenSpaceType, String greenSpaceName, int area, String streetName, int doorNumber, String postCodeNumber, String localization) {
    Optional<GreenSpace> greenSpaceOptional = Optional.empty();

    GreenSpaceManager greenSpaceManagerEmail = getGSMFromSession();
    Optional<ICollaborator> collaboratorOptional = getCollaboratorRepository().getCollaboratorByEmail(greenSpaceManagerEmail.getEmail());

    if (collaboratorOptional.isPresent()) {
        greenSpaceOptional = getGreenSpaceRepository().registerGreenSpace(greenSpaceType, greenSpaceName, area, streetName, doorNumber, postCodeNumber, localization);
    }
    boolean addListSucess = false;

    if (greenSpaceOptional.isPresent()) {
        GreenSpaceManager gsm = (GreenSpaceManager) collaboratorOptional.get();
        addListSucess = gsm.addListGreenSpace(greenSpaceOptional.get());
    }

    if (addListSucess) {
        getGreenSpaceRepository().ver();
        return greenSpaceOptional;
    }

    return Optional.empty();
}
```

### Class GreenSpaceRepository

```java
    public Optional<GreenSpace> registerGreenSpace(String greenSpaceType, String greenSpaceName, int area, String streetName, int doorNumber, String postCodeNumber, String localization) {
    Optional<GreenSpace> optionalValue = Optional.empty();
    GreenSpace newGreenSpace = null;
    switch (greenSpaceType) {
        case "Garden":
            newGreenSpace = new Garden(greenSpaceName, area, streetName, doorNumber, postCodeNumber, localization);
            break;
        case "Medium Park":
            newGreenSpace = new MediumPark(greenSpaceName, area, streetName, doorNumber, postCodeNumber, localization);
            break;
        case "Large Park":
            newGreenSpace = new LargePark(greenSpaceName, area, streetName, doorNumber, postCodeNumber, localization);
            break;
    }

    if (addGreenGreenSpace(newGreenSpace)) {
        optionalValue = Optional.of(newGreenSpace);
    }

    return optionalValue;
}
```
```java
    private boolean addGreenGreenSpace(GreenSpace greenSpace) {
    boolean success = false;
    if (validateAllData(greenSpace)) {
        success = greenSpaceList.add(greenSpace);
    }

    return success;
}
```

```java
    private boolean addGreenGreenSpace(GreenSpace greenSpace) {
    boolean success = false;
    if (validateAllData(greenSpace)) {
        success = greenSpaceList.add(greenSpace);
    }

    return success;
}
```



## 6. Integration and Demo

* A new option on the GSM menu options was added.

* For demo purposes some GreenSpaces are bootstrapped while system starts.

## 7. Observations

n/a