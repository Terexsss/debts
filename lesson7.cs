// Задача 1: Вывод всех натуральных чисел от M до N с помощью рекурсии

using System;

class Program
{
    static void PrintNumbers(int M, int N)
    {
        if (M > N)
        {
            return;
        }
        Console.WriteLine(M);
        PrintNumbers(M + 1, N);
    }

    static void Main()
    {
        Console.Write("Введите значение M: ");
        int M = int.Parse(Console.ReadLine());
        Console.Write("Введите значение N: ");
        int N = int.Parse(Console.ReadLine());

        Console.WriteLine($"Натуральные числа от {M} до {N}:");
        PrintNumbers(M, N);
    }
}

// Задача 2: Вычисление функции Аккермана с помощью рекурсии

using System;

class Program
{
    static int Ackermann(int m, int n)
    {
        if (m == 0)
        {
            return n + 1;
        }
        else if (m == 1)
        {
            return n + 2;
        }
        else if (m == 2)
        {
            return 2 * n + 3;
        }
        else if (m == 3)
        {
            return (int)Math.Pow(2, n + 3) - 3;
        }
        else
        {
            return Ackermann(m - 1, Ackermann(m, n - 1));
        }
    }

    static void Main()
    {
        Console.Write("Введите неотрицательное число m: ");
        int m = int.Parse(Console.ReadLine());
        Console.Write("Введите неотрицательное число n: ");
        int n = int.Parse(Console.ReadLine());

        int result = Ackermann(m, n);
        Console.WriteLine($"A({m}, {n}) = {result}");
    }
}


// Задача 3: Вывод элементов массива в обратном порядке с помощью рекурсии

using System;

class Program
{
    static void PrintReverse(int[] array, int index)
    {
        if (index < 0)
        {
            return;
        }
        Console.WriteLine(array[index]);
        PrintReverse(array, index - 1);
    }

    static void Main()
    {
        int[] array = { 1, 2, 3, 4, 5 }; // Можете задать произвольный массив

        Console.WriteLine("Элементы массива в обратном порядке:");
        PrintReverse(array, array.Length - 1);
    }
}