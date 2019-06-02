main :: IO ()
main = do
  min <- readLn
  max <- readLn
  putStrLn (concat (fizzBuzz [min..max]))

fizzBuzz :: (Integral numbers, Show numbers) => [numbers] -> [String]
fizzBuzz numbers = [fizzBuzzOneElement number | number <- numbers]

fizzBuzzOneElement :: (Integral number, Show number) => number -> String
fizzBuzzOneElement number
  | number `mod` (3 * 5) == 0 = "FizzBuzz\n"
  | number `mod` 3 == 0 = "Fizz\n"
  | number `mod` 5 == 0 = "Buzz\n"
  | otherwise = show number ++ "\n"
