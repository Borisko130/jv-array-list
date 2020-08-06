package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;
    private T[] values;

    ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        values[currentSize++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > currentSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("List has no such index");
        }
        ensureCapacity();
        // TODO This
        System.arraycopy(values, index, values, index + 1, values.length - index - 1);
        values[index] = value;
        currentSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= currentSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("List has no such index");
        }
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= currentSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("List has no such index");
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > currentSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("List has no such index");
        }
        T valueToReturn = values[index];
        // TODO
        System.arraycopy(values, index + 1, values, index, currentSize--);
        return valueToReturn;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < currentSize; i++) {
            if (t == values[i] || (t != null && t.equals(values[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in list");
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    private void ensureCapacity() {
        if (currentSize >= values.length) {
            int oldCapacity = values.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            values = Arrays.copyOf(values, newCapacity);
        }
    }
}
