def main():
    n_queries = int(input())

    stack = []
    max_stack = []

    while n_queries > 0:
        query = input()
        n_queries = n_queries - 1

        if " " in query:
            current_num = int(query.split()[1])

            stack.append(current_num)

            if len(max_stack) == 0 or current_num >= max_stack[len(max_stack) - 1]:
                max_stack.append(current_num)
        elif query == "2":
            popped = stack.pop()

            if popped == max_stack[len(max_stack) - 1]:
                max_stack.pop()
        else:
            print(str(max_stack[len(max_stack) - 1]))

if __name__ == "__main__":
    main()