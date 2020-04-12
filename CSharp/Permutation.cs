using System;

namespace jmoore
{
    class Permutation {

        static void Main(string[] args) {
            int right = args[0].Length - 1;
            Permute(args[0], 0, right);
        }

        static void Permute(string word, int left, int right)
        {
            if (left == right) {
                Console.WriteLine(word);
            }
            else
            {
                for(int i = left; i <= right; i++)
                {
                    word = Swap(word, left, i);
                    Permute(word, left + 1, right);
                    word = Swap(word, left, i);
                }
            }
        }

        static string Swap(string word, int idx1, int idx2)
        {
            char[] chars = word.ToCharArray();
            char temp = chars[idx1];
            chars[idx1] = chars[idx2];
            chars[idx2] = temp;
            return new String(chars);
        }
    }
}
