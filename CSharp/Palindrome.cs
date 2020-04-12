using System;

namespace jmoore
{
    class Palindrome {

        static void Main(string[] args)
        {
            // string strCount = Console.ReadLine();
            // int count = Convert.ToInt32(strCount);
            // string word, answer;
            // for (int i = 0; i < count; i++)
            // {
            //     word = Console.ReadLine();
            //     answer = LongestPalindrome(word);
            //     Console.WriteLine(answer);
            // }

            var answer= LongestPalindrome(args[0]);
            Console.WriteLine(answer);
        }

        static string LongestPalindrome(string original)
        {
            // check for palindromes of length i
            // i starts at the length of the string and goes down
            for (int i = original.Length; i > 0; i--)
            {
                int start = 0;
                string answer;

                // start at the beginning of the string and
                // check for palinddromes of length i
                // return as soon as you find one
                while (start + i <= original.Length)
                {
                    answer = original.Substring(start++, i);
                    if (IsPalindrome(answer)) {
                        return answer;
                    }
                }
            }
            // if none found, then original word was null
            return null;
        }

        static bool IsPalindrome(string word)
        {
            if (word == null) return false;
            if (word.Length == 1) return true;
            var chars = word.ToCharArray();

            bool isPalindrome = true;
            char right;
            for (int i = 0; i < (chars.Length / 2); i++)
            {
                right = chars[chars.Length - 1 - i];
                if (chars[i] != right) {
                    isPalindrome = false;
                }
            }
            return isPalindrome;
        }
    }
}
