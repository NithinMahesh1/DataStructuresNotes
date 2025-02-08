using System;

public class HelloWorld
{
    public static void Main(string[] args)
    {
        int[] vals = new int[]{1,2,3,4,5,6};
        
        for(int i=vals.Length-1; i>=0; i--) {
            Console.WriteLine(vals[i]);
        }
    }
}