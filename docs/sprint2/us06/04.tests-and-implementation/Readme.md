# US06 - Register a vehicle.

## 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the Vehicle class with null values. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Vehicle instance = new Vehicle(null, 0, 0, 0, null, null, 0, null, null, null);
	}
	

**Test 2:** Check that it is not possible to create an instance of the Vehicle class with a duplicate License Plate - AC2. 

	@Test(expected = true)
		public void testEqualsDifferentLicensePlate() {
        Vehicle vehicle = new Vehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"00-00-AA",new Brand("BMW"), new Model("XM"));
        Vehicle vehicle1 = new Vehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"00-00-AX",new Brand("BMW"), new Model("XM"));

        assertNotEquals(vehicle, vehicle1);
	}

**Test 3:** Check that it is not possible to create an instance of the Vehicle class with a wrong License Plate format- AC3.

	@Test(expected = IllegalArgumentException.class)
		public void testVehicleLicensePlate() {
		
		Vehicle instance = new Vehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"AA-00-AA",new Brand("BMW"), new Model("XM");
	}

## 5. Construction (Implementation)

### Class RegisterVehicleController 

```java
    private Optional<Brand> getBrand(String brandName, String modelName) {
        Optional<Brand> brand = Optional.empty();

        if (getBrandRepository().hasBrandByName(brandName)) {
        brand = getBrandRepository().getBrandByModelName(modelName);

        }
        return brand;
    }

    public Optional<Vehicle> registerVehicle(String type, float tare, float grossWeight, int currentKm, Date registerDate, Date acquisitionDate, int checkUpFrequency, String licensePlate, String brandName, String modelName) {
        Optional<Vehicle> newVehicle = Optional.empty();
        Optional<Brand> brand = Optional.empty();

        brand = getBrand(brandName, modelName);

        if (brand.isPresent()) {

        Optional<Model> model = Optional.empty();

        newVehicle = getVehicleRepository().createVehicle(type, tare, grossWeight, currentKm, registerDate, acquisitionDate, checkUpFrequency, licensePlate, new Brand(brandName), new Model(modelName));
        }
        return newVehicle;
    }
```

### Class VehicleRepository

```java
    public Optional<Vehicle> createVehicle(String type, float tare, float grossWeight, int currentKm, Date registerDate, Date acquisitionDate, int checkUpFrequency, String licensePlate, Brand brand, Model model) {
        // When a Vehicle is added, it should fail if the Vehicle already exists in the list of Vehicles.
        // In order to not return null if the operation fails, we use the Optional class.
        Optional<Vehicle> optionalVehicle = Optional.empty();

        Vehicle vehicle = new Vehicle(type, tare, grossWeight, currentKm, registerDate, acquisitionDate, checkUpFrequency, licensePlate, brand, model);

        if (addVehicle(vehicle)) {

        optionalVehicle = Optional.of(vehicle);
        }
        return optionalVehicle;
    }
```


## 6. Integration and Demo 

* A new option on the VFM menu options was added.

* For demo purposes some brands and models are bootstrapped while system starts.

## 7. Observations

n/a