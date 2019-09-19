import sys

def handle_case(case_line):
    numbers = list(map(int, case_line.split()))

    n = numbers[0]
    sequence = numbers[1:]

    number_map = [0] * 3001
    number_map[0] = 1

    previous = sequence[0]

    for num in sequence[1:]:
        number_map[abs(num - previous)] = 1
        previous = num

    result = "Jolly" if all(elem == 1 for elem in number_map[:n]) else "Not jolly"

    print(result)

def main():
    case_lines = sys.stdin

    for case_line in case_lines:
        handle_case(case_line)

if __name__ == "__main__":
    main()