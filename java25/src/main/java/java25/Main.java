package java25;

import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class Main {
    private <E> Gatherer<E, ?, E> distinct() {
        Supplier<HashSet<E>> initializer = HashSet::new;
        Gatherer.Integrator<HashSet<E>, E, E> integrator =
                (set, element, downstream) -> {
                    if (set.add(element)) downstream.push(element);
                    return true;
                };
        return Gatherer.ofSequential(initializer, integrator);
    }

    public static void main(String[] args) {
        // built-in gatherers:
        List<Integer> resultList0 = Stream.of(1, 2, 3, 4, 5).gather(Gatherers.fold(() -> 0, Integer::sum)).toList();
        List<String> resultList1 = Stream.of("a", "b", "c", "d").gather(Gatherers.mapConcurrent(2, String::toUpperCase)).toList();
        List<Integer> resultList2 = Stream.of(1, 2, 3, 4).gather(Gatherers.scan(() -> 0, Integer::sum)).toList();
        List<List<Integer>> resultList3 = Stream.of(1, 2, 3, 4, 5).gather(Gatherers.windowSliding(3)).toList();

        /*
        - Initializer (Optional) - Initializes an internal state, accumulator.
        - Integrator (Mandatory) - Defines the processing logic for each stream element.
        - Finisher (Optional) - Applies a final transformation before output (e.g., sorting, conversion).
        - Combiner (Optional) - Optimizes Gatherers for parallel execution.
        - AndThen (Optional) - Chains multiple Gatherers to create more sophisticated processing.
        */
        List<Object> resultList4 = Stream.of("apple", "banana", "cat").gather(Gatherer.of((state, element, downstream) -> {
                    Gatherer.Downstream<Object> x = downstream;
                    downstream.push(element.length());
                    return true;
                }))
                .toList();

        IO.println("result = " + Stream.of(1, 2, 3, 4, 5, 4, 3, 2, 1).gather(new Main().distinct()).toList());
    }
}
