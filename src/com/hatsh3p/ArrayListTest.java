package com.hatsh3p;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ArrayListTest {
    private ArrayList<Integer> arrayList;

    @BeforeEach
    void setUp() {
        arrayList = new ArrayList<>();
    }

    private List<Integer> toList() {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            list.add(arrayList.get(i));
        }
        return list;
    }

    @Test
    void add_firstElement() {
        arrayList.add(1);
        assertEquals(List.of(1), toList());
    }

    @Test
    void add_threeElements() {
        arrayList.add(2);
        arrayList.add(4);
        arrayList.add(6);
        assertEquals(List.of(2,4,6), toList());
    }

    @Test
    void add_withGrowArray() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        assertEquals(List.of(1,2,3,4,5,6), toList());
    }

    @Test
    void add_atPositionZero() {
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(0, 1);
        assertEquals(List.of(1,2,3), toList());
    }

    @Test
    void add_atLessThanZero() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(-1,1));
    }

    @Test
    void add_atGreaterThanSize() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(5001,1));
    }

    @Test
    void get_validPosition() {
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        assertEquals( 7, arrayList.get(0));
    }

    @Test
    void get_invalidPosition() {
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(3));
    }

    @Test
    void remove_validPosition() {
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        assertEquals( 7, arrayList.remove(0));
    }

    @Test
    void remove_invalidPosition() {
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(3));
    }

    @Test
    void size_afterRemove() {
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        arrayList.remove(0);
        assertEquals( 2, arrayList.size());
    }
}
