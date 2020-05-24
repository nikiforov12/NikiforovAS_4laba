//ссылка: https://github.com/Julia20011974/AaSD
package internalSorting;

import java.util.Scanner;

public class Sort {
	static private long time = 0;
	static private int compare = 0;
	static private int swap = 0;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.println("Введите количество элементов массива:");
		int size = in.nextInt();

		int[] Array = random(size);
		System.out.println("Массив задан рандомно:");
		Sorts(Array);

		System.out.println("----------------------------");
		System.out.println("Массив по возврастанию:");
		Sorts(Array);

		System.out.println("----------------------------");
		System.out.println("Массив по убыванию:");
		reverseArray(Array);

		Sorts(Array);
		in.close();
	}

	static void Sorts(int[] Array) {
		System.out.println("Пузырьком:");
		bubbleSort(Array);
		result();

		System.out.println();

		System.out.println("Вставка:");
		insertSort(Array);
		result();

		System.out.println();

		System.out.println("Выбор:");
		choiceSort(Array);
		result();

		System.out.println();

		System.out.println("Естественное двухпутевое слияние:");
		mergeSort(Array, Array.length);
		result();

		System.out.println();

		System.out.println("Быстрая сортировка:");
		quickSort(Array);
		result();

		System.out.println();
	}

	static void reverseArray(int[] Array) {
		for (int i = 0; i <= (Array.length - 1) / 2; i++) {
			int temp = Array[Array.length - 1 - i];
			Array[Array.length - 1 - i] = Array[i];
			Array[i] = temp;
		}
	}

	static private void result() {
		System.out.println("Время в миллисекундах: " + time);
		System.out.println("Количество сравнений: " + compare);
		System.out.println("Количество обменов: " + swap);
	}

	static int[] random(int size) {
		int[] Array = new int[size];

		for (int i = 0; i < Array.length; i++)
			Array[i] = (int) (Math.random() * 1000);
		return Array;
	}

	/*static void outputArray(int[] Array) {
		for (int i = 0; i < Array.length; i++)
			System.out.print(Array[i] + "  ");
		System.out.println();
	}*/

	static private void bubbleSort(int[] Array) {
		int[] arr = Array.clone();
		compare = 0;
		swap = 0;
		long begin = System.currentTimeMillis();

		for (int i = arr.length - 1; i >= 1; i--)
			for (int j = 0; j < i; j++) {
				compare++;
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swap++;
				}
			}

		time = System.currentTimeMillis() - begin;
	}

	static private void insertSort(int[] Array) {
		int buf;
		int[] arr = Array.clone();
		compare = 0;
		swap = 0;
		long begin = System.currentTimeMillis();
		for (int i = 1; i < arr.length; i++) {
			buf = arr[i];
			compare++;
			while (i > 0 && buf < arr[i - 1]) {
				compare++;
					arr[i] = arr[i - 1];
					i--;
			}
			swap++;
			arr[i] = buf;
		}
		time = System.currentTimeMillis() - begin;
		//outputArray(arr);
	}

	static private void choiceSort(int[] Array) {
		int[] arr = Array.clone();
		int k;
		int element;
		compare = 0;
		swap = 0;
		long begin = System.currentTimeMillis();
		for (int i = 0; i < arr.length; i++) {
			k = i;
			element = arr[i];
			for (int j = i + 1; j < arr.length; j++) {
				compare++;
				if (arr[j] < element) {
					k = j;
					element = arr[j];
				}
			}
			swap++;
			arr[k] = arr[i];
			arr[i] = element;
		}
		time = System.currentTimeMillis() - begin;
	}

	static private void quickSort(int[] Array) {
		compare = 0;
		swap = 0;
		long begin = System.currentTimeMillis();
		qSort(Array, 0, Array.length - 1);
		time = System.currentTimeMillis() - begin;
	}

	static private void qSort(int[] Array, int b, int e) {
		int l = b, r = e;
		int piv = Array[(l + r) / 2];
		while (l <= r) {
			compare++;
			while (Array[l] < piv) {
				compare++;
				l++;
			}
			compare++;
			while (Array[r] > piv) {
				compare++;
				r--;
			}
			if (l <= r) {
				int temp = Array[l];
				Array[l] = Array[r];
				Array[r] = temp;
				swap++;
				l++;
				r--;
			}
		}
		if (b < r)
			qSort(Array, b, r);
		if (e > l)
			qSort(Array, l, e);
	}

	static private void mergeSort(int[] a, int n) {
		boolean flag1 = true;
		boolean flag2 = true;
		boolean flag3 = true;
		int[] arr = new int[n * 2];
		swap = 0;
		compare = 0;

		long begin = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			arr[i] = a[i];
			arr[i + n] = a[i];
		}
		int f = -1, s, d = -1, i = -1, j = -1, k = -1, l = -1;
		s = 0;
		do {
			flag2 = true;
			if (s == 0) {

				i = 0;
				j = n - 1;
				k = n;
				l = (2 * n) - 1;
			}
			if (s == 1) {

				i = n;
				j = (2 * n) - 1;
				k = 0;
				l = n - 1;
			}
			d = 1;
			f = 1;
			compare++;
			if (((arr[i] <= arr[j]) && (i != j)) || (arr[i] > arr[j])) {

				do {
					compare++;
					if ((arr[i] <= arr[j]) && (i != j)) {

						do {
							flag3 = true;
							flag1 = true;
							swap++;
							arr[k] = arr[i];

							k += d;
							i++;
							compare++;
							if (arr[i - 1] <= arr[i]) {
								flag3 = false;
								break;
							}
							do {
								swap++;
								arr[k] = arr[j];

								k += d;
								j--;
								compare++;
								if (arr[j + 1] <= arr[j]) {
									continue;
								} else {
									flag1 = false;
									flag3 = false;
									f = 0;
									d = -d;
									int buf = k;
									k = l;
									l = buf;
									break;
								}
							} while (flag1);
						} while (flag3);
					}
					compare++;
					if (arr[i] > arr[j]) {

						do {
							flag3 = true;
							flag1 = true;

							swap++;
							arr[k] = arr[j];
							k += d;
							j--;
							compare++;
							if (arr[j + 1] <= arr[j]) {

								flag3 = false;
								break;
							}
							do {
								swap++;
								arr[k] = arr[i];

								k += d;
								i++;
								compare++;
								if (arr[i - 1] <= arr[i]) {

									continue;
								} else {
									flag1 = false;
									flag3 = false;
									f = 0;
									d = -d;
									int buf = k;
									k = l;
									l = buf;
									break;
								}
							} while (flag1);
						} while (flag3);
					}
					if (i == j) {
						flag2 = false;
						break;
					}
				} while (flag2);
			}
			if (i == j) {
				swap++;
				arr[k] = arr[i];
				if (f == 0) {
					s = 1 - s;
				} else {
					break;
				}
			}
		} while (f == 0);
		time = System.currentTimeMillis() - begin;
		/*
		if (s == 0) {
			for (int q = n; q < 2 * n; q++)
				System.out.print(arr[q] + " ");
			System.out.println();
		} else {
			for (int q = 0; q < n; q++)
				System.out.print(arr[q] + " ");
			System.out.println();
		}*/
		
	}
}
