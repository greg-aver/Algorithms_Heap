package org.gregory;

class Heap {
    public int[] HeapArray; // хранит неотрицательные числа-ключи
    private int heapSize;

    public Heap() {
        HeapArray = null;
        heapSize = 0;
    }

    public void MakeHeap(int[] a, int depth) {
        HeapArray = new int[(int) Math.pow(2, depth + 1) - 1];
        heapSize = a.length;
        System.arraycopy(a, 0, HeapArray, 0, getHeapSize());
        for (int i = getHeapSize() / 2; i >= 0; i--) {
            orderingVertex(i);
        }
        /*        for (int i = getHeapSize() / 2; i >= 0; i--) {
            orderingVertex(i);
        }*/
    }

    /*    private int orderingVertex(int indexVertex) {
            int indexLeftChild = 2 * indexVertex + 1;
            int indexRightChild = 2 * indexVertex + 2;
            int indexLagestChild = indexVertex;

            if (indexLeftChild < getHeapSize() && HeapArray[indexVertex] < HeapArray[indexLeftChild]) {
                indexLagestChild = indexLeftChild;
            }
            if (indexRightChild < getHeapSize() && HeapArray[indexVertex] < HeapArray[indexRightChild]) {
                indexLagestChild = indexRightChild;
            }
            if (indexLagestChild == indexVertex) {
                return indexLagestChild;
            }
            swap(indexLagestChild, indexVertex);
            orderingVertex(indexLagestChild);
        }*/


    /*private void orderingVertex(int indexVertex) {
        int indexLeftChild = 2 * indexVertex + 1;
        int indexRightChild = 2 * indexVertex + 2;
        int indexLargestChild = indexVertex;

        if (indexLeftChild < getHeapSize() && HeapArray[indexVertex] < HeapArray[indexLeftChild]) {
            indexLargestChild = indexLeftChild;
        }
        if (indexRightChild < getHeapSize() && HeapArray[indexVertex] < HeapArray[indexRightChild]) {
            indexLargestChild = indexRightChild;
        }
        if (indexLargestChild == indexVertex) {
            return;
        }
        swap(indexLargestChild, indexVertex);
        orderingVertex(indexLargestChild);
    }*/

    public void orderingVertex(int i)
    {
        int leftChild;
        int rightChild;
        int largestChild;

        for (; ; )
        {
            leftChild = 2 * i + 1;
            rightChild = 2 * i + 2;
            largestChild = i;

            if (leftChild < heapSize && HeapArray[leftChild] > HeapArray[largestChild])
            {
                largestChild = leftChild;
            }

            if (rightChild < heapSize && HeapArray[rightChild] > HeapArray[largestChild])
            {
                largestChild = rightChild;
            }

            if (largestChild == i)
            {
                break;
            }

            int temp = HeapArray[i];
            HeapArray[i] = HeapArray[largestChild];
            HeapArray[largestChild] = temp;
            i = largestChild;
        }
    }

    /*   private int orderingVertex(int indexVertex) {
        int leftChild = 2 * indexVertex + 1;
        int rightChild = 2 * indexVertex + 2;
        int lagestChild = indexVertex;

        if (HeapArray[indexVertex] < HeapArray[leftChild]) {
            lagestChild = orderingVertex(leftChild);
        }
        if (HeapArray[indexVertex] < HeapArray[rightChild]) {
            lagestChild = orderingVertex(rightChild);
        }
        if (lagestChild == indexVertex) {
            return indexVertex;
        }
        swap(lagestChild, indexVertex);
        return lagestChild;
    }*/

    public int GetMax() {
        // вернуть значение корня и перестроить кучу
        if (HeapArray.length == 0) {
            return -1; // если куча пуста
        }
        int max = HeapArray[0];
        addVertexAndRebuildHeap();
        return max;
    }

    private void addVertexAndRebuildHeap() {
        int rootNew = HeapArray[getHeapSize() - 1];
        HeapArray[getHeapSize()] = 0;
        heapSizeDecrement();
        orderingVertex(rootNew);
    }

    public boolean Add(int key) {
        // добавляем новый элемент key в кучу и перестраиваем её
        if (getHeapSize() >= HeapArray.length) {
            return false; // heap is full
        }
        HeapArray[getHeapSize()] = key;
        findPlace(getHeapSize(), key);
        heapSizeIncrement();
        return true;
    }

    private void findPlace(int indexCurrent, int key) {
        int indexParent = (indexCurrent - 1) / 2;
        //termination conditions
        if (key > indexParent) {
            return;
        }
        swap(indexParent, indexCurrent);
        findPlace(indexParent, key);
    }

    private void swap(int indexParent, int indexChild) {
        int valueTemporary = HeapArray[indexParent];
        HeapArray[indexParent] = HeapArray[indexChild];
        HeapArray[indexChild] = valueTemporary;
    }

    private void heapSizeDecrement() {
        heapSize--;
    }

    private void heapSizeIncrement() {
        heapSize++;
    }

    public int getHeapSize() {
        return heapSize;
    }
}
