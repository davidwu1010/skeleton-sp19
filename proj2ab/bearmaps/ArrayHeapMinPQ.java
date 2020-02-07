package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private class PriorityNode {
        private T item;
        private double priority;

        private PriorityNode(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }
    }

    private HashMap<T, Integer> indexesMap;
    private ArrayList<PriorityNode> minHeap;

    public ArrayHeapMinPQ() {
        indexesMap = new HashMap<>();
        minHeap = new ArrayList<>();
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }

        minHeap.add(new PriorityNode(item, priority));
        int index = minHeap.size() - 1;
        indexesMap.put(item, index);
        swim(index);
    }

    @Override
    public boolean contains(T item) {
        return indexesMap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (minHeap.isEmpty()) {
            throw new NoSuchElementException();
        }
        return minHeap.get(0).item;
    }

    @Override
    public T removeSmallest() {
        if (minHeap.isEmpty()) {
            throw new NoSuchElementException();
        }

        T returnValue = minHeap.get(0).item;
        swapNodes(0, minHeap.size() - 1);
        minHeap.remove(minHeap.size() - 1);
        indexesMap.remove(returnValue);
        sink(0);

        return returnValue;
    }

    @Override
    public int size() {
        return minHeap.size();
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        int index = indexesMap.get(item);
        minHeap.get(index).priority = priority;
        if (index > 0 && priority < minHeap.get((index - 1) / 2).priority) {
            swim(index);
        } else {
            sink(index);
        }
    }

    private void sink(int index) {
        while (index * 2 + 1 < minHeap.size()) {
            int next = index * 2 + 1;
            if (next + 1 < minHeap.size() && minHeap.get(next + 1).priority < minHeap.get(next).priority) {
                ++next;
            }
            if (minHeap.get(index).priority <= minHeap.get(next).priority) {
                break;
            }
            swapNodes(index, next);
            index = next;
        }
    }

    private void swim(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && minHeap.get(index).priority < minHeap.get(parentIndex).priority) {
            swapNodes(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private void swapNodes(int index1, int index2) {
        indexesMap.put(minHeap.get(index1).item, index2);
        indexesMap.put(minHeap.get(index2).item, index1);

        PriorityNode tmp = minHeap.get(index1);
        minHeap.set(index1, minHeap.get(index2));
        minHeap.set(index2, tmp);
    }
}
