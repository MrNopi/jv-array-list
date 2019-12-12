package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private Object[] elementData;
    private int size = 0;
    private static final int DEFAULT_LIST_CAPACITY = 10;

    public ArrayList() {
        elementData = new Object[]{};
    }

    public ArrayList(int length) {
        if (length > 0) {
            elementData = new Object[length];
        } else if (length == 0) {
            elementData = new Object[]{};
        } else {
            throw new ArrayIndexOutOfBoundsException("Your index is lesser that 0");
        }
    }

    @Override
    public void add(T value) {
        if (elementData.length == 0 || size == elementData.length) {
            grow();
            add(value);
        } else {
            elementData[size++] = value;
        }
    }

    @Override
    public void add(T value, int index) {
        if (elementData.length == size) {
            grow();
        }
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Your index is lesser that 0");
        }
        add((T) elementData[size]);
        for (int i = size - 1; i > index - 1; i--) {
            if (i == index) {
                set(value, i);
            } else {
                set((T) elementData[i - 1], i);
            }
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > elementData.length) {
            grow();
            addAll(list);
        } else {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException("Your index is lesser that 0");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException("Your index is lesser that 0");
        }
        if (index > elementData.length) {
            grow();
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        T value = null;
        if (elementData.length == size) {
            grow();
        }
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException("Your index is lesser that 0");
        }
        for (int i = index; i < size; i++) {
            if (i == index) {
                value = (T) elementData[i];
            }
            set((T) elementData[i + 1], i);
        }
        size--;
        return value;
    }

    @Override
    public T remove(T t) {
        int i = 0;
        for (; i < size; i++) {
            if (elementData[i] == t || elementData[i].equals(t)) {
                break;
            }
            if (i == size - 1) {
                throw new NoSuchElementException("Check your index");
            }
        }
        return remove(i);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0) {
            int newCapacity = (elementData.length * 3) / 2 + 1;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
        elementData = Arrays.copyOf(elementData, Math.max(DEFAULT_LIST_CAPACITY, size + 1));
    }
}
