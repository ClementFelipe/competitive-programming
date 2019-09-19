from sys import stdin

def build_character_costs(n_characters):
    character_costs = [0] * 256

    while n_characters > 0:
        n_characters -= 1

        character_and_cost = stdin.readline().split()

        character = character_and_cost[0]
        cost = int(character_and_cost[1])

        character_costs[ord(character)] = cost

    return character_costs


def handle_case():
    n_characters = int(stdin.readline())

    character_costs = build_character_costs(n_characters)

    total_cents = 0

    n_lines = int(stdin.readline())

    while n_lines > 0:
        n_lines -= 1
        line = stdin.readline()

        for c in line:
            char = ord(c)
            total_cents += character_costs[char] if char < 256 else 0

    dollars = int(total_cents/100)
    cents = total_cents - dollars * 100

    cents_formatted = f"0{cents}" if cents < 10 else cents

    print(f"{dollars}.{cents_formatted}$")

def main():
    n_cases = int(stdin.readline())

    while n_cases > 0:
        n_cases -= 1

        handle_case()

if __name__ == "__main__":
    main()