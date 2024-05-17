# US20 - Register a Green Space

## 4. Tests

**Test 1:** Check that it is not possible to create an instance of the Skill class with null values - AC1.

	@Test(expected = IllegalArgumentException.class)
		public void ensureSkillIsNotNull() {
		Skill instance = new Skill(null);
	}


**Test 2:** Check that it is not possible to create an instance of the Skill class with special caracters or numbers - AC2.

	@Test(expected = IllegalArgumentException.class)
		public void testSkillHasNumbers() {
		
		Skill instance = new Skill("Podador1");
	}

## 5. Construction (Implementation)

### Class RegisterSkillController

```java
    public Optional<Skill> registerSkill(String name){
        Optional<Skill> newSkill = Optional.empty();
        newSkill = getSkillRepository().createSkill(name);
        return newSkill;
        }
```

### Class SkillRepository

```java
    public Optional<Skill> createSkill(String name) {

        // When a Skill is added, it should fail if the Skill already exists in the list of Skills.
        // In order to not return null if the operation fails, we use the Optional class.
        Optional<Skill> optionalSkill = Optional.empty();

        Skill skill = new Skill(name);

        if (addSkill(skill)) {

        optionalSkill = Optional.of(skill);
        }
        return optionalSkill;
        }
```


## 6. Integration and Demo

* A new option on the HRM menu options was added.

* For demo purposes some skills are bootstrapped while system starts.

## 7. Observations

n/a