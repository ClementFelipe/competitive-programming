def build_queues(plants):
    queues = []

    previous_plant = plants[0]

    for plant in plants:
        if len(queues) == 0 or plant > previous_plant:
            queues.append([plant])
        else:
            queues[len(queues) - 1].append(plant)

        previous_plant = plant

    return queues

def dequeue_queues(queues):
    dequeued_queues = [queues[0]]

    for queue in queues[1:]:
        queue.pop(0)
        dequeued_queues.append(queue)

    return list(filter(lambda q: len(q) > 0, dequeued_queues))


def join_queues(queues):
    joined_queues = []

    previous_queue = None

    for queue in queues:
        if previous_queue is None:
            joined_queues = [queue]
            previous_queue = queue
            continue

        if  previous_queue[len(previous_queue) - 1] >= queue[0]:
            popped_queue = joined_queues.pop()
            joined_queues.append(popped_queue + queue)
        else:
            joined_queues.append(queue)

        previous_queue = joined_queues[len(joined_queues) - 1]

    return joined_queues

def simulate_days(queues):
    days_passed = 0

    while len(queues) > 1:
        queues = dequeue_queues(queues)
        queues = join_queues(queues)

        days_passed += 1

    return days_passed

def count_days(plants):
    queues = build_queues(plants)

    return simulate_days(queues)


def main():
    _ = int(input())
    plants = list(map(int, input().split()))

    print(count_days(plants))

if __name__ == "__main__":
    main()