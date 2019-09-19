import Data.Char

main :: IO ()
main = do
    line <- getLine
    putStrLn $ solve line

solve :: String -> String
solve s = checkOutput $ getPrefix $ s

getPrefix :: String -> String
getPrefix s = takeWhile isDigit s

checkOutput :: String -> String
checkOutput [] = "-1"
checkOutput s = s
