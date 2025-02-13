// Possible follow ups:

// Normalize probabilities if they don’t sum to 1:
// double sum = probabilities.Sum();
// for (int i = 0; i < probabilities.Length; i++)
// {
//     probabilities[i] /= sum; // Normalize to ensure sum = 1
// }

// Convert percentages to decimal:
// double[] probabilities = { 10, 50, 40 };
// for (int i = 0; i < probabilities.Length; i++)
// {
//     probabilities[i] /= 100.0;
// }

// This is shorthand for:
// e.g.
// b /= 1; is equal to b =  b / 1;

using System;
using System.Linq;

public class HelloWorld
{
    private static Random ran = new Random();

    public static void Main(string[] args)
    {
        int[] numbers = { 1, 2, 3, 4, 5 };
        double[] probabilities = { 0.1, 0.5, 0.3, 0.05, 0.05 };

        if (Math.Abs(probabilities.Sum() - 1.0) > 1e-6) 
        {
            throw new ArgumentException("Probabilities must sum to 1");
        }

        double r = ran.NextDouble();
        double cumulative = 0.0;

        int res = -1;

        for (int i = 0; i < probabilities.Length; i++)
        {
            // We generate a random number between 0 and 1
            // Using this decimal value we compare to our probabilities
            // If we have value greater than or equal we take that index from the numbers arr
            cumulative += probabilities[i];
            if (r <= cumulative)
            {
                res = numbers[i];
                break;
            }
        }

        Console.WriteLine(res);
    }
}
