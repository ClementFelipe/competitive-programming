class UnionFind:
    def __init__(self, size):
        self.size = size
        self.components = size
        self.ids = list(range(size))
        self.sizes = [1] * size

    def find(self, e):

        parent = e

        while parent != self.ids[parent]:
            parent = self.ids[parent]

        current = e

        # compress
        while current != parent:
            immediate_parent = self.ids[current]
            self.ids[current] = parent
            current = immediate_parent

        return parent

    def union(self, p, q):
        parent_p = self.find(p)
        parent_q = self.find(q)

        if parent_p != parent_q:
            if self.sizes[parent_p] < self.sizes[parent_q]:
                self.ids[parent_p] = parent_q
                self.sizes[parent_q] += self.sizes[parent_p]
            else:
                self.ids[parent_q] = parent_p
                self.sizes[parent_p] += self.sizes[parent_q]

            self.components -= 1

    def component_size(self, e):
        return self.sizes[self.find(e)]

    def are_connected(self, p, q):
        return self.find(p) == self.find(q)

def main():
    sizes = list(map(int, input().split()))

    n_elements = sizes[0]
    n_queries = sizes[1]

    sets = UnionFind(n_elements + 1)

    while n_queries > 0:
        n_queries -= 1

        query_items = input().split()

        query_type = query_items[0]

        if query_type == 'Q':
            element = int(query_items[1])

            print(sets.component_size(element))
        else:
            p = int(query_items[1])
            q = int(query_items[2])

            sets.union(p, q)

if __name__ == "__main__":
    main()