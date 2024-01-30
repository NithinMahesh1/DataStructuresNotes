# Given a value integer such as 123 or -123 
# Reverse the value and return 

input = -123
stringifyInput = str(input)
length = len(stringifyInput)
returnVal = ""

for i in range(length - 1, -1, -1):
    if stringifyInput[i] == "-":
        continue
    returnVal = returnVal + stringifyInput[i]

if "-" in stringifyInput:
    returnVal = "-" + returnVal

print(returnVal)