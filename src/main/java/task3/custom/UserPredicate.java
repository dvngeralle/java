package task3.custom;

@FunctionalInterface
public interface UserPredicate {
    boolean test(User user);
}
