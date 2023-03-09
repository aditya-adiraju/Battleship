package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    void testConstructorAndSetters() {
        Score s1 = new Score("Alice",2);
        Score s2 = new Score("Bob", 21);

        assertEquals("Alice", s1.getName());
        assertEquals(2, s1.getPoints());

        assertEquals("Bob", s2.getName());
        assertEquals(21, s2.getPoints());

        s1.setName("Charlie");
        s1.setPoints(4);
        assertEquals("Charlie", s1.getName());
        assertEquals(4, s1.getPoints());
    }

    @Test
    void testEquals() {
        Score s1 = new Score("Alice",2);
        Score s2 = new Score("Alice",2);
        Score s3 = new Score("Bob", 21);
        Score s4 = new Score("Charles", 21);
        Score s5 = new Score("Charles", 1);

        assertTrue(s1.equals(s1));
        assertTrue(s1.equals(s2));
        assertFalse(s1.equals(null));
        assertFalse(s1.equals(s3));
        assertFalse(s3.equals(s4));
        assertFalse(s5.equals(s4));
    }

    @Test
    void testHashCode() {
        Score s1 = new Score("Alice",2);
        Score s2 = new Score("Bob", 21);


        assertEquals(1963862371, s1.hashCode());
        assertEquals(2076897, s2.hashCode());
    }
}