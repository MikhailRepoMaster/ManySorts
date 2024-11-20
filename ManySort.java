import java.util.Random;

public class ManySort 
{
    private static int swapCountQuick = 0;
    private static int swapCountBubble = 0;
    private static int iterationCountInsertion = 0;
    private static int iterationCountShell = 0;

    public static void main(String[] args) 
    {
        // Zadaniye1();
        Zadaniye2();
    }

    public static void Zadaniye1()
    {
        int size = 50;
        int[] arr1 = new int[size];
        int[] arr2 = new int[size];
        Random random = new Random();
        
        for (int i = 0; i < size; i++) 
        {
            arr1[i] = random.nextInt(50);  
            arr2[i] = random.nextInt(50);  
        }
        System.out.println("Быстрая сортировка: ");
        System.out.println("Начальный массив: ");
        printArray(arr1);

        quickSort(arr1, 0, arr1.length - 1);
        System.out.println("Отсортированный массив: "); 
        printArray(arr1);
        System.out.println("Количество свапов: " + swapCountQuick); // <100
        
        System.out.println("\nПузырьковая сортировка: ");
        int[] arrBubble = arr2.clone();
        System.out.println("Начальный массив: ");
        printArray(arrBubble);
        bubbleSort(arrBubble);
        System.out.println("Отсортированный массив: ");
        printArray(arrBubble);
        System.out.println("Количество свапов: " + swapCountBubble);  // >500
    }

    public static void Zadaniye2()
    {
        int size = 50;
        Random random = new Random();

        int[][] arrays = new int[3][size];
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < size; i++) 
            {
                arrays[k][i] = random.nextInt(100); 
            }
        }

        for (int k = 0; k < 3; k++) {
            System.out.println("\nМассив " + (k + 1) + ": ");
            printArray(arrays[k]);

            int[] arrInsertion = arrays[k].clone();
            insertionSort(arrInsertion);
            System.out.println("Отсортированный массив вставка: "); 
            printArray(arrInsertion);
            System.out.println("Количество итераций вставка: " + iterationCountInsertion); // >500
            iterationCountInsertion = 0; 

         
            int[] arrShell = arrays[k].clone();
            shellSort(arrShell);
            System.out.println("Отсортированный массив шелла: "); 
            printArray(arrShell);
            System.out.println("Количество итераций шелла: " + iterationCountShell); // <500
            iterationCountShell = 0; 
        }
    }

    public static void quickSort(int[] arr, int l, int r) 
    {
        int i = l, j = r;
        int pivot = arr[(l + r) / 2];
        while (i <= j) 
        {
            while (arr[i] < pivot) 
            {
                i++;
            }
            while (arr[j] > pivot) 
            {
                j--;
            }
            if (i <= j) 
            {
                swap(arr, i, j);
                swapCountQuick++;
                i++;
                j--;
            }
        }

        if (l < j) 
        {
            quickSort(arr, l, j);
        }
        if (i < r) 
        {
            quickSort(arr, i, r);
        }
    }

    public static void insertionSort(int[] arr) 
    {
        int n = arr.length;
        for (int i = 1; i < n; i++) 
        {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) 
            {
                arr[j + 1] = arr[j];
                j--;
                iterationCountInsertion++;
            }
            arr[j + 1] = key;
            iterationCountInsertion++;
        }
    }

    public static void shellSort(int[] arr) 
    {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) 
        {
            for (int i = gap; i < n; i++) 
            {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) 
                {
                    arr[j] = arr[j - gap];
                    iterationCountShell++;
                }
                arr[j] = temp;
                iterationCountShell++;
            }
        }
    }

    public static void bubbleSort(int[] arr) 
    {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) 
        {
            for (int j = 0; j < n - i - 1; j++) 
            {
                if (arr[j] > arr[j + 1]) 
                {
                    swap(arr, j, j + 1);
                    swapCountBubble++;
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) 
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void printArray(int[] arr) 
    {
        for (int num : arr) 
        {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
