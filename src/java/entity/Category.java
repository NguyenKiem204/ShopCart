package entity;

/**
 *
 * @author nkiem
 */
public class Category {

    private int categoryID;
    private String categoryName;
    private Integer parentCategoryID; // Nullable

    public Category() {
    }

    public Category(String categoryName, Integer parentCategoryID) {
        this.categoryName = categoryName;
        this.parentCategoryID = parentCategoryID;
    }

    // Getters and Setters
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getParentCategoryID() {
        return parentCategoryID;
    }

    public void setParentCategoryID(Integer parentCategoryID) {
        this.parentCategoryID = parentCategoryID;
    }
}
