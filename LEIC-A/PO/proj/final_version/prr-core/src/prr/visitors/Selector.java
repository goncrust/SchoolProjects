package prr.visitors;

/**
 * Predicate interface for entity selection.
 * 
 * @param <Entity> the entity to be tested.
 */
public interface Selector<Entity> {

    /**
     * @param entity the entity
     * @return true if selected, false otherwise.
     */
    default boolean ok(Entity entity) {
        return true;
    }

}
