def main():
    ns = input()
    n_items = int(ns.split()[0])
    n_queries = int(ns.split()[1])

    items = list(map(int, input().split()))

    maxes = []

    while n_queries > 0:
        n_queries = n_queries - 1

        window_size = int(input())

        queue = []
        maxes = []
        min_n = 2000000

        for n in items:
            if len(queue) == window_size:
                popped = queue.pop(0)

                if popped == maxes[0]:
                    maxes.pop(0)

                    if len(maxes) == 0:
                        maxes = [max(queue)] if len(queue) > 0 else []

            queue.append(n)

            if len(maxes) == 0:
                maxes.append(n)
            elif n >= maxes[0]:
                if n > maxes[0]:
                    maxes.pop(0)
                maxes.append(n)

            if len(queue) == window_size and maxes[0] < min_n:
                min_n = maxes[0]

        print(min_n)

if __name__ == "__main__":
    main()
