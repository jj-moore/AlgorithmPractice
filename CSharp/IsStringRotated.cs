using System;

namespace jmoore
{
    class IsStringRotated
    {
        public static void Main(string[] args)
        {
            string strCount = Console.ReadLine();
            int count = Convert.ToInt32(strCount);
            string word1, word2, answer;
            for (int i = 0; i < count; i++)
            {
                word1 = Console.ReadLine();
                word2 = Console.ReadLine();
                answer = IsRotated(word1, word2) ? "1" : "0";
                Console.WriteLine(answer);
            }
            // if (args == null || args.Length < 2)
            // {
            //     Console.WriteLine("Please provide 2 parameters");
            // }
            // else
            // {
            //     string isRotated = IsRotated(args[0], args[1]) ? "1" : "0";
            //     Console.Write(isRotated);
            // }
        }

        static bool IsRotated(string word1, string word2)
        {
            if (word1 == null || word1.Length < 2 || word2 == null || word2.Length < 2 )
            {
                return false;
            }
            string rotatedRight = word1.Substring(word1.Length - 2) + word1.Substring(0, word1.Length - 2);
            string rotatedLeft = word1.Substring(2) + word1.Substring(0, 2);
            return rotatedRight.Equals(word2) || rotatedLeft.Equals(word2);
        }
    }
}
