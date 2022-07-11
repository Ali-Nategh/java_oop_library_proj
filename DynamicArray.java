package com.company;

class DynamicArray implements DArray {
    Object[] m = new Object[3];
    private int A_index = 0;
    public int length = m.length;

    public void add(Object o) {
        if (A_index == m.length) {
            Object[] temp = new Object[2 * m.length];
            this.length *= 2;
            for (int i = 0; i < m.length; i++)
                temp[i] = m[i];
            m = temp;
        }
        m[A_index++] = o;
    }

    public void remove(Object o) {
        for (int i = 0; i < A_index; i++) {
            if (o.equals(m[i])) {
                for (int j = i; j < A_index; j++)
                    m[j] = m[j + 1];
                A_index--;
                break;
            }
        }
    }

    public Object getA_index(int a) {
        if (a < A_index) {
            return m[a];
        } else
            return null;
//            throw new RuntimeException("invalid number");
//        else throw new Exception("invalid number");
    }
}
