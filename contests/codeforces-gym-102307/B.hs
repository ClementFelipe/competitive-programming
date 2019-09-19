main :: IO ()
main = do
    line <- getLine
    putStrLn $ solve line

solve :: String -> String
solve line = appendSuffix line (findPalindrome line)

appendSuffix :: String -> String -> String
appendSuffix wholeString palindrome =
    wholeString ++ reversePrefix wholeString palindrome

findPalindrome :: String -> String
findPalindrome s | isPalindrome s = s
                 | otherwise      = findPalindrome $ tail s

isPalindrome :: String -> Bool
isPalindrome s = s == reverse s

reversePrefix :: String -> String -> String
reversePrefix wholeString palindrome = reverse $ take (length wholeString - length palindrome) wholeString
