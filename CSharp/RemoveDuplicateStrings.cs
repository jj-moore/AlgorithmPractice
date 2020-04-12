using System;
using System.Text.RegularExpressions;

namespace jmoore
{
    class RemoveDuplicateStings {
        public static void Main(string[] args) {
            string strCount = Console.ReadLine();
            int count = Convert.ToInt32(strCount);
            string word, answer;
            for (int i = 0; i < count; i++)
            {
                word = Console.ReadLine();
                answer = RemoveDuplicates(word);
                Console.WriteLine(answer);
            }
            
            // if (args.Length == 0)
            // {
            //     Console.WriteLine("Please supply an input word");
            // }
            // else
            // {
            //     string answer = RemoveDuplicates(args[0]);
            //     Console.WriteLine(answer);
            // }
        }

        static string RemoveDuplicates(string word)
        {
            string pattern = @"(.)\1+";
            string newWord = Regex.Replace(word, pattern, "");
            if (word == newWord)
            {
                return word;
            }
            return RemoveDuplicates(newWord);
        }
    }
}
