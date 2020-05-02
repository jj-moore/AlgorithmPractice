static int sherlockAndAnagrams(string s) {
		var matches = 0;

		for (int i = 1; i <= s.Length - 1; i++) {
				var a = new List<string>();

				for (int j = 0; j <= s.Length - i; j++) {
						a.Add(new string(s.Substring(j, i).OrderBy(c => c).ToArray()));
				}

				var b = (from c in a group c by c into g select
						new { Key = g.Key, Count = g.Count() })
						.ToDictionary(g => g.Key, g => g.Count);

				foreach (var v in b.Values) {
						matches += v * (v - 1) / 2;
				}
		}

		return matches;
}
