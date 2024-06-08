package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.model.Brand;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The BrandRepository class manages the storage and retrieval of brand objects.
 * It provides methods for adding and retrieving brands from the repository.
 *
 * @author Group22
 */
public class BrandRepository {

    private final List<Brand> brandList;

    /**
     * Constructs a BrandRepository object.
     */
    public BrandRepository() {
        brandList = new ArrayList<>();
    }

    /**
     * Adds a brand to the repository.
     *
     * @param brand The brand to add.
     * @return True if the brand is successfully added, false otherwise.
     */
    public boolean addBrand(Brand brand) {
        boolean success = false;
        if (validateBrand(brand)) {
            // A clone of the brand is added to the list of brands, to avoid side effects and outside manipulation.
            success = brandList.add(brand.clone());
        }
        return success;
    }

    /**
     * Validates if the brand is not already present in the repository.
     *
     * @param brand The brand to validate.
     * @return True if the brand is valid (not already present), false otherwise.
     */
    private boolean validateBrand(Brand brand) {
        return !brandList.contains(brand);
    }

    /**
     * Checks if a brand with the given name exists in the brand list.
     *
     * @param brandName The name of the brand to check for existence.
     * @return True if a brand with the given name exists, false otherwise.
     */
    public boolean hasBrandByName(String brandName) {
        
        Brand brand = new Brand (brandName);

        return brandList.contains(brand);
    }

    /**
     * Retrieves a brand by the name of one of its models, if available.
     *
     * @param modelName The name of the model associated with the brand.
     * @return An Optional containing the Brand object if found, otherwise empty.
     */
    public Optional<Brand> getBrandByModelName(String modelName){

        Optional<Brand> brand = Optional.empty();

        for (Brand b : brandList) {
            if (b.hasModelByName(modelName)) {
                brand = Optional.of(b);
            }
        }

        return brand;
    }

}