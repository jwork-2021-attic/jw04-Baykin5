package com.anish.monsterarmy;

public class SelectSorter<T extends Comparable<T>> implements Sorter<T> {

    private T[] a;

    @Override
    public void load(T[] a) {
        this.a = a;
    }

    private void swap(int i, int j) {
        T temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += "" + a[i] + "<->" + a[j] + "\n";
    }

    private String plan = "";


    @Override
    public void sort() {
        int min=0;
        T minValue=a[min];
        T maxValue=a[min];
        for (int i=0;i<a.length;i++){
            if (maxValue.compareTo(a[i])<0)
                maxValue=a[i];
        }
        for (int i=0;i<a.length;i++) {
            for (int j = i; j < a.length; j++) {
                if (minValue.compareTo(a[j])>0) {
                    min = j;
                    minValue = a[j];
                }
            }
            if (i != min)
                swap(i, min);
            minValue = maxValue;
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}