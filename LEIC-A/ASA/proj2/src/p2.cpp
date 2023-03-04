#include <algorithm>
#include <iostream>
#include <vector>

struct edge_t {
    int u;
    int v;
    size_t weight;
};

struct graph_t {
    int size;
    int v_count;
    std::vector<int> ranks;
    std::vector<int> parents;
    std::vector<edge_t> data;
};

graph_t *graph;

void make_set(int x) {
    graph->parents[x] = x;
    graph->ranks[x] = 0;
}

int find_set(int x) {
    if (x != graph->parents[x])
        graph->parents[x] = find_set(graph->parents[x]);
    return graph->parents[x];
}

void link(int x, int y) {
    if (graph->ranks[x] > graph->ranks[y]) {
        graph->parents[y] = x;
    } else {
        graph->parents[x] = y;
        if (graph->ranks[x] == graph->ranks[y])
            graph->ranks[y]++;
    }
}

void node_union(int x, int y) { link(find_set(x), find_set(y)); }

bool comparator(edge_t e1, edge_t e2) { return e1.weight > e2.weight; }

size_t get_maximum_cost_spanning_tree() {
    size_t result = 0;

    std::sort(graph->data.begin(), graph->data.end(), comparator);

    for (int i = 0; i < graph->size; i++) {
        if (find_set(graph->data[i].u) != find_set(graph->data[i].v)) {
            result += graph->data[i].weight;
            node_union(graph->data[i].u, graph->data[i].v);
        }
    }
    return result;
}

void read_input() {
    size_t weight;
    int v_count, e_count, id1, id2;
    std::cin >> v_count >> e_count;

    graph->parents = std::vector<int>(v_count);
    graph->ranks = std::vector<int>(v_count);

    for (int i = 0; i < e_count; i++) {
        std::cin >> id1 >> id2 >> weight;

        make_set(--id1);
        make_set(--id2);

        graph->data.push_back(edge_t({.u = id1, .v = id2, .weight = weight}));
    }
    graph->size = e_count;
    graph->v_count = v_count;
}

int main(void) {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    graph = new graph_t();
    read_input();

    std::cout << get_maximum_cost_spanning_tree() << "\n";

    delete graph;

    return 0;
}
