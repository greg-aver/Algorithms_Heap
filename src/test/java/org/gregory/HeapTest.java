package org.gregory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {
    Heap heap;

    @BeforeEach
    void setUp() {
        heap = new Heap();
    }

    @AfterEach
    void tearDown() {
        heap = null;
    }

    @Test
    void makeHeap() {
        heap.MakeHeap(new int[]{1, 4, 2, 9, 11, 3, 5, 7, 8, 6}, 3);
        try {
            Field field = heap.getClass().getDeclaredField("HeapArray");
            field.setAccessible(true);
            assertThat((int[]) field.get(heap), is(new int[]{11, 9, 5, 8, 6, 3, 2, 7, 1, 4, 0, 0, 0, 0, 0}));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getMax() {
        heap.MakeHeap(new int[]{1, 4, 2, 8, 9, 11, 3, 5, 7, 8, 6}, 3);
        assertThat(heap.GetMax(), is(11));
        try {
            Field field = heap.getClass().getDeclaredField("heapSize");
            field.setAccessible(true);
            assertThat(field.get(heap), is(10));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void add() {
        heap.MakeHeap(new int[]{1, 4, 2, 8, 9, 11, 3, 5, 7, 6}, 3);
        assertThat(heap.Add(15), is(true));
        assertThat(heap.Add(40), is(true));
        assertThat(heap.Add(10), is(true));
        assertThat(heap.Add(12), is(true));
        assertThat(heap.Add(17), is(true));
        assertThat(heap.Add(27), is(false));
        try {
            Field field = heap.getClass().getDeclaredField("heapSize");
            field.setAccessible(true);
            assertThat((int) field.get(heap), is(15));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}