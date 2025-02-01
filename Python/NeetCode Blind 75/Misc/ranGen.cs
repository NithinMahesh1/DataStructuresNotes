using System

namespace gen
{
    public class gen {
        public static int ranGen() {
            Random random = new Random();
            int randomNum = random.Next(0,(int)1e9);
            return randomNum;
        }
        public static void Main(string[] args) {
            Console.WriteLine(ranGen());
        }
    }
}