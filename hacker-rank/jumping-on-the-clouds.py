def solve(numberOfClouds, clouds):
    clouds = clouds + ["1"]
    jumps = 0
    currentCloudIndex = 0

    while currentCloudIndex < numberOfClouds - 1:
        nextCloud1 = clouds[currentCloudIndex + 1]
        nextCloud2 = clouds[currentCloudIndex + 2]

        if nextCloud2 == "0":
            currentCloudIndex += 2
            jumps += 1
        elif nextCloud1 == "0":
            currentCloudIndex += 1
            jumps += 1

    return jumps

def main():
    numberOfClouds = int(input())
    clouds = input().split()

    print(str(solve(numberOfClouds, clouds)) + "\n")

if __name__ == "__main__":
    main()
