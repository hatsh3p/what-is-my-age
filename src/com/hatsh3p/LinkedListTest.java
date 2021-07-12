package com.hatsh3p;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LinkedListTest {
    private LinkedList<Integer> linkedList;

    @BeforeEach
    void setUp() {
        linkedList = new LinkedList<>();
    }

    private List<Integer> toList() {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    @Test
    void add_firstElement() {
        linkedList.add(1);
        assertEquals(List.of(1), toList());
    }

    @Test
    void add_threeElements() {
        linkedList.add(2);
        linkedList.add(4);
        linkedList.add(6);
        assertEquals(List.of(2,4,6), toList());
    }

    @Test
    void add_atPositionZero() {
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(0, 1);
        assertEquals(List.of(1,2,3), toList());
    }

    @Test
    void add_atLessThanZero() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.add(-1,1));
    }

    @Test
    void add_atGreaterThanSize() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.add(5001,1));
    }

    @Test
    void get_validPosition() {
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(9);
        assertEquals( 7, linkedList.get(0));
    }

    @Test
    void get_invalidPosition() {
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(9);
        assertThrows(NullPointerException.class, () -> linkedList.get(3));
    }

    @Test
    void remove_validPosition() {
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(9);
        assertEquals( 7, linkedList.remove(0));
    }

    @Test
    void remove_invalidPosition() {
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(9);
        assertThrows(NullPointerException.class, () -> linkedList.remove(3));
    }

    @Test
    void size_afterRemove() {
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(9);
        linkedList.remove(0);
        assertEquals( 2, linkedList.size());
    }

    @Test
    void toString_threeElement() {
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(9);
        assertEquals("7\n8\n9\n", linkedList.toString());
    }

    @Test
    void toString_empty() {
        assertEquals("NULL", linkedList.toString());
    }
}
