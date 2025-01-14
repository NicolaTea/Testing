package Repo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class InMemoryRepo<T> {
    private final List<T> items = new ArrayList<>();

    /**
     * Add an element
     * @param item the item to be added
     * @param nameExtractor function to extract the name or unique attribute of the item
     * @return true if added successfully, false if duplicate
     */
    public boolean add(T item, Function<T, String> nameExtractor) {
        for (T existingItem : items) {
            if (nameExtractor.apply(existingItem).equals(nameExtractor.apply(item))) {
                System.out.println("Error: An item with the same name already exists.");
                return false;
            }
        }
        items.add(item);
        System.out.println("Item added successfully.");
        return true;
    }

    /**
     * Delete an element
     * @param item the item to be deleted
     */
    public void delete(T item) {
        if (items.remove(item)) {
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Error: Item not found.");
        }
    }

    /**
     * Update an element based on a condition
     * @param item the new item to update
     * @param condition the condition to find the item to update
     */
    public void update(T item, Predicate<T> condition) {
        for (int i = 0; i < items.size(); i++) {
            if (condition.test(items.get(i))) {
                items.set(i, item);
                System.out.println("Item updated successfully.");
                return;
            }
        }
        System.out.println("Error: Item not found for update.");
    }

    /**
     * Get all elements
     * @return a list of all elements
     */
    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    /**
     * Find elements based on a condition
     * @param condition the condition to match the items
     * @return a list of items that match the condition
     */
    public List<T> find(Predicate<T> condition) {
        List<T> results = new ArrayList<>();
        for (T item : items) {
            if (condition.test(item)) {
                results.add(item);
            }
        }
        return results;
    }
}
