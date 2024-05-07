# US02 - Register a job.

## 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the Job class with null values - AC1. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureJobIsNotNull() {
		Job instance = new Job(null);
	}
	

**Test 2:** Check that it is not possible to create an instance of the Job class with special caracters or numbers - AC2. 

	@Test(expected = IllegalArgumentException.class)
		public void testJobHasNumbers() {
		
		Job instance = new Job("Empreteiro1");
	}

## 5. Construction (Implementation)

### Class RegisterJobController 

```java
    public Optional<Job> registerJob(String name){
        Optional<Job> newJob = Optional.empty();
        newJob = getJobRepository().createJob(name);
        return newJob;
        }
```

### Class JobRepository

```java
    public Optional<Job> createJob(String name) {

        // When a Job is added, it should fail if the Job already exists in the list of Jobs.
        // In order to not return null if the operation fails, we use the Optional class.
        Optional<Job> optionalJob = Optional.empty();

        Job job = new Job(name);

        if (addJob(job)) {

        optionalJob = Optional.of(job);
        }
        return optionalJob;
        }
```


## 6. Integration and Demo 

* A new option on the HRM menu options was added.

* For demo purposes some jobs are bootstrapped while system starts.

## 7. Observations

n/a