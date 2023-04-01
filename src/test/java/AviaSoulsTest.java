import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class AviaSoulsTest {

    Ticket ticket1 = new Ticket("Москва", "Париж", 25_000, 19, 23);
    Ticket ticket2 = new Ticket("Санкт-Петербург", "Москва", 40_994, 23, 1);
    Ticket ticket3 = new Ticket("Москва", "Париж", 6_635, 13, 15);
    Ticket ticket4 = new Ticket("Дубай", "Москва", 25_000, 3, 7);
    Ticket ticket5 = new Ticket("Санкт-Петербург", "Москва", 13_660, 11, 13);
    Ticket ticket6 = new Ticket("Москва", "Париж", 18_876, 6, 12);
    Ticket ticket7 = new Ticket("Москва", "Париж", 46_083, 22, 4);
    AviaSouls manager = new AviaSouls();
    @BeforeEach
    public void setup() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
    }

    @Test
    public void compareIfMore() {
        System.out.println(ticket1.compareTo(ticket5));

        int expected = 1;
        int actual = ticket1.compareTo(ticket5);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void compareIfLess() {
        System.out.println(ticket1.compareTo(ticket2));

        int expected = -1;
        int actual = ticket1.compareTo(ticket2);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void compareIfEqual() {
        System.out.println(ticket1.compareTo(ticket4));

        int expected = 0;
        int actual = ticket1.compareTo(ticket4);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void sortByPrice() {
        Ticket[] expected = {ticket3, ticket6, ticket1, ticket7};
        Ticket[] actual = manager.search("Москва", "Париж");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findOnlyOne() {
        Ticket[] expected = {ticket4};
        Ticket[] actual = manager.search("Дубай", "Москва");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void exceptionIfNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            manager.search("Каир", "Москва");
        });
    }

    @Test
    public void sortByFlightTime() {
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket3, ticket1, ticket6, ticket7};
        Ticket[] actual = manager.searchAndSortBy("Москва", "Париж", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}
