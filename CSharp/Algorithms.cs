using System;
using System.Linq;
using System.Collections.Generic;
using System.Text.RegularExpressions;


namespace jmoore
{
    class Algorithms
    {
        static void Main(string[] args)
        {
            // int[] arr1 = {16, 17, 4, 3, 5, 2};
            // int[] arr2 = {1, 2, 3, 4, 0};
            // int[] arr3 = {7, 4, 5, 7, 3};
            // int[] arr4 = {1, 2, 3, 4, 5, 6, 7, 8};
            //
            // int[] answer = ReverseKthGroup(arr1, 3);
            // PrintIntArray(answer);
            // answer = ReverseKthGroup(arr2, 2);
            // PrintIntArray(answer);
            // answer = ReverseKthGroup(arr3, 4);
            // PrintIntArray(answer);
            // answer = ReverseKthGroup(arr4, 5);
            // PrintIntArray(answer);

            // int[] arrival1 = { 900, 940, 950, 1100, 1500, 1800 };
            // int[] depart1 = { 910, 1200, 1120, 1130, 1900, 2000 };
            // int answer = MinimumPlatforms(arrival1, depart1);
            // Console.WriteLine(answer);

            // int[] arrival2 = { 900, 1100, 1235 };
            // int[] depart2 = { 1000, 1200, 1240 };
            // answer = MinimumPlatforms(arrival2, depart2);
            // Console.WriteLine(answer);

            Permute("ABC", 0, 2);
        }

        static void Permute(String str, int l, int r)
        {
            if (l == r)
                Console.WriteLine();
            else
            {
                for (int i = l; i <= r; i++)
                {
                    str = Swap(str, l, i);
                    Permute(str, l + 1, r);
                    str = Swap(str, l, i);
                }
            }
        }

        static string Swap(String a, int i, int j)
        {

            Console.Write(a + " " + i + " " + j + " ");
            char temp;
            char[] charArray = a.ToCharArray();
            temp = charArray[i] ;
            charArray[i] = charArray[j];
            charArray[j] = temp;
            string s = new string(charArray);
            return s;
        }

        static string ReverseWords(string original)
        {
            //string[] words = Regex.Split(original, @"\.");
            string[] words = original.Split(' ');
            int left = 0;
            int right = words.Count() - 1;
            string temp;
            while (left < right) {
                temp = words[left];
                words[left] = words[right];
                words[right] = temp;
                left++;
                right--;
            }
            return String.Join(" ", words);
        }


        static void PrintIntArray(int[] arr)
        {
            foreach (int num in arr) {
                Console.Write($"{num} ");
            }
            Console.WriteLine();
        }

        static int Equilibrium (int[] arr)
        {
            if (arr.Count() == 1) {
                return 0;
            }
            int center = 1;
            int leftSum = arr[0];
            int rightSum = 0;
            for (int i = 2; i < arr.Count(); i++)
            {
                rightSum += arr[i];
            }
            while (leftSum != rightSum && center < arr.Count() - 2)
            {
                leftSum += arr[center++];
                rightSum -= arr[center];
            }
            if (leftSum == rightSum)
            {
                return center;
            }
            return -1;
        }

        static int[] ArrayLeaders(int[] arr)
        {
            int highest = arr[arr.Count() - 1] - 1;
            List<int> leaders = new List<int>();

            for (int i = arr.Count() - 1; i >= 0; i--)
            {
                if (arr[i] >= highest) {
                    leaders.Insert(0, arr[i]);
                    highest = arr[i];
                }
            }
            return leaders.ToArray();
        }

        static int MinimumPlatforms(int[] arrivals, int[] departs)
        {
            int trains = arrivals.Count();
            if (trains != departs.Count())
                return -1;

            int minPlatforms = 1;
            for (int a = 0; a < trains; a++) {
                int platforms = 1;
                for (int d = a + 1; d < trains; d++) {
                    if (arrivals[a] < arrivals[d] && departs[a] > arrivals[d])
                    {
                        platforms++;
                    }
                }
                if (platforms > minPlatforms)
                    minPlatforms = platforms;
            }
            return minPlatforms;
        }

        static int[] ReverseKthGroup(int[] arr, int k)
        {
            for (int i = 0; i <= arr.Count(); i += k) {

                for (int low = i, high = Math.Min(i + k - 1, arr.Count() - 1); low < high; low++, high--)
                {
                    int temp = arr[low];
                    arr[low] = arr[high];
                    arr[high] = temp;
                }
            }
            return arr;
        }

    }
}
