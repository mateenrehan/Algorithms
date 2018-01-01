public class Dijkstra {

  /** Array of Initialize which is inner object class */
	private static Initialize edges[];

  /** Array of Initialize which is inner object class */
	private static Data data[];

	public int[][] Dijkstra_alg(int n, int e, int mat[][], int s) {
		edges = new Initialize[e];
		int[][] answer = new int [n][2];

    //Initialize edges array by using mat[][] given as parameter
		for (int i = 0; i < e; i++) {
			edges[i] = new Initialize(mat[i][0], mat[i][2], mat[i][1]);
		}

		data = new Data[n];
		for (int i = 0; i < n; i++) {
			if (i == s - 1) {
				data[i] = new Data(i, 0, true, 1);
			} else {
				data[i] = new Data(i, 1000000, false, 1);
			}
		}

		for (int m = 0; m < n; m++) {
			Data u = null;
			if (m == 0) {
				u = data[s - 1];
			} else {
				u = extractMin();
			}

			if (u == null) {
				break;
			}

			for (int i = 0; i < edges.length; i++) {
				if ((edges[i].first - 1) == u.idx) {
					if (data[edges[i].second - 1].value > u.value + edges[i].weight) {
						data[edges[i].second - 1].value = u.value + edges[i].weight;
						answer[edges[i].second - 1][0] = data[edges[i].second - 1].value;
					} else if (data[edges[i].second - 1].value == u.value + edges[i].weight) {
						data[edges[i].second - 1].usp = 0;
					}
				} else if ((edges[i].second - 1) == u.idx) {
					if (data[edges[i].first - 1].value > u.value + edges[i].weight) {
						data[edges[i].first - 1].value = u.value + edges[i].weight;
						answer[edges[i].first - 1][0] = data[edges[i].first - 1].value;
					} else if (data[edges[i].first - 1].value == u.value + edges[i].weight) {
						data[edges[i].first - 1].usp = 0;
					}
				}
			}
			data[u.idx].pred = true;
			answer[u.idx][1] = data[u.idx].usp;
		}

		return answer;
	}

	public Data extractMin() {
		int min = 10000000;
		int index = -1;
		for (int i = 0; i < data.length; i++) {
			if (data[i].pred == false && (min > data[i].value)) {
				min = data[i].value;
				index = i;
			}
			if ((i == data.length - 1) && (index == -1)) {
				return null;
			}
		}
		return data[index];
	}

	private class Initialize {
		int first;
		int weight;
		int second;

		private Initialize(int first, int weight, int second) {
			super();
			this.first = first;
			this.weight = weight;
			this.second = second;
		}
	}

	private class Data {
		int idx;
		int value;
		boolean pred;
		int usp;

		private Data(int idx, int value, boolean pred, int usp) {
			super();
			this.idx = idx;
			this.value = value;
			this.pred = pred;
			this.usp = usp;
		}
	}
}
