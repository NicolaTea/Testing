package Repo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class InMemoryRepo<T> {
    private final List<T> items = new ArrayList<>();

    /**
     * add an element
     * @param item
     */
    public void add(T item) {
        items.add(item);
    }


    /**
     * delete an element
     * @param item
     */
    public void delete(T item) {
        items.remove(item);
    }

    /**
     * update an element based on a condition
     * @param item
     * @param condition
     */
    public void update(T item, Predicate<T> condition) {
        for (int i = 0; i < items.size(); i++) {
            if (condition.test(items.get(i))) {
                items.set(i, item);
                return;
            }
        }
    }

    /**
     * get all the elements
     * @return
     */
    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    /**
     * find the elements based on a condition
     * @param condition
     * @return
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
